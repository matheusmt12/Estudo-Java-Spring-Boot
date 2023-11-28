package com.matheusmt.pvd.pvd.Service;

import com.matheusmt.pvd.pvd.DTO.UserDTO;
import com.matheusmt.pvd.pvd.Exceptions.NoItemException;
import com.matheusmt.pvd.pvd.Repository.IUserRepository;
import com.matheusmt.pvd.pvd.Security.SecurityConfig;
import com.matheusmt.pvd.pvd.entity.User;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private IUserRepository iUserRepository;
    private ModelMapper mapper;
    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
        this.mapper = new ModelMapper();

    }

    public List<UserDTO> findAll(){
        return iUserRepository.findAll().stream().map(user ->
                new UserDTO(user.getId(), user.getName(),user.getUsername(), user.getPassword(),
                        user.isEnabled())).collect(Collectors.toList());
    }


    @Transactional
    public User save(UserDTO userDto){


        userDto.setPassword(SecurityConfig.passwordEncoder().encode(userDto.getPassword()));

        User user = new User();
        user.setEnabled(user.isEnabled());
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        iUserRepository.save(user);
        return user;
    }

    @Transactional
    public UserDTO update(UserDTO user){

        user.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));

        User save = mapper.map(user,User.class);

        save.setId(user.getId());
        save.setEnabled(user.isEnabled());
        save.setName(user.getName());

        Optional<User> opt = iUserRepository.findById(user.getId());
            if (opt.isEmpty()){
                throw new NoItemException("Não foi possivel encontrar o usuário");
            }

        save = iUserRepository.save(save);
        return new UserDTO(save.getId(), save.getName(), save.getUsername(), save.getPassword(), save.isEnabled());
    }

    @Transactional
    public void deleteById(Long id){
        iUserRepository.deleteById(id);
    }

    public UserDTO findById(Long id){
        Optional<User> opt = iUserRepository.findById(id);
        if (opt.isPresent()){
            User user = opt.get();
             return new UserDTO(user.getId(), user.getName(), user.getUsername(), user.getPassword(),user.isEnabled());
        }else{
            throw new NoItemException("O Usuario não foi encontrado");
        }

    }

    public User getByUsername(String username){

        return iUserRepository.findUserByUsername(username);

    }

}
