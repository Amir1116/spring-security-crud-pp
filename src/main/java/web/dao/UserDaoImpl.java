package web.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManagerFactory entityManagerFactory){
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    @Transactional
    public void save(User user) {
            entityManager.persist(user);
    }

    @Override
    public List<User> listUsers() {
       return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        entityManager.remove(getUser(id));
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class,id);
    }

    @Override
    @Transactional
    public void updateUser(int id, User user) {
        User oldUser = getUser(id);
        oldUser.setName(user.getName());
        oldUser.setLastName(user.getLastName());
        oldUser.setEmail(user.getEmail());
        entityManager.merge(oldUser);
    }

    @Override
    public User getUser(String name) {
        String username = name;
        Query query = entityManager.createQuery("FROM User u where u.username = :username",User.class);
        User user = (User) query.setParameter("username", name).getSingleResult();
        return user;
    }
}

