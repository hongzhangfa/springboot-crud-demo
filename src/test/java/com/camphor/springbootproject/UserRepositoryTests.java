package com.camphor.springbootproject;

import com.camphor.springbootproject.dao.UserRepository;
import com.camphor.springbootproject.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("caocao@qq.com");
        user.setPassword("56789");
        user.setFirstName("Alex");
        user.setLastName("Stevenson");

        User theUser = userRepository.save(user);

        System.out.println("Save theUser: "+ theUser);

        Assertions.assertThat(theUser).isNotNull();
        Assertions.assertThat(theUser.getId()).isGreaterThan(0);

//        Assertions.assertNotNull(theUser, "This value cannot be empty!");
//        Assertions.assertTrue(theUser.getId()>0);

    }


    @Test
    public void testListAll() {
        Iterable<User> users = userRepository.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void testUpdate() {
        Integer userId = 1;
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        user.setPassword("hello123");
        userRepository.save(user);

        User updateUser = userRepository.findById(userId).get();
        Assertions.assertThat(updateUser.getPassword()).isEqualTo("hello123");
    }

    @Test
    public void testGet() {
        Integer userId = 1;
        Optional<User> optionalUser = userRepository.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete() {
        Integer userId = 2;
        userRepository.deleteById(userId);

        Optional<User> optionalUser = userRepository.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
