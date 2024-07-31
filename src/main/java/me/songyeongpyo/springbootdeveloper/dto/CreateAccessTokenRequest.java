package me.songyeongpyo.springbootdeveloper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccessTokenRequest {
    private String refresh_token;
}
