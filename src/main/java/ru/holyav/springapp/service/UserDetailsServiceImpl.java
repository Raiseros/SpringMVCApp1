package ru.holyav.springapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.holyav.springapp.dao.StudentDAO;
import ru.holyav.springapp.entity.Student;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        Student student = studentDAO.findByUserName(firstName);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
       String role = studentDAO.findByUserRole(firstName);
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role));
        return new org.springframework.security.core.userdetails.User(student.getFirstName(),
                passwordEncoder.encode(student.getPassword()), grantedAuthorities);

    }
}
