package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    public List<Role> listRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public void deleteRole(int id) {
        entityManager.remove(id);
    }

    @Override
    public Role getRole(int id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public void updateRole(Role role) {
        entityManager.merge(role);
    }

    @Override
    public Role getRole(String name) {
        String role = name;
        Query query = entityManager.createQuery("FROM Role r where r.role = :role",Role.class);
        Role out = (Role) query.setParameter("role", name).getSingleResult();
        return out;
    }
}
