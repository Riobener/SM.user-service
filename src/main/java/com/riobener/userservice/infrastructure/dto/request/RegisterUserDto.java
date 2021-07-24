package com.riobener.userservice.infrastructure.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.riobener.userservice.domain.model.value_objects.UserInfo;
import com.riobener.userservice.domain.model.value_objects.UserRole;



@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterUserDto {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("info")
    private UserInfoDto info;

    private UserRole userRole = UserRole.USER;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfoDto getInfo() {
        return info;
    }

    public void setInfo(UserInfoDto info) {
        this.info = info;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
