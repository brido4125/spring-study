package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Address address = new Address("daegu", "daebak", "23211");

            User user1 = new User();
            user1.setUsername("user1");
            user1.setAddress(address);

            Address copyAddress = new Address("new city", address.getStreet(),address.getZipcode());

            user1.setAddress(copyAddress);

            User user2 = new User();
            user2.setUsername("user2");
            user2.setAddress(address);

            em.persist(user1);
            em.persist(user2);

            //user1 의 도시만 new city로 바꾸려는 의도
            //but user1 user2 city 모두 바뀐다.
            user1.getAddress().setCity("new city");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

}
