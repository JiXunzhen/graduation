package com.gayson.repos;

import com.gayson.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jixunzhen on 2018/5/13.
 */
@Repository
public interface PlatformRepository extends JpaRepository<Platform, Short> {

}
