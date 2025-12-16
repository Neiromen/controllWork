package com.example.dz16;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserEntity {
    private String username;
    private String password;
    private int age;

    public UserDTOOutput toDTOOut() {
        return new UserDTOOutput(this.username,this.age);
    }
}
