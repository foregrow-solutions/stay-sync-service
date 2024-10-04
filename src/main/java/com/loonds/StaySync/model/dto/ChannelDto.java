package com.loonds.StaySync.model.dto;

import com.loonds.StaySync.model.entity.Channel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelDto {
    String id;
    String name;
    String email;
    String mobile;

    public static ChannelDto of(Channel channel){
        ChannelDto output = new ChannelDto();
        output.setId(channel.getId());
        output.setName(channel.getName());
        output.setEmail(channel.getEmail());
        output.setMobile(channel.getMobile());
        return output;
    }
}
