package hellojpa;

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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            User user = new User();
            user.setUsername("user1");
            user.setTeam(team);
            em.persist(user);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
