package web.service;

import web.dao.RoleDAO;
import web.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Pavel Peskov
 * implamentation of {@link RoleService} interface
 */
@Service
public class RoleServiceImpl implements RoleService {

    private RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public RoleServiceImpl() {
    }

    @Override
    public List<Role> listRoles() {
        return roleDAO.listRoles();
    }
}
