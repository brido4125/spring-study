package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired
    EntityManager em;
    @Autowired OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = createMember();
        Item book = createBook("올리버 트위스트", 8000, 10);
        //when
        int orderCount = 2; // 주문할 책의 수량
        Long orderID = orderService.order(member.getId(), book.getId(), orderCount);
        //then
        Order getOrder = orderRepository.findOne(orderID);

        assertEquals("상품주문시상태는 ORDER ", OrderStatus.ORDER,getOrder.getStatus());
        assertEquals("주문한 상품 주문 수가 동일해야한다.",1,getOrder.getOrderItems().size());
        assertEquals("주문 가격은 수량 곱하기 가격이다.",8000 * orderCount,getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야한다.",8,book.getStockQuantity());
    }



    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_주문수량초과() {
        //given
        Member member = createMember();
        Item book = createBook("올리버 트위스트", 8000, 10);
        int orderCount = 11;
        //when
        orderService.order(member.getId(), book.getId(), orderCount);
        //then
        fail("재고 수량 부족 예외가 발생해야 한다.");
    }

    @Test
    public void 주문취소() throws Exception {
        //given
        Member member = createMember();
        Item book = createBook("JPA",10000,10);
        int orderCount = 2;
        Long orderID = orderService.order(member.getId(), book.getId(), orderCount);
        //when
        orderService.cancelOrder(orderID);
        //then
        Order getOrder = orderRepository.findOne(orderID);
        assertEquals("주문취소시 상태가 CANCEL로 변경되어야 한다.",OrderStatus.CANCEL,getOrder.getStatus());
        assertEquals("취소된 경우 재고가 복구되어야 한다.",10,book.getStockQuantity());
    }

    private Item createBook(String name, int price, int stockQuantity) {
        Item book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("member1");
        member.setAddress(new Address("대구", "수성구","123-22"));
        em.persist(member);
        return member;
    }
}