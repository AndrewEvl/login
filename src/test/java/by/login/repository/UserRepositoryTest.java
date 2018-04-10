package by.login.repository;

import by.login.config.RepositoryConfigurationTest;
import by.login.entity.Status;
import by.login.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepositoryConfigurationTest.class})
/*
I do not understand why I stopped picking up the H2 base when testing, and writes everything on the worker base.
 */
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveFindById() {
        User user = createUser();
        userRepository.save(user);
        User userById = userRepository.findById(user.getId()).get();
        assertEquals(user,userById);
    }


    @Test
    public void findAll() {
        User userFirst = createUser();
        User secondUser = createUser();
        userRepository.save(userFirst);
        userRepository.save(secondUser);
        Iterable<User> all = userRepository.findAll();
        assertNotNull(all);
    }

    @Test
    public void deleteById() {
        User user = createUser();
        userRepository.save(user);
        Long id = user.getId();
        userRepository.deleteById(id);
        assertNull(userRepository.findById(id));
    }

    @Test
    public void delete() {
        User user = createUser();
        userRepository.save(user);
        Long id = user.getId();
        userRepository.delete(user);
        assertNull(userRepository.findById(id));
    }

    private User createUser (){
        User user = new User();
        user.setFirstName("Test first name");
        user.setLastName("Test last name");
        user.setPassword("123");
        user.setMail("mail");
        user.setImage("image test.jpeg");
        user.setStatusNow(Status.ONLINE);
        user.setPreviousStatus(Status.OFFLINE);
        return user;
    }
}