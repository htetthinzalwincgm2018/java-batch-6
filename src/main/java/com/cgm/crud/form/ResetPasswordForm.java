package com.cgm.crud.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordForm {
    @NotEmpty
    @Size(min = 6, max = 12)
    private String password;
    private String token;

    private String confirm_password;

    public ResetPasswordForm() {
        super();
    }
}
