package com.app.law.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {

    private Integer id;
    private String email;
    private String password;
    private String repassword;
    private String name;
    private String image;
    private String roleName;
    private String token;

    private List<UserSpecializationDto> listSpecialization;
    private List<String> listPrivilege;

    @Override
    public String toString() {
        return "UserDTO: { "+
                "id="+id+" ,"+
                "email="+email+" ,"+
                "password="+password+" ,"+
                "token="+token+" ,"+
                "name="+name + " }";
    }

}
