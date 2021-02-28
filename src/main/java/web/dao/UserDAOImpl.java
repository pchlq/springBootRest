package web.dao;

import web.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author Pavel Peskov
 * Implementation of {@link UserDAO} interface
 */
@Repository
public class UserDAOImpl implements UserDAO {

    private final EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addUser(User user) {
        User newUser = entityManager.merge(user);
        entityManager.persist(newUser);
    }

    @Override
    public void removeUser(User user) {
        User managed = entityManager.merge(user);
        entityManager.remove(managed);
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> listUsers() {
        return entityManager.createQuery(
                "SELECT user FROM User user", User.class
        ).getResultList();
    }

    @Override
    public User getUserByEmail(String userEmail) {
        try {
            return entityManager.createQuery(
                    "SELECT u FROM User u where u.email = :email", User.class
            )
                    .setParameter("email", userEmail)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
