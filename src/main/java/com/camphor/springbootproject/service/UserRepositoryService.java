package com.camphor.springbootproject.service;

import com.camphor.springbootproject.dao.UserRepository;
import com.camphor.springbootproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRepositoryService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User get(Integer id) throws UserNoFoundException {
        Optional<User> result = userRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        }
        throw new UserNoFoundException("Could not find any users with ID "+ id);
    }

    public void delete(Integer id) throws UserNoFoundException {
        Long count = userRepository.countById(id);
        if(count ==null || count == 0) {
            throw new UserNoFoundException("Could not find any users with ID "+ id);
        }
        userRepository.deleteById(id);
    }


}
