package com.matheusmt.pvd.pvd.Repository;

import com.matheusmt.pvd.pvd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {

    User findUserByUsername (String username);
}
