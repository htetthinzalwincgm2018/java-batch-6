package com.cgm.crud.dao;

import com.cgm.crud.entity.PasswordReset;

public interface PasswordDao {
    PasswordReset getTokenDataByEmail(String email);

    void deleteToken(String email);

    void createToken(PasswordReset pswToken);
}
