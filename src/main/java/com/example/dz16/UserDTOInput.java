package com.example.dz16;

import lombok.Data;

@Data
public class UserDTOInput {
    private String username;
    private String password;
    private String repeatPassword;
    private int age;

    public UserEntity toEntity() {
        return new UserEntity(this.username,this.password,this.age);
    }
}
