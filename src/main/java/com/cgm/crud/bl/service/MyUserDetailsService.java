package com.cgm.crud.bl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cgm.crud.bl.dto.EmployeeDto;

@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeServices employeeService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        EmployeeDto employeeDto = employeeService.findByEmail(email);

        if (employeeDto == null) {
            throw new UsernameNotFoundException("Employee not found");
        }

        System.out.println("---employee found----");
        System.out.println("---employee found----" + employeeDto.getEmail());
        System.out.println("---employee found----" + employeeDto.getPassword());
        System.out.println("---employee found----" + employeeDto.getType());

        UserDetails user = new User(employeeDto.getEmail(), employeeDto.getPassword(), authorities(employeeDto.getType()));
        return user;
    }

    private List<GrantedAuthority> authorities(String type) {
        List<GrantedAuthority> setAuths = new ArrayList<GrantedAuthority>();

        if (Integer.parseInt(type) == 0) {
            setAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        } else if (Integer.parseInt(type) == 1) {
            setAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return setAuths;
    }
}
