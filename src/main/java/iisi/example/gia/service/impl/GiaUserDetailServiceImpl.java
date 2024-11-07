package iisi.example.gia.service.impl;

import iisi.example.gia.model.entity.EmpDO;
import iisi.example.gia.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiaUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private EmpRepository empRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmpDO byEname = empRepository.findByEname(username);
        User user = new User(byEname.getEname(), "123456", List.of(new SimpleGrantedAuthority("ROLE_EMP")));
        return user;
    }
}
