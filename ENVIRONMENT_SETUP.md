# Environment Setup Instructions

## Required Environment Variables

This application requires the following environment variables to be set. No default values are provided for security reasons.

### Quick Setup

1. Copy the `environment.template` file to `.env`:
   ```bash
   cp environment.template .env
   ```

2. Modify the values in `.env` according to your environment

3. Run the application:
   ```bash
   ./gradlew bootRun
   ```

### Environment Variables Required

#### Server Configuration
- `SERVER_PORT` - Port number for the application server

#### Database Configuration
- `DB_USERNAME` - Database username
- `DB_PASSWORD` - Database password  
- `R2DBC_URL` - R2DBC connection URL
- `DB_SCHEMA` - Database schema name

#### Database Connection Pool
- `DB_POOL_MAX_IDLE_TIME` - Maximum idle time for connections
- `DB_POOL_INITIAL_SIZE` - Initial number of connections
- `DB_POOL_MAX_SIZE` - Maximum number of connections

#### Security
- `JWT_SECRET` - Secret key for JWT token generation

#### Logging
- `LOGGING_LEVEL_ROOT` - Root logging level (DEBUG, INFO, WARN, ERROR)
- `LOGGING_LEVEL_ERP_BASE_API` - Application logging level

#### Management Endpoints
- `MANAGEMENT_ENDPOINTS_WEB_EXPOSURE_INCLUDE` - Comma-separated list of endpoints
- `MANAGEMENT_ENDPOINT_HEALTH_SHOW_DETAILS` - Health endpoint details policy

#### File Upload
- `MAX_FILE_SIZE` - Maximum file size (e.g., 10MB)
- `MAX_REQUEST_SIZE` - Maximum request size (e.g., 10MB)

#### Spring Configuration
- `SPRING_PROFILES_ACTIVE` - Active Spring profile (dev, prod, etc.)

#### Service URLs (Development)
- `ADQUISICION_SERVICE_URL_DEV` - Adquisicion service URL for development
- `ERP_BASE_API_SERVICE_URL_DEV` - ERP Base API service URL for development
- `INVENTARIO_SERVICE_URL_DEV` - Inventario service URL for development
- `CONTABILIDAD_SERVICE_URL_DEV` - Contabilidad service URL for development
- `VENTAS_SERVICE_URL_DEV` - Ventas service URL for development

#### Service URLs (Production)
- `ADQUISICION_SERVICE_URL_PROD` - Adquisicion service URL for production
- `ERP_BASE_API_SERVICE_URL_PROD` - ERP Base API service URL for production
- `INVENTARIO_SERVICE_URL_PROD` - Inventario service URL for production
- `CONTABILIDAD_SERVICE_URL_PROD` - Contabilidad service URL for production
- `VENTAS_SERVICE_URL_PROD` - Ventas service URL for production

### Security Note

The `.env` file is ignored by git for security reasons. Never commit sensitive information like passwords or secrets to version control.