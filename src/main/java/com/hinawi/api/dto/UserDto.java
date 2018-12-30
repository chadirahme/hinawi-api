package com.hinawi.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    Long userId;
    String userName;
    String email;
    //List<SkillDto> skillDtos= new ArrayList<>();
    String password;

    public UserDto(Long userId, String userName,String email) {
        this.userId = userId;
        this.userName = userName;
       // this.skillDtos = skillDtos;
    }

    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
        // this.skillDtos = skillDtos;
    }
}
