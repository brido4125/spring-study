package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
            user.changeTeam(team);
            em.persist(user);

            //역방향만 연관관계 설정 => team.getUserList().add(user);
            em.flush();
            em.clear();

            User findUser = em.find(User.class, user.getId());
            List<User> userList = findUser.getTeam().getUserList();
            for (User member : userList) {
                System.out.println("member = " + member);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
