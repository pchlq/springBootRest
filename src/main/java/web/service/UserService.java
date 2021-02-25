package web.service;

import web.models.User;

import java.util.List;

/**
 * @author Pavel Peskov
 */
public interface UserService {
    void addUser(User user);

    void removeUser(User user);

    User getUserById(Long id);

    List<User> listUsers();

    void editUser(User user);

}
