package com.example.bloglv4.user.dto;

import com.example.bloglv4.user.entity.UserRoleEnum;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;


@Getter
public class SignupRequestDto {
    @Pattern(regexp = "^[0-9a-z]{4,10}$", message = "4 ~ 10자 사이의 알파벳 소문자와 숫자만 가능합니다.")
    private String username;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
            message = "password 형식 : 대소문자, 숫자, 특수문자(@,$,!,%,*,?,&)만 입력가능 및 하나 씩 포함 해야 함.")
    private String password;
    private UserRoleEnum userRole;
    private String adminToken;
}




