#!/bin/bash
# ===========================================
# Start ERP Base API
# ===========================================
# Ensures postgres-dev container is running, then runs the application.
#
# Usage: ./scripts/start_app.sh
#        Or from project root: ./scripts/start_app.sh

set -e

CONTAINER_NAME="postgres-dev"
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"

# Colors
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m'

cd "$PROJECT_ROOT"

# Check if Docker is available
if ! command -v docker &> /dev/null; then
    echo -e "${RED}Error: Docker is not installed or not in PATH${NC}"
    exit 1
fi

# Check if postgres-dev container is running
if docker ps --format '{{.Names}}' | grep -q "^${CONTAINER_NAME}$"; then
    echo -e "${GREEN}Container '$CONTAINER_NAME' is already running.${NC}"
else
    # Container exists but is stopped?
    if docker ps -a --format '{{.Names}}' | grep -q "^${CONTAINER_NAME}$"; then
        echo -e "${YELLOW}Starting existing container '$CONTAINER_NAME'...${NC}"
        docker start "$CONTAINER_NAME"
    else
        echo -e "${YELLOW}Container '$CONTAINER_NAME' not found. Creating and starting it...${NC}"
        # Load .env for DB_PASSWORD if present
        if [ -f .env ]; then
            set -a
            source .env
            set +a
        fi
        docker run -d \
            --name "$CONTAINER_NAME" \
            -e POSTGRES_USER=postgres \
            -e POSTGRES_PASSWORD="${DB_PASSWORD:-postgres}" \
            -e POSTGRES_DB=erp_base_db \
            -p 5432:5432 \
            postgres:16-alpine
    fi
    echo "Waiting for PostgreSQL to be ready..."
    sleep 3
fi

echo -e "${GREEN}Starting application...${NC}"
./gradlew bootRun
