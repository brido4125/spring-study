package hello.itemservice;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemRepository itemRepository;

    /**
     * 확인용 초기 데이터 추가 - 메모리에 샘플 데이터 추가
     * ApplicationReadyEvent -> 스프링 컨테이너가 초기화를 다 끝내고, 실행 준비가 될 경우 발생하는 이벤트
     * 그 때 해당 로직을 수행함 (@PostConstruct과 비슷한 기능)
     * 다만, @PostConstruct는 AOP와 관련된 기능이 적용되지 않았을 때 호출됨
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        log.info("test data init");
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

}
