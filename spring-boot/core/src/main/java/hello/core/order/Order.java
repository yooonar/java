package hello.core.order;

public class Order {

    // 주문자 아이디
    private Long memberId;

    // 상품 이름
    private String itemName;

    // 상품 가격
    private int itemPrice;

    // 할인 금액
    private int discountPrice;

    public Long getMemberId() {
        return memberId;
    }

    // 생성자
    public Order(Long memberId, String itemName, int itemPrice, int discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    // 할인 계산된 결제 금액
    public int calculatePrice() {
        return itemPrice - discountPrice;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    // 객체를 출력하면 아래의 형식으로 결과가 나옴
    // System.out.println("order = " + order);
    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
