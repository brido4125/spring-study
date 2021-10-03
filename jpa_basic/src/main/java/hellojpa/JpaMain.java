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

            em.flush();
            em.clear();

            User findUser1 = em.find(User.class, user1.getId());
            User findRefUser1 = em.getReference(User.class, user1.getId());
            //두가지 동일하다고 나온다.
            System.out.println("findUser1.getClass() = " + findUser1.getClass());
            System.out.println("findRefUser1.getClass() = " + findRefUser1.getClass());

            //reference 를 먼저 초기화해도 두가지 클래스 타입은 동일하다고 출력된다.
            User referenceUser2 = em.getReference(User.class, user2.getId());
            System.out.println("referenceUser2.getClass() = " + referenceUser2.getClass());
            User findUser2 = em.find(User.class, user2.getId());
            System.out.println("findUser2.getClass() = " + findUser2.getClass());

            //proxy 객체가 초기화 되었는지 판별하는 메서드
            System.out.println("isLoaded" + emf.getPersistenceUnitUtil().isLoaded(findRefUser1));
            Hibernate.initialize(findRefUser1); // 강제 초기화 메서드
            findRefUser1.getUsername(); // proxy 객체가 영속성 context 에 초기화 요청 -> real query 날라감
            System.out.println("isLoaded" + emf.getPersistenceUnitUtil().isLoaded(findRefUser1));


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

}
