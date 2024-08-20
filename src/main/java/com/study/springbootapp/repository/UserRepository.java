package com.study.springbootapp.repository;

import com.study.springbootapp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long>{
}
