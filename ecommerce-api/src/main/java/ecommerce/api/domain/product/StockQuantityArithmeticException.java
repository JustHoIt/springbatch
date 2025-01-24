package ecommerce.api.domain.product;

public class StockQuantityArithmeticException extends ArithmeticException {

  public StockQuantityArithmeticException() {
    super("재고를 더이상 증가시킬 수 없습니다.");
  }

}
