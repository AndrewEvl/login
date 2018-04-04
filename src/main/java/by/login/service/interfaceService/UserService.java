package by.login.service.interfaceService;

import by.login.entity.User;

import java.util.Optional;

public interface UserService {

    void save(User user);

    Iterable<User> findAll();

    Optional<User> findById(Long id);

    void delete(User user);

    Optional<User> findUserByMailAndPassword (String mail, String password);
}
