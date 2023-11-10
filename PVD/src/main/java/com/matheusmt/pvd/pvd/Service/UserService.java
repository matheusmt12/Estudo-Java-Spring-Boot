package com.matheusmt.pvd.pvd.Service;

import com.matheusmt.pvd.pvd.DTO.ProductDTO;
import com.matheusmt.pvd.pvd.DTO.ProductDTOInfo;
import com.matheusmt.pvd.pvd.DTO.UserDTO;
import com.matheusmt.pvd.pvd.Exceptions.NoItemException;
import com.matheusmt.pvd.pvd.Repository.IUserRepository;
import com.matheusmt.pvd.pvd.entity.ItemSale;
import com.matheusmt.pvd.pvd.entity.Sale;
import com.matheusmt.pvd.pvd.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    IUserRepository iUserRepository;

    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public List<UserDTO> findAll(){
        return iUserRepository.findAll().stream().map(user ->
                new UserDTO(user.getName(),user.isEnabled())).collect(Collectors.toList());
    }


    @Transactional
    public User save(User user){

        iUserRepository.save(user);
        return user;
    }

    @Transactional
    public UserDTO update(User user){
        User save = save(user);
        return new UserDTO(save.getName(),save.isEnabled());
    }

    @Transactional
    public void deleteById(Long id){
        iUserRepository.deleteById(id);
    }

    public UserDTO findById(Long id){
        Optional<User> opt = iUserRepository.findById(id);
        if (opt.isPresent()){
            User user = opt.get();
             return new UserDTO(user.getName(),user.isEnabled());
        }else{
            throw new NoItemException("O Usuario não foi encontrado");
        }

    }

}
