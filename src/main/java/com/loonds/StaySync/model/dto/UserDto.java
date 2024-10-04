package com.loonds.StaySync.model.dto;

import com.loonds.StaySync.model.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    String id;
    String firstName;
    String lastName;

    public static UserDto of(User user){
        UserDto output = new UserDto();
        output.setId(user.getId());
        output.setFirstName(user.getFirstName());
        output.setLastName(user.getLastName());
        return output;
    }
}
