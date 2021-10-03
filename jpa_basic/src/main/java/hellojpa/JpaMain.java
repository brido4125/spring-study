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
            User user1 = new User();
            user1.setUsername("hello");
            em.persist(user1);

            User user2 = new User();
            user2.setUsername("제이피에이");
            em.persist(user2);

            Team team = new Team();
            team.setName("TeamA");
            user1.setTeam(team);

            em.persist(team);

            em.flush();
            em.clear();

            //fetch = FetchType.LAZY -> proxy object 사용
            User findUser = em.find(User.class, user1.getId());
            //finUser.getTeam()은 proxy 객체를 가져오는 메서드이기 때문에 초기화 되어서 쿼리 날리지 않음
            System.out.println("findUser.getTeam() = " + findUser.getTeam().getClass());
            findUser.getTeam().getName();//이때 초기화 됨 -> 쿼리 날라감





            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

}
