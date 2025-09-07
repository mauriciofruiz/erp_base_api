package erp.base.api.security.dto;

import erp.base.api.dto.RolResponseDto;
import erp.base.api.dto.TbaMenuUsuarioResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDto {
    private Integer userId;
    private String userName;
    private String userNick;
    private String token;
    private String password;
    private String photo;
    private Integer position;
    private List<TbaMenuUsuarioResponseDto> menuList;
    private Integer cashId;
    private Integer branchId;
    private List<RolResponseDto> roles;
}
