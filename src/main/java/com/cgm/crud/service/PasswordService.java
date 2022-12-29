package com.cgm.crud.service;

import com.cgm.crud.form.ResetForm;

public interface PasswordService {

    ResetForm createResetToken(String email);

}
