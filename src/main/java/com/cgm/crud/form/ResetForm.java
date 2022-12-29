package com.cgm.crud.form;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.cgm.crud.entity.PasswordReset;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetForm {
	@Email
	@NotEmpty
	private String email;
	private String password;
	private String token;
	private Timestamp created_at;
	private Timestamp expired_at;

	public ResetForm() {
		super();
	}

	public ResetForm(PasswordReset passwordReset) {
		this.email = passwordReset.getEmail();
		this.token = passwordReset.getToken();
		this.created_at = passwordReset.getCreated_at();
		this.expired_at = passwordReset.getExpired_at();
	}
}
