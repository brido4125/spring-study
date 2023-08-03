package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository //컴포넌트 스캔의 대상
@RequiredArgsConstructor
public class OrderRepositoryV0 {
    public void save(String itemId) {
        //저장 로작
        if(itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생");//Repo에 정상적으로 저장되지 않음
        }
        sleep(1000); // 1초간 상품 저장하는데 걸림
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
