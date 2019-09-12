package com.app.law.dto.user;

import com.app.law.dto.UserSpecializationDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {

    private Integer id;
    @NonNull
    @Email
    private String email;
    private String password;
    private String repassword;
    @NonNull
    private String name;
    private String image;
    private String field;
    private String charges;
    private String introduce;
    private InforDetail[] inforDetails;
    private Prize[] prizes;
    private Experience[] experiences;
    private Education[] educations;
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
