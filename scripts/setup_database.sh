#!/bin/bash
# ===========================================
# Database Setup Script for erp_base_api
# ===========================================
# This script creates the erp_base_db database and all required tables
# 
# Usage:
#   ./setup_database.sh [postgres_user] [postgres_password] [host] [port] [docker_container]
#
# Example:
#   ./setup_database.sh postgres mypassword localhost 5432
#   ./setup_database.sh postgres mypassword localhost 5432 postgres-dev
#   ./setup_database.sh postgres mypassword localhost 5432 ""  # Auto-detect Docker container

set -e

# Default values
DB_USER="${1:-postgres}"
DB_PASSWORD="${2:-postgres}"
DB_HOST="${3:-localhost}"
DB_PORT="${4:-5432}"
DOCKER_CONTAINER="${5:-}"

# Colors for output
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Detect if we should use Docker
USE_DOCKER=false
PSQL_CMD=""

# Check if psql is available locally
if command -v psql &> /dev/null; then
    PSQL_CMD="psql"
    echo -e "${BLUE}Using local psql command${NC}"
elif command -v docker &> /dev/null; then
    USE_DOCKER=true
    # Auto-detect PostgreSQL container if not specified
    if [ -z "$DOCKER_CONTAINER" ]; then
        DOCKER_CONTAINER=$(docker ps --filter "ancestor=postgres" --format "{{.Names}}" | head -n 1)
        if [ -z "$DOCKER_CONTAINER" ]; then
            # Try to find any postgres container
            DOCKER_CONTAINER=$(docker ps --format "{{.Names}}" | grep -i postgres | head -n 1)
        fi
    fi
    
    if [ -z "$DOCKER_CONTAINER" ]; then
        echo -e "${RED}Error: No PostgreSQL Docker container found${NC}"
        echo "Please specify a container name as the 5th argument, or ensure a PostgreSQL container is running."
        echo "Available containers:"
        docker ps --format "  {{.Names}} ({{.Image}})"
        exit 1
    fi
    
    # Verify container is running
    if ! docker ps --format "{{.Names}}" | grep -q "^${DOCKER_CONTAINER}$"; then
        echo -e "${RED}Error: Docker container '$DOCKER_CONTAINER' is not running${NC}"
        exit 1
    fi
    
    PSQL_CMD="docker exec -i $DOCKER_CONTAINER psql"
    echo -e "${BLUE}Using Docker container: $DOCKER_CONTAINER${NC}"
else
    echo -e "${RED}Error: Neither psql nor docker command found${NC}"
    echo "Please install PostgreSQL client tools or ensure Docker is available."
    exit 1
fi

echo -e "${GREEN}===========================================${NC}"
echo -e "${GREEN}ERP Base API - Database Setup${NC}"
echo -e "${GREEN}===========================================${NC}"
echo ""
echo "Database User: $DB_USER"
echo "Database Host: $DB_HOST"
echo "Database Port: $DB_PORT"
if [ "$USE_DOCKER" = true ]; then
    echo "Docker Container: $DOCKER_CONTAINER"
fi
echo ""

# Check if DDL file exists
DDL_FILE="../database/erp_base_db.sql"
if [ ! -f "$DDL_FILE" ]; then
    echo -e "${RED}Error: DDL file not found at $DDL_FILE${NC}"
    echo "Please ensure the database/erp_base_db.sql file exists."
    exit 1
fi

echo -e "${YELLOW}Creating database erp_base_db...${NC}"

# Set password for psql
export PGPASSWORD="$DB_PASSWORD"

# Create database (if it doesn't exist)
# When using Docker, connect to localhost inside container
if [ "$USE_DOCKER" = true ]; then
    DB_HOST_INTERNAL="localhost"
else
    DB_HOST_INTERNAL="$DB_HOST"
fi

$PSQL_CMD -h "$DB_HOST_INTERNAL" -p "$DB_PORT" -U "$DB_USER" -d postgres -tc "SELECT 1 FROM pg_database WHERE datname = 'erp_base_db'" | grep -q 1 || \
    $PSQL_CMD -h "$DB_HOST_INTERNAL" -p "$DB_PORT" -U "$DB_USER" -d postgres -c "CREATE DATABASE erp_base_db;"

if [ $? -eq 0 ]; then
    echo -e "${GREEN}✓ Database erp_base_db created successfully${NC}"
else
    echo -e "${RED}✗ Failed to create database${NC}"
    exit 1
fi

echo ""
echo -e "${YELLOW}Running DDL script to create tables...${NC}"

# Execute DDL script
# When using Docker, we need to pipe the file content
if [ "$USE_DOCKER" = true ]; then
    cat "$DDL_FILE" | $PSQL_CMD -h "$DB_HOST_INTERNAL" -p "$DB_PORT" -U "$DB_USER" -d erp_base_db
else
    $PSQL_CMD -h "$DB_HOST_INTERNAL" -p "$DB_PORT" -U "$DB_USER" -d erp_base_db -f "$DDL_FILE"
fi

if [ $? -eq 0 ]; then
    echo ""
    echo -e "${GREEN}===========================================${NC}"
    echo -e "${GREEN}✓ Database setup completed successfully!${NC}"
    echo -e "${GREEN}===========================================${NC}"
    echo ""
    echo "Next steps:"
    echo "1. Update your .env file with the correct database credentials"
    echo "2. Start the application: ./gradlew bootRun"
else
    echo -e "${RED}✗ Failed to execute DDL script${NC}"
    exit 1
fi

# Unset password
unset PGPASSWORD
