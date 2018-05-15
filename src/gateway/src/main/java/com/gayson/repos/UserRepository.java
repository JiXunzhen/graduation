package com.gayson.repos;

import com.gayson.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jixunzhen on 2018/5/13.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByTelephone(String telephone);
    User findByEmail(String email);
}
