package hello.itemservice.domain.item;

public enum ItemType {
    BOOK("도서"),
    FOOD("음식"),
    ECT("기타");

    private final String discription;

    ItemType(String discription) {
        this.discription = discription;
    }
}
