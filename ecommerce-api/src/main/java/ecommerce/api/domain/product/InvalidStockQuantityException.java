package ecommerce.api.domain.product;

public class InvalidStockQuantityException extends ArithmeticException{

  public InvalidStockQuantityException() {
    super("0이하의 값으로는 재고를 증가시킬 수 없습니다.");
  }

}
