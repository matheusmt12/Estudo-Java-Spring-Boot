package com.matheusmt.pvd.pvd.Security;

import com.matheusmt.pvd.pvd.DTO.LoginDTO;
import com.matheusmt.pvd.pvd.Exceptions.PassWordNotFound;
import com.matheusmt.pvd.pvd.Service.UserService;
import com.matheusmt.pvd.pvd.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustonUserDetalheService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Login não encontrado");
        }
        return new UserPrincipal(user);
    }

    public void verifyUserCredentials(LoginDTO loginDate){

        UserDetails user = loadUserByUsername(loginDate.getUsername());

        boolean validPass = SecurityConfig.passwordEncoder()
                .matches(loginDate.getPassword(), user.getPassword());

        if(!validPass){
            throw new PassWordNotFound("Senha inválida");
        }

    }
}
