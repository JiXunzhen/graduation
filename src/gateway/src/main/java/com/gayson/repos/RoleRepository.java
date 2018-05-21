package com.gayson.repos;

import com.gayson.model.Role;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jixunzhen on 2018/5/14.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Short> {
    Role getByName(@Param("name") Role.RoleName name);
}
