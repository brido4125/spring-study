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
//        Member member = new Member();
//        member.setId(1L);
//        member.setName("helloJPA");
        //em.persist(member);


        /*Member findMember = em.find(Member.class, 1L);
        findMember.setName("수정된 이름");*/

        try {
            /*
            * JPQL : Entity Object 를 대상으로 query 작성 cf) SQL : Table 을 대상으로 query 작성
            * setFirstResult, setMaxResults => 페이징 할때 갯수 조절 시 사용 가능
            * */
            List<Member> findAllMember = em.createQuery("select m from Member as m",Member.class)
                    .getResultList();

            for (Member member : findAllMember) {
                System.out.println("member = " + member.getName());
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
