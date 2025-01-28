package ecommerce.api.domain.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ProductTest {

  private Product product;

  @BeforeEach
  void setUp() {
    LocalDateTime now = LocalDateTime.now();
    product = Product.of("PRODUCT-1", 1L, "Electronics", "TestProduct", now.toLocalDate(),
        now.toLocalDate(), ProductStatus.AVAILABLE, "Test Brand", "Test Manufacturer", 1000, 50,
        now, now);
  }

  @Test
  @DisplayName("재고 증가")
  void testIncreaseStock() {
    product.increaseStock(50);

    assertThat(product.getStockQuantity()).isEqualTo(100);
  }

  @Test
  @DisplayName("MAX 값을 추가 시켰을때 예외처리")
  void testIncreaseNegativeResult() {
    assertThatThrownBy(() -> product.increaseStock(Integer.MAX_VALUE))
        .isInstanceOf(StockQuantityArithmeticException.class);
  }

  @ParameterizedTest
  @ValueSource(ints = {-10, -1, 0})
  void testIncreaseStockPositiveParameter(int notPositiveQuantity) {
    assertThatThrownBy(() -> product.increaseStock(notPositiveQuantity))
        .isInstanceOf(InvalidStockQuantityException.class);
  }


  @Test
  @DisplayName("재고 감소")
  void testDecreaseStock() {
    product.decreaseStock(50);

    assertThat(product.getStockQuantity()).isEqualTo(0);
  }

  @ParameterizedTest
  @ValueSource(ints = {-10, -1, 0})
  void testDecreaseStockPositiveParameter(int notPositiveQuantity) {
    assertThatThrownBy(() -> product.decreaseStock(notPositiveQuantity))
        .isInstanceOf(InvalidStockQuantityException.class);
  }

  @Test
  void testDecreaseStockWithInsufficientStock() {
    assertThatThrownBy(() -> product.decreaseStock(51))
        .isInstanceOf(InsufficientStockException.class);
  }
}