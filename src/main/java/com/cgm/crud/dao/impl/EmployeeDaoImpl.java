package com.cgm.crud.dao.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cgm.crud.dao.EmployeeDao;
import com.cgm.crud.entity.Employee;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {
    @Autowired
    private HibernateTemplate hiberneteTemplate;

    @Autowired
    private SessionFactory sessionFactory;

    // add employee
    @Override
    public void addEmployee(Employee emp) {
        sessionFactory.getCurrentSession().save(emp);
    }

    // get all employee
    @Override
    public List<Employee> getAllEmp() {
        return hiberneteTemplate.loadAll(Employee.class);
    }

    // get employee by id
    @Override
    public Employee getEmpById(int id) {
        Employee emp = hiberneteTemplate.get(Employee.class, id);
        return emp;
    }

    // update employee
    @Override
    public void updateEmp(Employee emp) {
        hiberneteTemplate.update(emp);
    }

    // delete employee
    @Override
    public void deleteEmp(int id) {
        hiberneteTemplate.delete(hiberneteTemplate.load(Employee.class, id));
    }
}
