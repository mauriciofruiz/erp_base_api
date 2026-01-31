# Database Setup Guide for erp_base_api

This guide explains how to set up the `erp_base_db` database for the `erp_base_api` microservice.

## Prerequisites

- PostgreSQL installed and running
- Access to PostgreSQL with privileges to create databases
- The DDL script located at `../database/erp_base_db.sql`

## Quick Setup

### Option 1: Using the Setup Script (Recommended)

1. Navigate to the `erp_base_api` directory:
   ```bash
   cd erp_base_api
   ```

2. Run the setup script:
   ```bash
   ./setup_database.sh [postgres_user] [postgres_password] [host] [port]
   ```

   Example:
   ```bash
   ./setup_database.sh postgres mypassword localhost 5432
   ```

   If using default values (postgres/postgres/localhost/5432):
   ```bash
   ./setup_database.sh
   ```

### Option 2: Manual Setup

1. Connect to PostgreSQL:
   ```bash
   psql -U postgres -h localhost
   ```

2. Create the database:
   ```sql
   CREATE DATABASE erp_base_db;
   ```

3. Exit psql and run the DDL script:
   ```bash
   psql -U postgres -h localhost -d erp_base_db -f ../database/erp_base_db.sql
   ```

## Configuration

### Environment Variables

Ensure your `.env` file (copied from `environment.template`) has the correct database configuration:

```bash
DB_USERNAME=postgres
DB_PASSWORD=your_password
R2DBC_URL=r2dbc:postgresql://localhost:5432/erp_base_db?currentSchema=public
DB_SCHEMA=public
```

### Application Properties

The `application.properties` file is already configured with:
- Database: `erp_base_db`
- Schema: `public`
- Default connection URL: `r2dbc:postgresql://localhost:5432/erp_base_db?currentSchema=public`

## Verification

After setup, verify the database was created correctly:

```bash
psql -U postgres -h localhost -d erp_base_db -c "\dt"
```

This should list all tables in the database (approximately 124 tables).

## Troubleshooting

### Database Already Exists

If the database already exists, the setup script will skip creation and proceed with table creation. If you need to recreate the database:

```sql
DROP DATABASE erp_base_db;
CREATE DATABASE erp_base_db;
```

Then run the setup script again.

### Connection Issues

- Verify PostgreSQL is running: `pg_isready -h localhost -p 5432`
- Check credentials in your `.env` file
- Ensure the database host and port are correct

### Permission Errors

Ensure your PostgreSQL user has the necessary privileges:
```sql
ALTER USER postgres WITH CREATEDB;
```

## Database Schema

The `erp_base_db` database uses the `public` schema (default PostgreSQL schema). All tables are created without prefixes (e.g., `PERSONA`, `USUARIO`, `SUCURSAL`).

## Next Steps

1. ✅ Database created and tables initialized
2. Configure your `.env` file with correct credentials
3. Start the application: `./gradlew bootRun`
4. Test the API endpoints to verify database connectivity

## Migration from erp_double

If you're migrating from the old `erp_double` database, refer to the `../database/MIGRATION_GUIDE.md` for detailed migration instructions.
