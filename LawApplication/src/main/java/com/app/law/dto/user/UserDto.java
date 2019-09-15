package com.app.law.dto.user;

import com.app.law.entity.Privilege;
import com.app.law.entity.Specialization;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.google.gson.annotations.Expose;
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

    @NonNull
    @Expose(serialize = false)
    private String password;

    @Expose(serialize = false)
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
    private Integer roleId;
    private String token;

    private List<Specialization> listSpecialization;
    private List<Privilege> listPrivilege;

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
