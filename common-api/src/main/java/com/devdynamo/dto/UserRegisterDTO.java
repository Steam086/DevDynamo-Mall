package com.devdynamo.dto;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String oldPassword;
    private String confirmPassword;
    private String phone;

    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$", message = "用户名只能包含字母和数字，长度2-20")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", 
            message = "密码至少8个字符，至少包含一个字母和一个数字")
    private String password;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @Pattern(regexp = "^[a-zA-Z\\s\\-']{2,20}$", 
            message = "名字只能包含字母、空格、连字符和撇号，长度2-20")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z\\s\\-']{2,20}$", 
            message = "姓氏只能包含字母、空格、连字符和撇号，长度2-20")
    private String lastName;
}
