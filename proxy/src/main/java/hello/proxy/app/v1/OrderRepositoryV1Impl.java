package hello.proxy.app.v1;

public class OrderRepositoryV1Impl implements OrderRepositoryV1{
    @Override
    public void save(String itemId) {
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생");
        }
        sleep(1000); // 1초간 상품 저장하는데 걸림
        System.out.println("OrderRepository.save");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
