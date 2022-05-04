package com.camphor.springbootproject.dao;

import com.camphor.springbootproject.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    Long countById(Integer id);

}


