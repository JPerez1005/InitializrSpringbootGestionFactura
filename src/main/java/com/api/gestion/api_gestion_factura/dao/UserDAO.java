package com.api.gestion.api_gestion_factura.dao;

import com.api.gestion.api_gestion_factura.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */

@Repository
public interface UserDAO extends JpaRepository<User, Integer>{
    
    User findByEmail(@Param(("email")) String email);
//    User findByEmail(String email);

    
}
