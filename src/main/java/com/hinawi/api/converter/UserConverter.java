package com.hinawi.api.converter;


import com.hinawi.api.domains.Users;
import com.hinawi.api.dto.UserDto;

import java.util.stream.Collectors;

public class UserConverter {

    public static Users dtoToEntity(UserDto userDto) {
        Users user = new Users(userDto.getUserName(),null);
        user.setUserId(userDto.getUserId());
        user.setPassword(userDto.getPassword());
        //user.setSkills(userDto.getSkillDtos().stream().map(SkillConverter::dtoToEntity).collect(Collectors.toList()));
        return user;
    }

    public static UserDto entityToDto(Users user) {
        if(user==null)
            return new UserDto(Long.parseLong("0"),null,"");

        UserDto userDto = new UserDto(user.getUserId(), user.getUserName(), user.getEmail());
        //userDto.setSkillDtos(user.getSkills().stream().map(SkillConverter::entityToDto).collect(Collectors.toList()));
        return userDto;
    }
}
