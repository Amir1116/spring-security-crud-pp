package web.dao;

import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(User user) {
        Long id = user.getId();
        if(id == null){
            entityManager.persist(user);
        }else{
            entityManager.merge(user);
        }
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        return entityManager.createQuery("from user_crud").getResultList();
    }

    @Override
    @Transactional
    public void updateUser(long id) {
        User user = getUser(id);

    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        entityManager.remove(id);
    }

    @Override
    @Transactional
    public User getUser(long id) {
        return entityManager.find(User.class,id);
    }
}

