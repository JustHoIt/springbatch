package ecommerce.api.domain.order;

public enum OrderStatus {

  PENDING_PAYMENT("결제 대기중"),
  PROCESSING("처리 중"),
  COMPLETED("완료 됨"),
  CANCELLED("취소 됨")

  ;

  final String desc;

  OrderStatus(String desc) {
    this.desc = desc;
  }


}
