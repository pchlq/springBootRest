package web.dao;

import web.models.User;

import java.util.List;

/**
 * @author Pavel Peskov
 */
public interface UserDAO {
    void addUser(User user);

    void removeUser(User user);

    User getUserById(Long id);

    List<User> listUsers();

    User getUserByEmail(String email);
}
