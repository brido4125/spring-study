package hello.core2.discount;

import hello.core2.member.Member;

public interface DiscountPolicy {
    //return : 할인된 금액
    int discount(Member member, int price);
}
