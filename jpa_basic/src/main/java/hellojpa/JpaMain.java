package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            User user = new User();
            user.setUsername("user1");
            user.setHomeAddress(new Address("homeCity", "street", "1234"));

            user.getFavoriteFoods().add("chicken");
            user.getFavoriteFoods().add("ice");
            user.getFavoriteFoods().add("pasta");

            user.getAddressHistory().add(new Address("old1", "street", "1234"));
            user.getAddressHistory().add(new Address("old2", "street", "1234"));

            em.persist(user);

            em.flush();
            em.clear();

            User findUser = em.find(User.class, user.getId());

            /*
            * 값 타입 컬렉션들은 지연로딩이라 아래 코드가 실행되어야 select 쿼리가 발생
            * */
            List<Address> addressHistory = findUser.getAddressHistory();
            for (Address address : addressHistory) {
                System.out.println("address.getCity() = " + address.getCity());
            }
            Set<String> favoriteFoods = findUser.getFavoriteFoods();
            for (String favoriteFood : favoriteFoods) {
                System.out.println("favoriteFood = " + favoriteFood);
            }

            /*
            * 값 타입 수정하기
            * 1) HomeAddress : homeCity -> newCity
            * 2) FavoriteFoods : chicken -> 한식
            * 3) AddressHistory : old1 -> newCity1
            * */
            Address homeAddress = findUser.getHomeAddress();
            findUser.setHomeAddress(new Address("newCity", homeAddress.getStreet(), homeAddress.getZipcode()));

            findUser.getFavoriteFoods().remove("chicken");
            findUser.getFavoriteFoods().add("한식");

            findUser.getAddressHistory().remove(new AddressEntity("old1", "street", "1234"));
            findUser.getAddressHistory().add(new Address("newCity1", "street", "1234"));
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

}
