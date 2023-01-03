package com.cgm.crud.persistence.dao;

import com.cgm.crud.persistence.entity.PasswordReset;

public interface PasswordDao {
    PasswordReset getTokenDataByEmail(String email);

    void deleteToken(String email);

    void createToken(PasswordReset pswToken);

    PasswordReset dbGetDataByToken(String token);
}
