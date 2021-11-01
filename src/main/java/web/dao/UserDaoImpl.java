package web.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    @Qualifier("getEntityManager")
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(User user) {
        entityManager.getTransaction().begin();
            Integer id = user.getId();
            if (id == null){
                entityManager.persist(user);
            }else{
                entityManager.merge(user);
            }
            entityManager.getTransaction().commit();
    }

    @Override
    @Transactional
    public List<User> listUsers() {
       return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        entityManager.getTransaction().begin();
        entityManager.remove(getUser(id));
        entityManager.getTransaction().commit();
    }

    @Override
    @Transactional
    public User getUser(int id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public void updateUser(int id, User user) {
        User oldUser = getUser(id);
        oldUser.setName(user.getName());
        oldUser.setLastName(user.getLastName());
        oldUser.setEmail(user.getEmail());
        save(oldUser);
    }
}

