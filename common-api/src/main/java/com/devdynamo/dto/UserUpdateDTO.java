package com.devdynamo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserUpdateDTO {
    @Pattern(regexp = "^[a-zA-Z\\s\\-']{2,20}$", 
            message = "名字只能包含字母、空格、连字符和撇号，长度2-20")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z\\s\\-']{2,20}$", 
            message = "姓氏只能包含字母、空格、连字符和撇号，长度2-20")
    private String lastName;

    @Email(message = "邮箱格式不正确")
    private String email;

    private String phone;
} 