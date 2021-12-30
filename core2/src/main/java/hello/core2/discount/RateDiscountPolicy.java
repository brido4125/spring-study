package hello.core2.discount;

import hello.core2.annotation.MainDiscountPolicy;
import hello.core2.member.Grade;
import hello.core2.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@MainDiscountPolicy
@Primary
public class RateDiscountPolicy implements DiscountPolicy{

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            int discountPercent = 10;
            return price * discountPercent / 100;
        }else{
            return 0;
        }
    }
}
