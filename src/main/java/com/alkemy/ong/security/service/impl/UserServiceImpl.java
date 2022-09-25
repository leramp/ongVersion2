package com.alkemy.ong.security.service.impl;

import com.alkemy.ong.entity.Role;
import com.alkemy.ong.entity.Users;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UsersRepository;
import com.alkemy.ong.security.dto.RegisterDTO;
import com.alkemy.ong.security.mapper.UserMapper;
import com.alkemy.ong.security.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String emailOrPassword) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmailOrPassword(emailOrPassword, emailOrPassword);
        if(user == null){
            throw new UsernameNotFoundException("ok: false");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));

        return new org.springframework.security.core.userdetails
                .User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public RegisterDTO create(RegisterDTO user) {
        Users newUsers = userMapper.userDTO2Entity(user);
        if (user.getEmail().contains("admin")) {
            Role roles = roleRepository.findByName("ROLE_ADMIN").get();
            newUsers.setRole(roles);
        }else {
            Role roles = roleRepository.findByName("ROLE_USER").get();
            newUsers.setRole(roles);
        }
        Users usersSave = usersRepository.save(newUsers);
        RegisterDTO registerDTO = userMapper.userEntity2DTO(usersSave);
        return registerDTO;
    }

    public void delete(String id) throws NotFoundException {
        Optional<Users> user = usersRepository.findById(id);
        if(user.isPresent()){
            usersRepository.deleteById(id);
        }else{
            throw new NotFoundException("User not found");
        }
    }
}
