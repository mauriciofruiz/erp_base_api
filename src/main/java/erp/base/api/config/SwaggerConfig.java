package erp.base.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Swagger/OpenAPI documentation
 */
@Configuration
public class SwaggerConfig {

    /**
     * Custom OpenAPI configuration with JWT authentication support and manual path definitions
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ERP Base API")
                        .version("1.0.0")
                        .description("ERP Base API Documentation - Spring WebFlux"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("JWT token authentication")))
                .addTagsItem(new Tag().name("Authentication").description("Authentication endpoints"))
                .addTagsItem(new Tag().name("Person Management").description("Person management endpoints"))
                .addTagsItem(new Tag().name("Employee Management").description("Employee management endpoints"))
                .addTagsItem(new Tag().name("Provider Management").description("Provider management endpoints"))
                .addTagsItem(new Tag().name("Client Management").description("Client management endpoints"))
                .addTagsItem(new Tag().name("User Management").description("User management endpoints"))
                .addTagsItem(new Tag().name("Menu Management").description("Menu management endpoints"))
                .addTagsItem(new Tag().name("Project Management").description("Project management endpoints"))
                .addTagsItem(new Tag().name("Role Management").description("Role management endpoints"))
                .addTagsItem(new Tag().name("Department Management").description("Department management endpoints"))
                // Manual path definitions for functional routes
                // Authentication
                .path("/login", createLoginPath())
                .path("/logout", createLogoutPath())
                .path("/estado-sesion", createSessionStatePath())
                
                // Person Management
                .path("/persona", createPersonPath())
                .path("/persona/{id}", createPersonByIdPath())
                .path("/persona/proveedor/{id}", createPersonProviderPath())
                .path("/proveedor/persona", createAllPersonProvidersPath())
                .path("/persona/cliente/{id}", createPersonClientPath())
                .path("/cliente/persona/ficha", createAllPersonClientsPath())
                
                // Employee Management
                .path("/empleado", createEmployeePath())
                .path("/empleado/update/{id}", createEmployeeUpdatePath())
                .path("/empleado/informacion-contractual/{id}", createEmployeeContractualPath())
                .path("/empleado/datos-contables/{id}", createEmployeeAccountingPath())
                .path("/empleado/activo", createEmployeeActivePath())
                .path("/empleado/persona", createEmployeePersonPath())
                
                // Provider Management
                .path("/proveedor", createProviderPath())
                .path("/proveedor/{id}", createProviderByIdPath())
                .path("/proveedor/por-parametro/{id}", createProviderByParameterPath())
                .path("/proveedor/solicitud-compra", createProviderPurchaseRequestPath())
                .path("/proveedor/lista-pedido", createProviderOrderListPath())
                .path("/proveedor/importacion/{id}", createProviderImportPath())
                .path("/proveedor/verificar-existencia/{id}", createProviderVerifyExistencePath())
                .path("/proveedor/transportista", createProviderCarriersPath())
                
                // Client Management
                .path("/cliente", createClientPath())
                .path("/cliente/{id}", createClientByIdPath())
                .path("/cliente/verificar-existencia/{id}", createClientVerifyExistencePath())
                
                // User Management
                .path("/usuario", createUserPath())
                .path("/usuario/{id}", createUserByIdPath())
                .path("/usuario/solicitud-compra", createUserPurchaseRequestPath())
                .path("/usuario/orden-compra", createUserPurchaseOrderPath())
                .path("/usuario/venta", createUserSalePath())
                
                // Menu Management
                .path("/menu", createMenuPath())
                .path("/menu/{id}", createMenuByIdPath())
                
                // Project Management
                .path("/proyecto", createProjectPath())
                
                // Role Management
                .path("/rol/{id}", createRoleByIdPath())
                
                // Department Management
                .path("/departamento", createDepartmentPath())
                .path("/departamento/{id}", createDepartmentByIdPath());
    }

    private PathItem createLoginPath() {
        return new PathItem()
                .post(new Operation()
                        .tags(java.util.Arrays.asList("Authentication"))
                        .summary("User login")
                        .description("Authenticates a user and returns a JWT token")
                        .requestBody(new RequestBody()
                                .content(new Content()
                                        .addMediaType("application/json", new MediaType()
                                                .schema(new Schema<>().type("object")))))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse()
                                        .description("Login successful")
                                        .content(new Content()
                                                .addMediaType("application/json", new MediaType()
                                                        .schema(new Schema<>().type("object")))))
                                .addApiResponse("401", new ApiResponse().description("Invalid credentials"))
                                .addApiResponse("400", new ApiResponse().description("Invalid request body"))));
    }

    private PathItem createLogoutPath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Authentication"))
                        .summary("User logout")
                        .description("Logs out the current user and invalidates the JWT token")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Logout successful"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized - Invalid token"))));
    }

    private PathItem createSessionStatePath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Authentication"))
                        .summary("Session state")
                        .description("Checks the current session state")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Session state retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized - Invalid token"))));
    }

    private PathItem createPersonPath() {
        return new PathItem()
                .post(new Operation()
                        .tags(java.util.Arrays.asList("Person Management"))
                        .summary("Create person")
                        .description("Creates a new person")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .requestBody(new RequestBody()
                                .content(new Content()
                                        .addMediaType("application/json", new MediaType()
                                                .schema(new Schema<>().type("object")))))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Person created successfully"))
                                .addApiResponse("400", new ApiResponse().description("Invalid input data"))
                                .addApiResponse("500", new ApiResponse().description("Internal server error"))))
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Person Management"))
                        .summary("Get all people")
                        .description("Retrieves all people with pagination")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("People retrieved successfully"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createPersonByIdPath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Person Management"))
                        .summary("Get person by ID")
                        .description("Retrieves a person by their ID")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("integer")))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Person found"))
                                .addApiResponse("404", new ApiResponse().description("Person not found"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))))
                .put(new Operation()
                        .tags(java.util.Arrays.asList("Person Management"))
                        .summary("Update person")
                        .description("Updates an existing person by ID")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("integer")))
                        .requestBody(new RequestBody()
                                .content(new Content()
                                        .addMediaType("application/json", new MediaType()
                                                .schema(new Schema<>().type("object")))))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Person updated successfully"))
                                .addApiResponse("400", new ApiResponse().description("Invalid input data"))
                                .addApiResponse("404", new ApiResponse().description("Person not found"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createEmployeePath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Employee Management"))
                        .summary("Get employee ficha")
                        .description("Retrieves employee ficha information")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Employee ficha retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))))
                .post(new Operation()
                        .tags(java.util.Arrays.asList("Employee Management"))
                        .summary("Create employee")
                        .description("Creates a new employee")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .requestBody(new RequestBody()
                                .content(new Content()
                                        .addMediaType("application/json", new MediaType()
                                                .schema(new Schema<>().type("object")))))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Employee created successfully"))
                                .addApiResponse("400", new ApiResponse().description("Invalid input data"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }


    // Additional Person Management paths
    private PathItem createPersonProviderPath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Person Management"))
                        .summary("Get person providers by provider ID")
                        .description("Retrieves person providers by provider ID")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("integer")))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Person providers retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createAllPersonProvidersPath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Person Management"))
                        .summary("Get all person providers")
                        .description("Retrieves all person providers")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("All person providers retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createPersonClientPath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Person Management"))
                        .summary("Get person client by provider ID")
                        .description("Retrieves person client by provider ID")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("integer")))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Person client retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createAllPersonClientsPath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Person Management"))
                        .summary("Get all person clients")
                        .description("Retrieves all person clients")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("All person clients retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    // Additional Employee Management paths
    private PathItem createEmployeeUpdatePath() {
        return new PathItem()
                .put(new Operation()
                        .tags(java.util.Arrays.asList("Employee Management"))
                        .summary("Update employee information")
                        .description("Updates employee information by ID")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("integer")))
                        .requestBody(new RequestBody()
                                .content(new Content()
                                        .addMediaType("application/json", new MediaType()
                                                .schema(new Schema<>().type("object")))))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Employee updated successfully"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createEmployeeContractualPath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Employee Management"))
                        .summary("Get employee contractual information")
                        .description("Retrieves employee contractual information by ID")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("integer")))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Employee contractual information retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))))
                .put(new Operation()
                        .tags(java.util.Arrays.asList("Employee Management"))
                        .summary("Update employee contractual information")
                        .description("Updates employee contractual information by ID")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("integer")))
                        .requestBody(new RequestBody()
                                .content(new Content()
                                        .addMediaType("application/json", new MediaType()
                                                .schema(new Schema<>().type("object")))))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Employee contractual information updated"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createEmployeeAccountingPath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Employee Management"))
                        .summary("Get employee accounting data")
                        .description("Retrieves employee accounting data by ID")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("integer")))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Employee accounting data retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createEmployeeActivePath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Employee Management"))
                        .summary("Get all active employees")
                        .description("Retrieves all active employees")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Active employees retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createEmployeePersonPath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Employee Management"))
                        .summary("Get employee person by ID")
                        .description("Retrieves employee person information by ID")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Employee person information retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    // Provider Management paths
    private PathItem createProviderPath() {
        return new PathItem()
                .post(new Operation()
                        .tags(java.util.Arrays.asList("Provider Management"))
                        .summary("Create provider")
                        .description("Creates a new provider")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .requestBody(new RequestBody()
                                .content(new Content()
                                        .addMediaType("application/json", new MediaType()
                                                .schema(new Schema<>().type("object")))))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Provider created successfully"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))))
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Provider Management"))
                        .summary("Get all providers")
                        .description("Retrieves all providers")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Providers retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createProviderByIdPath() {
        return new PathItem()
                .put(new Operation()
                        .tags(java.util.Arrays.asList("Provider Management"))
                        .summary("Update provider")
                        .description("Updates an existing provider by ID")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("integer")))
                        .requestBody(new RequestBody()
                                .content(new Content()
                                        .addMediaType("application/json", new MediaType()
                                                .schema(new Schema<>().type("object")))))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Provider updated successfully"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))))
                .patch(new Operation()
                        .tags(java.util.Arrays.asList("Provider Management"))
                        .summary("Patch provider")
                        .description("Partially updates a provider by ID")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("integer")))
                        .requestBody(new RequestBody()
                                .content(new Content()
                                        .addMediaType("application/json", new MediaType()
                                                .schema(new Schema<>().type("object")))))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Provider patched successfully"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    // Additional Provider paths (simplified for brevity)
    private PathItem createProviderByParameterPath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Provider Management"))
                        .summary("Get provider by parameter")
                        .description("Retrieves provider by parameter")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("integer")))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Provider retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createProviderPurchaseRequestPath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Provider Management"))
                        .summary("Get providers for purchase requests")
                        .description("Retrieves providers for pending purchase requests")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Providers retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createProviderOrderListPath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Provider Management"))
                        .summary("Get providers by approved order list")
                        .description("Retrieves providers by approved order list")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Providers retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createProviderImportPath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Provider Management"))
                        .summary("Get provider for import")
                        .description("Retrieves provider data for import by ID")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("integer")))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Provider import data retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createProviderVerifyExistencePath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Provider Management"))
                        .summary("Verify provider existence")
                        .description("Verifies if provider exists by identification")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("string")))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Provider existence verified"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createProviderCarriersPath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Provider Management"))
                        .summary("Get carriers")
                        .description("Retrieves all carriers")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Carriers retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    // Client Management paths
    private PathItem createClientPath() {
        return new PathItem()
                .post(new Operation()
                        .tags(java.util.Arrays.asList("Client Management"))
                        .summary("Create client")
                        .description("Creates a new client")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .requestBody(new RequestBody()
                                .content(new Content()
                                        .addMediaType("application/json", new MediaType()
                                                .schema(new Schema<>().type("object")))))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Client created successfully"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))))
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Client Management"))
                        .summary("Get clients by parameters")
                        .description("Retrieves clients by search parameters")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Clients retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createClientByIdPath() {
        return new PathItem()
                .put(new Operation()
                        .tags(java.util.Arrays.asList("Client Management"))
                        .summary("Update client")
                        .description("Updates an existing client by ID")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("integer")))
                        .requestBody(new RequestBody()
                                .content(new Content()
                                        .addMediaType("application/json", new MediaType()
                                                .schema(new Schema<>().type("object")))))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Client updated successfully"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))))
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Client Management"))
                        .summary("Get client by ID")
                        .description("Retrieves a client by ID")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("integer")))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Client retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createClientVerifyExistencePath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Client Management"))
                        .summary("Verify client existence")
                        .description("Verifies if client exists by identification")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("string")))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Client existence verified"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    // User Management paths
    private PathItem createUserPath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("User Management"))
                        .summary("Get users by search parameters")
                        .description("Retrieves users by search parameters")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Users retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))))
                .put(new Operation()
                        .tags(java.util.Arrays.asList("User Management"))
                        .summary("Forgot password")
                        .description("Handles forgot password request")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .requestBody(new RequestBody()
                                .content(new Content()
                                        .addMediaType("application/json", new MediaType()
                                                .schema(new Schema<>().type("object")))))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Password reset initiated"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createUserByIdPath() {
        return new PathItem()
                .put(new Operation()
                        .tags(java.util.Arrays.asList("User Management"))
                        .summary("Update user")
                        .description("Updates an existing user by ID")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("integer")))
                        .requestBody(new RequestBody()
                                .content(new Content()
                                        .addMediaType("application/json", new MediaType()
                                                .schema(new Schema<>().type("object")))))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("User updated successfully"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))))
                .get(new Operation()
                        .tags(java.util.Arrays.asList("User Management"))
                        .summary("Get user by ID")
                        .description("Retrieves a user by ID")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("integer")))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("User retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createUserPurchaseRequestPath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("User Management"))
                        .summary("Get users for purchase requests")
                        .description("Retrieves users authorized for purchase requests")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Users retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createUserPurchaseOrderPath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("User Management"))
                        .summary("Get users for purchase orders")
                        .description("Retrieves users authorized for purchase orders")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Users retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createUserSalePath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("User Management"))
                        .summary("Get sales users")
                        .description("Retrieves users authorized for sales")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Sales users retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    // Menu Management paths
    private PathItem createMenuPath() {
        return new PathItem()
                .post(new Operation()
                        .tags(java.util.Arrays.asList("Menu Management"))
                        .summary("Create menu")
                        .description("Creates a new menu item")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .requestBody(new RequestBody()
                                .content(new Content()
                                        .addMediaType("application/json", new MediaType()
                                                .schema(new Schema<>().type("object")))))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Menu created successfully"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))))
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Menu Management"))
                        .summary("Get menus by search parameters")
                        .description("Retrieves menus by search parameters")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Menus retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createMenuByIdPath() {
        return new PathItem()
                .put(new Operation()
                        .tags(java.util.Arrays.asList("Menu Management"))
                        .summary("Update menu")
                        .description("Updates an existing menu by ID")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("integer")))
                        .requestBody(new RequestBody()
                                .content(new Content()
                                        .addMediaType("application/json", new MediaType()
                                                .schema(new Schema<>().type("object")))))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Menu updated successfully"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))))
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Menu Management"))
                        .summary("Get menu by ID")
                        .description("Retrieves a menu by ID")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("integer")))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Menu retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    // Project Management paths
    private PathItem createProjectPath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Project Management"))
                        .summary("Get all active projects")
                        .description("Retrieves all active projects")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Active projects retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    // Role Management paths
    private PathItem createRoleByIdPath() {
        return new PathItem()
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Role Management"))
                        .summary("Get role by ID")
                        .description("Retrieves a role by ID")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("integer")))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Role retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    // Department Management paths
    private PathItem createDepartmentPath() {
        return new PathItem()
                .post(new Operation()
                        .tags(java.util.Arrays.asList("Department Management"))
                        .summary("Create department")
                        .description("Creates a new department")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .requestBody(new RequestBody()
                                .content(new Content()
                                        .addMediaType("application/json", new MediaType()
                                                .schema(new Schema<>().type("object")))))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Department created successfully"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))))
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Department Management"))
                        .summary("Get departments by parameters")
                        .description("Retrieves departments by search parameters")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Departments retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }

    private PathItem createDepartmentByIdPath() {
        return new PathItem()
                .put(new Operation()
                        .tags(java.util.Arrays.asList("Department Management"))
                        .summary("Update department")
                        .description("Updates an existing department by ID")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("integer")))
                        .requestBody(new RequestBody()
                                .content(new Content()
                                        .addMediaType("application/json", new MediaType()
                                                .schema(new Schema<>().type("object")))))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Department updated successfully"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))))
                .get(new Operation()
                        .tags(java.util.Arrays.asList("Department Management"))
                        .summary("Get department by ID")
                        .description("Retrieves a department by ID")
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .addParametersItem(new Parameter()
                                .name("id")
                                .in("path")
                                .required(true)
                                .schema(new Schema<>().type("integer")))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Department retrieved"))
                                .addApiResponse("401", new ApiResponse().description("Unauthorized"))));
    }
}
