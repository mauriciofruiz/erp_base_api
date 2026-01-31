package erp.base.api.security.dto

import erp.base.api.dto.MenuUsuarioResponseDto
import erp.base.api.dto.RolResponseDto

data class LoginResponseDto(
    val userId: Int? = null,
    val userName: String? = null,
    val userNick: String? = null,
    val token: String? = null,
    val password: String? = null,
    val photo: String? = null,
    val position: Int? = null,
    val menuList: List<MenuUsuarioResponseDto>? = null,
    val cashId: Int? = null,
    val branchId: Int? = null,
    val roles: List<RolResponseDto>? = null,
)
