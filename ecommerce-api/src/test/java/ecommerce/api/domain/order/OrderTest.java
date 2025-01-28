package ecommerce.api.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import ecommerce.api.domain.payment.PaymentMethod;
import ecommerce.api.domain.payment.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

  private Order order;

  @BeforeEach
  void setUp() {
    order = Order.createOrder(1L);
    order.addOrderItem("PROD01", 2, 100);
    order.initPayment(PaymentMethod.CREDIT_CARD);
  }

  @Test
  @DisplayName("결제 완료")
  void testCompletePaymentSuccess() {
    order.completePayment(true);

    assertAll(
        () -> assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.PROCESSING),
        () -> assertThat(order.getPaymentStatus()).isEqualTo(PaymentStatus.COMPLETED),
        () -> assertThat(order.isPaymentSuccess()).isTrue()
    );
  }

  @Test
  @DisplayName("결제 실패")
  void testCompletePaymentFail() {
    order.completePayment(false);

    assertAll(
        () -> assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.PROCESSING),
        () -> assertThat(order.getPaymentStatus()).isEqualTo(PaymentStatus.FAILED),
        () -> assertThat(order.isPaymentSuccess()).isFalse()
    );
  }

  @Test
  @DisplayName("결제를 처리할수 없는 상태 - 예외처리")
  void testCompletePaymentException() {
    order.completePayment(false);

    assertThatThrownBy(() -> order.completePayment(true))
        .isInstanceOf(IllegalOrderStateException.class);
  }

  @Test
  @DisplayName("주문 자체가 완료됐을 경우")
  void testCompleteOrder() {
    order.completePayment(true);

    order.complete();

    assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.COMPLETED);
  }

  @Test
  @DisplayName("결제가 완료되지 않았을 때 주문 완료 처리를 할 경우")
  void testCompleteOrderPaymentFail() {
    order.completePayment(false);

    assertThatThrownBy(() -> order.complete())
        .isInstanceOf(IllegalOrderStateException.class);
  }

  @Test
  @DisplayName("주문상태가 처리중이 아닐 경우")
  void testCompleteOrderException() {
    assertThatThrownBy(() -> order.complete())
        .isInstanceOf(IllegalOrderStateException.class);
  }

  @Test
  @DisplayName("주문 취소")
  void testCompletePaymentOrderCancel() {
    order.completePayment(true);

    order.cancel();

    assertAll(
        () -> assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.CANCELLED),
        () -> assertThat(order.getPaymentStatus()).isEqualTo(PaymentStatus.REFUNDED)
    );
  }

  @Test
  @DisplayName("완료된 주문 취소 할 경우")
  void testOrderCancelAfterComplete() {
    order.completePayment(true);
    order.complete();

    assertThatThrownBy(() -> order.cancel())
        .isInstanceOf(IllegalOrderStateException.class);
  }
}