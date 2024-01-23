package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.SQLException;

/*
* Transaction - 트랜잭션 Template 사용(반복적인 try - catch 사용 안해도 됨)
* */
@Slf4j
public class MemberServiceV3_2 {
    private final MemberRepositoryV3 memberRepository;
    //private final DataSource dataSource;
    //private final PlatformTransactionManager transactionManager;
    private final TransactionTemplate txTemplate;

    //@RequiredArgs 삭제
    public MemberServiceV3_2(MemberRepositoryV3 memberRepository, PlatformTransactionManager transactionManager) {
        this.memberRepository = memberRepository;
        this.txTemplate = new TransactionTemplate(transactionManager);
    }

    //기존의 accountTransfer가 void 타입이라서
    //TransactionStatus는 txManager로부터 getTransaction 시 반환받는 오브젝트
    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        //executeWithoutResult는 default method이고 해당 메서드가 rollback,commit 호출하는 execute() 추상 메서드 호출함
        txTemplate.executeWithoutResult((status) -> {
            try {
                bizLogic(fromId, toId, money);
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    private void bizLogic(String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);
        //간단한 계좌이체 로직
        memberRepository.update(fromId, fromMember.getMoney() - money);
        validation(toMember);
        memberRepository.update(toId, toMember.getMoney() + money);
    }

    private void validation(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이체중 예외 발생");
        }
    }
}
