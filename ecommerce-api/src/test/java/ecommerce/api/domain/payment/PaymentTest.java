package ecommerce.api.domain.payment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentTest {

  private Payment payment;

  @BeforeEach
  void setUp() {
    payment = Payment.createPayment(PaymentMethod.CREDIT_CARD, 1000, null);
  }

  @Test
  @DisplayName("결제 대기중")
  void testPaymentPending() {
    assertThat(payment.getPaymentStatus()).isEqualTo(PaymentStatus.PENDING);
  }


  @Test
  @DisplayName("결제 완료")
  void testPaymentStatusComplete() {
    payment.complete();

    assertThat(payment.getPaymentStatus()).isEqualTo(PaymentStatus.COMPLETED);
  }

  @Test
  @DisplayName("결제 실패")
  void testPaymentStatusNotComplete() {
    payment.cancel();

    assertThat(!payment.getPaymentStatus().equals(PaymentStatus.COMPLETED));
  }

  @Test
  @DisplayName("결제 완료 예외처리")
  void testPaymentStatusCompleteException() {
    payment.complete();

    assertThatThrownBy(payment::complete).isInstanceOf(IllegalPaymentStateException.class);
  }


  @Test
  @DisplayName("결제 실패")
  void testPaymentStatusFail() {

    payment.fail();

    assertThat(payment.getPaymentStatus()).isEqualTo(PaymentStatus.FAILED);
  }

  @Test
  @DisplayName("결제 실패 예외처리")
  void testPaymentStatusFailException() {
    payment.complete();

    assertThatThrownBy(payment::fail)
        .isInstanceOf(IllegalPaymentStateException.class);
  }


  @Test
  @DisplayName("결제 취소")
  void testPaymentStatusCancel() {
    payment.cancel();

    assertThat(payment.getPaymentStatus()).isEqualTo(PaymentStatus.CANCELLED);
  }

  @Test
  @DisplayName("결제 완료 후 취소")
  void testPaymentStatusCancelAfterComplete() {
    payment.complete();
    payment.cancel();

    assertThat(payment.getPaymentStatus()).isEqualTo(PaymentStatus.REFUNDED);
  }

  @Test
  @DisplayName("결제 대기 중 취소")
  void testPaymentStatusCancelAfterPending() {
    payment.cancel();

    assertThat(payment.getPaymentStatus()).isEqualTo(PaymentStatus.CANCELLED);
  }

  @Test
  @DisplayName("결제 실패 후 취소")
  void testPaymentStatusCancelAfterFail() {
    payment.fail();
    payment.cancel();

    assertThat(payment.getPaymentStatus()).isEqualTo(PaymentStatus.CANCELLED);
  }

  @Test
  @DisplayName("환불 후 취소")
  void testPaymentStatusCancelAfterRefund() {
    payment.complete();
    payment.cancel();

    assertThatThrownBy(payment::cancel)
        .isInstanceOf(IllegalPaymentStateException.class);
  }

  @Test
  @DisplayName("취소 후 취소")
  void testPaymentStatusCancelAfterCancel() {
    payment.fail();
    payment.cancel();

    assertThatThrownBy(payment::cancel)
        .isInstanceOf(IllegalPaymentStateException.class);
  }

  @Test
  @DisplayName("결제 취소 예외처리")
  void testPaymentStatusCancelException() {
    payment.complete();

    assertThatThrownBy(payment::complete)
        .isInstanceOf(IllegalPaymentStateException.class);
  }

  @Test
  @DisplayName("환불")
  void testPaymentRefund() {
    payment.complete();

    payment.refund();

    assertThat(payment.getPaymentStatus()).isEqualTo(PaymentStatus.REFUNDED);
  }

  @Test
  @DisplayName("환불 예외처리")
  void testPaymentStatusRefundException() {
    payment.fail();

    assertThatThrownBy(payment::refund)
        .isInstanceOf(IllegalPaymentStateException.class);
  }


}