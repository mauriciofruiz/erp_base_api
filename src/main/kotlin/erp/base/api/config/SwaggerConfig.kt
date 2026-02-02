package erp.base.api.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.PathItem
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.media.Content
import io.swagger.v3.oas.models.media.MediaType
import io.swagger.v3.oas.models.media.Schema
import io.swagger.v3.oas.models.parameters.Parameter
import io.swagger.v3.oas.models.parameters.RequestBody
import io.swagger.v3.oas.models.responses.ApiResponse
import io.swagger.v3.oas.models.responses.ApiResponses
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.tags.Tag
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun customOpenAPI(): OpenAPI =
        OpenAPI()
            .info(
                Info()
                    .title("ERP Base API")
                    .version("1.0.0")
                    .description("ERP Base API Documentation - Spring WebFlux"),
            )
            .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
            .components(
                Components()
                    .addSecuritySchemes(
                        "bearerAuth",
                        SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                            .description("JWT token authentication"),
                    ),
            )
            .addTagsItem(Tag().name("Authentication").description("Authentication endpoints"))
            .addTagsItem(Tag().name("Person Management").description("Person management endpoints"))
            .addTagsItem(Tag().name("Employee Management").description("Employee management endpoints"))
            .addTagsItem(Tag().name("Provider Management").description("Provider management endpoints"))
            .addTagsItem(Tag().name("Client Management").description("Client management endpoints"))
            .addTagsItem(Tag().name("User Management").description("User management endpoints"))
            .addTagsItem(Tag().name("Menu Management").description("Menu management endpoints"))
            .addTagsItem(Tag().name("Project Management").description("Project management endpoints"))
            .addTagsItem(Tag().name("Role Management").description("Role management endpoints"))
            .addTagsItem(Tag().name("Department Management").description("Department management endpoints"))
            .path("/login", createLoginPath())
            .path("/logout", createLogoutPath())
            .path("/estado-sesion", createSessionStatePath())
            .path("/persona", createPersonPath())
            .path("/persona/{id}", createPersonByIdPath())
            .path("/persona/proveedor/{id}", createPersonProviderPath())
            .path("/proveedor/persona", createAllPersonProvidersPath())
            .path("/persona/cliente/{id}", createPersonClientPath())
            .path("/cliente/persona/ficha", createAllPersonClientsPath())
            .path("/empleado", createEmployeePath())
            .path("/empleado/update/{id}", createEmployeeUpdatePath())
            .path("/empleado/informacion-contractual/{id}", createEmployeeContractualPath())
            .path("/empleado/datos-contables/{id}", createEmployeeAccountingPath())
            .path("/empleado/activo", createEmployeeActivePath())
            .path("/empleado/persona", createEmployeePersonPath())
            .path("/proveedor", createProviderPath())
            .path("/proveedor/{id}", createProviderByIdPath())
            .path("/proveedor/por-parametro/{id}", createProviderByParameterPath())
            .path("/proveedor/solicitud-compra", createProviderPurchaseRequestPath())
            .path("/proveedor/lista-pedido", createProviderOrderListPath())
            .path("/proveedor/importacion/{id}", createProviderImportPath())
            .path("/proveedor/verificar-existencia/{id}", createProviderVerifyExistencePath())
            .path("/proveedor/transportista", createProviderCarriersPath())
            .path("/cliente", createClientPath())
            .path("/cliente/{id}", createClientByIdPath())
            .path("/cliente/verificar-existencia/{id}", createClientVerifyExistencePath())
            .path("/usuario", createUserPath())
            .path("/usuario/{id}", createUserByIdPath())
            .path("/usuario/solicitud-compra", createUserPurchaseRequestPath())
            .path("/usuario/orden-compra", createUserPurchaseOrderPath())
            .path("/usuario/venta", createUserSalePath())
            .path("/menu", createMenuPath())
            .path("/menu/{id}", createMenuByIdPath())
            .path("/proyecto", createProjectPath())
            .path("/rol/{id}", createRoleByIdPath())
            .path("/departamento", createDepartmentPath())
            .path("/departamento/{id}", createDepartmentByIdPath())

    private fun createLoginPath(): PathItem {
        val loginRequestSchema = Schema<Any>()
            .type("object")
            .required(listOf("nick", "password"))
            .addProperty("nick", Schema<Any>().type("string").description("User nickname / username"))
            .addProperty("password", Schema<Any>().type("string").description("User password"))
            .example(mapOf("nick" to "admin", "password" to "your_password"))
        return PathItem()
            .post(
                Operation()
                    .tags(listOf("Authentication"))
                    .summary("User login")
                    .description("Authenticates a user and returns a JWT token")
                    .requestBody(
                        RequestBody()
                            .description("Login credentials")
                            .required(true)
                            .content(
                                Content()
                                    .addMediaType(
                                        "application/json",
                                        MediaType()
                                            .schema(loginRequestSchema)
                                            .example(mapOf("nick" to "admin", "password" to "your_password")),
                                    ),
                            ),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse(
                                "200",
                                ApiResponse()
                                    .description("Login successful")
                                    .content(
                                        Content()
                                            .addMediaType(
                                                "application/json",
                                                MediaType().schema(Schema<Any>().type("object")),
                                            ),
                                    ),
                            )
                            .addApiResponse("401", ApiResponse().description("Invalid credentials"))
                            .addApiResponse("400", ApiResponse().description("Invalid request body")),
                    ),
            )
    }

    private fun createLogoutPath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("Authentication"))
                    .summary("User logout")
                    .description("Logs out the current user and invalidates the JWT token")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Logout successful"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized - Invalid token")),
                    ),
            )

    private fun createSessionStatePath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("Authentication"))
                    .summary("Session state")
                    .description("Checks the current session state")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Session state retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized - Invalid token")),
                    ),
            )

    private fun createPersonPath(): PathItem =
        PathItem()
            .post(
                Operation()
                    .tags(listOf("Person Management"))
                    .summary("Create person")
                    .description("Creates a new person")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .requestBody(
                        RequestBody()
                            .content(
                                Content()
                                    .addMediaType(
                                        "application/json",
                                        MediaType().schema(Schema<Any>().type("object")),
                                    ),
                            ),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Person created successfully"))
                            .addApiResponse("400", ApiResponse().description("Invalid input data"))
                            .addApiResponse("500", ApiResponse().description("Internal server error")),
                    ),
            )
            .get(
                Operation()
                    .tags(listOf("Person Management"))
                    .summary("Get all people")
                    .description("Retrieves all people with pagination")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("People retrieved successfully"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createPersonByIdPath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("Person Management"))
                    .summary("Get person by ID")
                    .description("Retrieves a person by their ID")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("integer")),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Person found"))
                            .addApiResponse("404", ApiResponse().description("Person not found"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )
            .put(
                Operation()
                    .tags(listOf("Person Management"))
                    .summary("Update person")
                    .description("Updates an existing person by ID")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("integer")),
                    )
                    .requestBody(
                        RequestBody()
                            .content(
                                Content()
                                    .addMediaType(
                                        "application/json",
                                        MediaType().schema(Schema<Any>().type("object")),
                                    ),
                            ),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Person updated successfully"))
                            .addApiResponse("400", ApiResponse().description("Invalid input data"))
                            .addApiResponse("404", ApiResponse().description("Person not found"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createEmployeePath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("Employee Management"))
                    .summary("Get employee ficha")
                    .description("Retrieves employee ficha information")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Employee ficha retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )
            .post(
                Operation()
                    .tags(listOf("Employee Management"))
                    .summary("Create employee")
                    .description("Creates a new employee")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .requestBody(
                        RequestBody()
                            .content(
                                Content()
                                    .addMediaType(
                                        "application/json",
                                        MediaType().schema(Schema<Any>().type("object")),
                                    ),
                            ),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Employee created successfully"))
                            .addApiResponse("400", ApiResponse().description("Invalid input data"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createPersonProviderPath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("Person Management"))
                    .summary("Get person providers by provider ID")
                    .description("Retrieves person providers by provider ID")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("integer")),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Person providers retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createAllPersonProvidersPath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("Person Management"))
                    .summary("Get all person providers")
                    .description("Retrieves all person providers")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("All person providers retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createPersonClientPath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("Person Management"))
                    .summary("Get person client by provider ID")
                    .description("Retrieves person client by provider ID")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("integer")),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Person client retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createAllPersonClientsPath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("Person Management"))
                    .summary("Get all person clients")
                    .description("Retrieves all person clients")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("All person clients retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createEmployeeUpdatePath(): PathItem =
        PathItem()
            .put(
                Operation()
                    .tags(listOf("Employee Management"))
                    .summary("Update employee information")
                    .description("Updates employee information by ID")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("integer")),
                    )
                    .requestBody(
                        RequestBody()
                            .content(
                                Content()
                                    .addMediaType(
                                        "application/json",
                                        MediaType().schema(Schema<Any>().type("object")),
                                    ),
                            ),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Employee updated successfully"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createEmployeeContractualPath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("Employee Management"))
                    .summary("Get employee contractual information")
                    .description("Retrieves employee contractual information by ID")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("integer")),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Employee contractual information retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )
            .put(
                Operation()
                    .tags(listOf("Employee Management"))
                    .summary("Update employee contractual information")
                    .description("Updates employee contractual information by ID")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("integer")),
                    )
                    .requestBody(
                        RequestBody()
                            .content(
                                Content()
                                    .addMediaType(
                                        "application/json",
                                        MediaType().schema(Schema<Any>().type("object")),
                                    ),
                            ),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Employee contractual information updated"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createEmployeeAccountingPath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("Employee Management"))
                    .summary("Get employee accounting data")
                    .description("Retrieves employee accounting data by ID")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("integer")),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Employee accounting data retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createEmployeeActivePath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("Employee Management"))
                    .summary("Get all active employees")
                    .description("Retrieves all active employees")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Active employees retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createEmployeePersonPath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("Employee Management"))
                    .summary("Get employee person by ID")
                    .description("Retrieves employee person information by ID")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Employee person information retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createProviderPath(): PathItem =
        PathItem()
            .post(
                Operation()
                    .tags(listOf("Provider Management"))
                    .summary("Create provider")
                    .description("Creates a new provider")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .requestBody(
                        RequestBody()
                            .content(
                                Content()
                                    .addMediaType(
                                        "application/json",
                                        MediaType().schema(Schema<Any>().type("object")),
                                    ),
                            ),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Provider created successfully"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )
            .get(
                Operation()
                    .tags(listOf("Provider Management"))
                    .summary("Get all providers")
                    .description("Retrieves all providers")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Providers retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createProviderByIdPath(): PathItem =
        PathItem()
            .put(
                Operation()
                    .tags(listOf("Provider Management"))
                    .summary("Update provider")
                    .description("Updates an existing provider by ID")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("integer")),
                    )
                    .requestBody(
                        RequestBody()
                            .content(
                                Content()
                                    .addMediaType(
                                        "application/json",
                                        MediaType().schema(Schema<Any>().type("object")),
                                    ),
                            ),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Provider updated successfully"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )
            .patch(
                Operation()
                    .tags(listOf("Provider Management"))
                    .summary("Patch provider")
                    .description("Partially updates a provider by ID")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("integer")),
                    )
                    .requestBody(
                        RequestBody()
                            .content(
                                Content()
                                    .addMediaType(
                                        "application/json",
                                        MediaType().schema(Schema<Any>().type("object")),
                                    ),
                            ),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Provider patched successfully"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createProviderByParameterPath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("Provider Management"))
                    .summary("Get provider by parameter")
                    .description("Retrieves provider by parameter")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("integer")),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Provider retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createProviderPurchaseRequestPath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("Provider Management"))
                    .summary("Get providers for purchase requests")
                    .description("Retrieves providers for pending purchase requests")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Providers retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createProviderOrderListPath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("Provider Management"))
                    .summary("Get providers by approved order list")
                    .description("Retrieves providers by approved order list")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Providers retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createProviderImportPath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("Provider Management"))
                    .summary("Get provider for import")
                    .description("Retrieves provider data for import by ID")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("integer")),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Provider import data retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createProviderVerifyExistencePath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("Provider Management"))
                    .summary("Verify provider existence")
                    .description("Verifies if provider exists by identification")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("string")),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Provider existence verified"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createProviderCarriersPath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("Provider Management"))
                    .summary("Get carriers")
                    .description("Retrieves all carriers")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Carriers retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createClientPath(): PathItem =
        PathItem()
            .post(
                Operation()
                    .tags(listOf("Client Management"))
                    .summary("Create client")
                    .description("Creates a new client")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .requestBody(
                        RequestBody()
                            .content(
                                Content()
                                    .addMediaType(
                                        "application/json",
                                        MediaType().schema(Schema<Any>().type("object")),
                                    ),
                            ),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Client created successfully"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )
            .get(
                Operation()
                    .tags(listOf("Client Management"))
                    .summary("Get clients by parameters")
                    .description("Retrieves clients by search parameters")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Clients retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createClientByIdPath(): PathItem =
        PathItem()
            .put(
                Operation()
                    .tags(listOf("Client Management"))
                    .summary("Update client")
                    .description("Updates an existing client by ID")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("integer")),
                    )
                    .requestBody(
                        RequestBody()
                            .content(
                                Content()
                                    .addMediaType(
                                        "application/json",
                                        MediaType().schema(Schema<Any>().type("object")),
                                    ),
                            ),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Client updated successfully"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )
            .get(
                Operation()
                    .tags(listOf("Client Management"))
                    .summary("Get client by ID")
                    .description("Retrieves a client by ID")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("integer")),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Client retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createClientVerifyExistencePath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("Client Management"))
                    .summary("Verify client existence")
                    .description("Verifies if client exists by identification")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("string")),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Client existence verified"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createUserPath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("User Management"))
                    .summary("Get users by search parameters")
                    .description("Retrieves users by search parameters")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Users retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )
            .put(
                Operation()
                    .tags(listOf("User Management"))
                    .summary("Forgot password")
                    .description("Handles forgot password request")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .requestBody(
                        RequestBody()
                            .content(
                                Content()
                                    .addMediaType(
                                        "application/json",
                                        MediaType().schema(Schema<Any>().type("object")),
                                    ),
                            ),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Password reset initiated"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createUserByIdPath(): PathItem =
        PathItem()
            .put(
                Operation()
                    .tags(listOf("User Management"))
                    .summary("Update user")
                    .description("Updates an existing user by ID")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("integer")),
                    )
                    .requestBody(
                        RequestBody()
                            .content(
                                Content()
                                    .addMediaType(
                                        "application/json",
                                        MediaType().schema(Schema<Any>().type("object")),
                                    ),
                            ),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("User updated successfully"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )
            .get(
                Operation()
                    .tags(listOf("User Management"))
                    .summary("Get user by ID")
                    .description("Retrieves a user by ID")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("integer")),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("User retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createUserPurchaseRequestPath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("User Management"))
                    .summary("Get users for purchase requests")
                    .description("Retrieves users authorized for purchase requests")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Users retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createUserPurchaseOrderPath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("User Management"))
                    .summary("Get users for purchase orders")
                    .description("Retrieves users authorized for purchase orders")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Users retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createUserSalePath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("User Management"))
                    .summary("Get sales users")
                    .description("Retrieves users authorized for sales")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Sales users retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createMenuPath(): PathItem =
        PathItem()
            .post(
                Operation()
                    .tags(listOf("Menu Management"))
                    .summary("Create menu")
                    .description("Creates a new menu item")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .requestBody(
                        RequestBody()
                            .content(
                                Content()
                                    .addMediaType(
                                        "application/json",
                                        MediaType().schema(Schema<Any>().type("object")),
                                    ),
                            ),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Menu created successfully"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )
            .get(
                Operation()
                    .tags(listOf("Menu Management"))
                    .summary("Get menus by search parameters")
                    .description("Retrieves menus by search parameters")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Menus retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createMenuByIdPath(): PathItem =
        PathItem()
            .put(
                Operation()
                    .tags(listOf("Menu Management"))
                    .summary("Update menu")
                    .description("Updates an existing menu by ID")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("integer")),
                    )
                    .requestBody(
                        RequestBody()
                            .content(
                                Content()
                                    .addMediaType(
                                        "application/json",
                                        MediaType().schema(Schema<Any>().type("object")),
                                    ),
                            ),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Menu updated successfully"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )
            .get(
                Operation()
                    .tags(listOf("Menu Management"))
                    .summary("Get menu by ID")
                    .description("Retrieves a menu by ID")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("integer")),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Menu retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createProjectPath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("Project Management"))
                    .summary("Get all active projects")
                    .description("Retrieves all active projects")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Active projects retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createRoleByIdPath(): PathItem =
        PathItem()
            .get(
                Operation()
                    .tags(listOf("Role Management"))
                    .summary("Get role by ID")
                    .description("Retrieves a role by ID")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("integer")),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Role retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createDepartmentPath(): PathItem =
        PathItem()
            .post(
                Operation()
                    .tags(listOf("Department Management"))
                    .summary("Create department")
                    .description("Creates a new department")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .requestBody(
                        RequestBody()
                            .content(
                                Content()
                                    .addMediaType(
                                        "application/json",
                                        MediaType().schema(Schema<Any>().type("object")),
                                    ),
                            ),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Department created successfully"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )
            .get(
                Operation()
                    .tags(listOf("Department Management"))
                    .summary("Get departments by parameters")
                    .description("Retrieves departments by search parameters")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Departments retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )

    private fun createDepartmentByIdPath(): PathItem =
        PathItem()
            .put(
                Operation()
                    .tags(listOf("Department Management"))
                    .summary("Update department")
                    .description("Updates an existing department by ID")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("integer")),
                    )
                    .requestBody(
                        RequestBody()
                            .content(
                                Content()
                                    .addMediaType(
                                        "application/json",
                                        MediaType().schema(Schema<Any>().type("object")),
                                    ),
                            ),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Department updated successfully"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )
            .get(
                Operation()
                    .tags(listOf("Department Management"))
                    .summary("Get department by ID")
                    .description("Retrieves a department by ID")
                    .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                    .addParametersItem(
                        Parameter()
                            .name("id")
                            .`in`("path")
                            .required(true)
                            .schema(Schema<Any>().type("integer")),
                    )
                    .responses(
                        ApiResponses()
                            .addApiResponse("200", ApiResponse().description("Department retrieved"))
                            .addApiResponse("401", ApiResponse().description("Unauthorized")),
                    ),
            )
}
