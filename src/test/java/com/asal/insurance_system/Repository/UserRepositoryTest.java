package com.asal.insurance_system.Repository;
import com.asal.insurance_system.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void findByEmailNotFoundTest(){
        Optional<User> user = userRepository.findByEmail("mm@mail.com");
        assertFalse(user.isPresent());
    }

    @Test
    void findByEmailFoundTest(){
        Optional<User> user = userRepository.findByEmail("admin@gmail.com");
        assertTrue(user.isPresent());
        assertEquals("admin@gmail.com",user.get().getEmail());
    }

}