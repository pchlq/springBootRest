package web.dao;

import web.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Pavel Peskov
 * Implementation of {@link RoleDAO} interface
 */
@Repository
public class RoleDAOImpl implements RoleDAO {

    private final EntityManager entityManager;

    @Autowired
    public RoleDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Role> listRoles() {
        return entityManager.createQuery(
                "SELECT roles FROM Role roles", Role.class
        ).getResultList();
    }
}
