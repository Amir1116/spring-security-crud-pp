package web.services;

import web.model.Role;

import java.util.List;

public interface RoleService {
    void save(Role role);

    List<Role> getRolesList();

    void deleteRole(int id);

    Role getRole(int id);

    void updateRole(Role role);

    Role getRole(String name);
}
