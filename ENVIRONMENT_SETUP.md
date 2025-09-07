# ERP Base API - Environment Configuration

## Overview
This project now uses environment variables for sensitive configuration data like database credentials, JWT secrets, and service URLs. This approach provides better security and flexibility across different environments.

## Files Structure

### Environment Files
- `.env` - Contains all environment variables (DO NOT COMMIT TO GIT)
- `.gitignore` - Excludes `.env` files from version control

### Configuration Files
- `application.properties` - Main Spring Boot configuration using environment variables
- `services-desarrollo.properties` - Development service URLs
- `services-consolidado.properties` - Production service URLs

## Setup Instructions

### 1. Configure Your Environment
Edit the `.env` file with your specific values:

```bash
# Database Configuration
DB_HOST=your-database-host
DB_PORT=5432
DB_NAME=your-database-name
DB_USERNAME=your-username
DB_PASSWORD=your-secure-password

# JWT Secret (CHANGE THIS!)
JWT_SECRET=your-super-secure-jwt-secret-key

# Environment
SPRING_PROFILES_ACTIVE=dev
```

### 3. Environment Variables Reference

#### Database Configuration
- `DB_HOST` - Database host (default: host.docker.internal)
- `DB_PORT` - Database port (default: 5432)
- `DB_NAME` - Database name (default: erp_double)
- `DB_USERNAME` - Database username (default: postgres)
- `DB_PASSWORD` - Database password (default: database123)
- `R2DBC_URL` - Complete R2DBC connection URL

#### Security Configuration
- `JWT_SECRET` - JWT signing secret (REQUIRED: Change in production!)
- `JWT_EXPIRATION` - Token expiration time in milliseconds

#### Service URLs
- `ADQUISICION_SERVICE_URL_DEV` - Development adquisicion service URL
- `ERP_BASE_API_SERVICE_URL_DEV` - Development ERP base API URL
- `INVENTARIO_SERVICE_URL_DEV` - Development inventory service URL
- `CONTABILIDAD_SERVICE_URL_DEV` - Development accounting service URL
- `VENTAS_SERVICE_URL_DEV` - Development sales service URL

#### Production URLs
- `ADQUISICION_SERVICE_URL_PROD` - Production adquisicion service URL
- `ERP_BASE_API_SERVICE_URL_PROD` - Production ERP base API URL
- `INVENTARIO_SERVICE_URL_PROD` - Production inventory service URL
- `CONTABILIDAD_SERVICE_URL_PROD` - Production accounting service URL
- `VENTAS_SERVICE_URL_PROD` - Production sales service URL

## Running the Application

### Development
```bash
# Set environment to development
SPRING_PROFILES_ACTIVE=dev

# Run the application
./gradlew bootRun
```

### Production
```bash
# Set environment to production
SPRING_PROFILES_ACTIVE=prod

# Run the application
./gradlew bootRun
```

## Security Best Practices

### 1. Never Commit Sensitive Data
- ✅ `.env` files are excluded from git
- ✅ Use `env.example` as a template
- ✅ Keep production secrets secure

### 2. Use Strong Secrets
- Generate strong JWT secrets (minimum 32 characters)
- Use complex database passwords
- Rotate secrets regularly

### 3. Environment Separation
- Use different `.env` files for different environments
- Consider using a secrets management system for production
- Never use development secrets in production

## Docker Support

For Docker deployments, you can pass environment variables:

```bash
docker run -e DB_HOST=production-db \
           -e DB_PASSWORD=secure-password \
           -e JWT_SECRET=production-secret \
           your-app-image
```

## Troubleshooting

### Common Issues

1. **Application won't start**
   - Check if `.env` file exists
   - Verify all required environment variables are set
   - Check database connectivity

2. **Database connection errors**
   - Verify `DB_HOST`, `DB_PORT`, `DB_NAME` values
   - Check database credentials
   - Ensure database is running

3. **JWT token issues**
   - Verify `JWT_SECRET` is set
   - Ensure secret is consistent across restarts

### Debug Mode
Enable debug logging by setting:
```bash
LOGGING_LEVEL_ERP_BASE_API=DEBUG
```

## Migration from Hardcoded Values

If you're migrating from hardcoded values:

1. Copy values from old configuration files to `.env`
2. Update configuration files to use environment variables
3. Test the application with new configuration
4. Remove hardcoded values from version control

## Support

For issues or questions about environment configuration, please check:
- Spring Boot documentation on externalized configuration
- Environment variable best practices
- Security guidelines for sensitive data
