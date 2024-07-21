package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<User> UserSomeCar(String model, int series) {
      String query = "select u from User u, Car c where u.car = c and c.model =:model and c.series =:series ";
      Query result = sessionFactory.getCurrentSession().createQuery(query, User.class);
      result.setParameter("model", model);
      result.setParameter("series", series);
      List<User> userList = result.getResultList();
      return userList;
   }

}
