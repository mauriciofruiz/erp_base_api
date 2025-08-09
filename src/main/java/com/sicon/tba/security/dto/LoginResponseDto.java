package com.sicon.tba.security.dto;

import com.sicon.tba.dto.RolResponseDto;
import com.sicon.tba.dto.TbaMenuUsuarioResponseDto;
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
