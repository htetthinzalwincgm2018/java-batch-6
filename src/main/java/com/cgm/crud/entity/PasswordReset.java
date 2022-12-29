package com.cgm.crud.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cgm.crud.form.ResetForm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "password_reset")
public class PasswordReset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String token;
    private Timestamp created_at;
    private Timestamp expired_at;
    
    public PasswordReset() {
        super();
    }

    public PasswordReset(ResetForm resetForm) {
        super();
        this.email = resetForm.getEmail();
        this.token = resetForm.getToken();
        this.created_at = resetForm.getCreated_at();
        this.expired_at = resetForm.getExpired_at();
    }
}
