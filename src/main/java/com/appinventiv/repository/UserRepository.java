package com.appinventiv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appinventiv.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
