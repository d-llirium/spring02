package br.anhembi.spring02.dto;

import br.anhembi.spring02.model.User;

// DTO = Data transfer Object

public class UserDto {
    private long code;
    private String name;
    private String email;

    public UserDto() {}

    public UserDto(User user) {
        code = user.getCode();
        name = user.getName();
        email = user.getEmail();
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
