package com.cgm.crud.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cgm.crud.dao.PasswordDao;
import com.cgm.crud.entity.PasswordReset;

@Repository
@Transactional
public class PasswordDaoImpl implements PasswordDao {
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("rawtypes")
    @Override
    public PasswordReset getTokenDataByEmail(String email) {
        String query = "SELECT pw FROM PasswordReset pw WHERE pw.email = :email";
        Query queryEmail = this.sessionFactory.getCurrentSession().createQuery(query);
        queryEmail.setParameter("email", email);
        PasswordReset passwordReset = (PasswordReset) queryEmail.uniqueResult();
        return passwordReset;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void deleteToken(String email) {
        String query = "DELETE FROM PasswordReset pw WHERE pw.email = :email";
        Query q = this.sessionFactory.getCurrentSession().createQuery(query);

        q.setParameter("email", email);
        q.executeUpdate();
    }

    @Override
    public void createToken(PasswordReset pswToken) {
        sessionFactory.getCurrentSession().save(pswToken);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public PasswordReset dbGetDataByToken(String token) {
        String query = "SELECT pw FROM PasswordReset pw WHERE pw.token = :token";
        Query queryToken = this.sessionFactory.getCurrentSession().createQuery(query);
        queryToken.setParameter("token", token);
        PasswordReset passwordReset = (PasswordReset) queryToken.uniqueResult();
        return passwordReset;
    }
}
