package ecommerce.api.service.transaction;

import ecommerce.api.domain.transaction.TransactionStatus;
import ecommerce.api.domain.transaction.TransactionType;
import ecommerce.api.service.order.OrderResult;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  protected static Logger logger = LoggerFactory.getLogger(TransactionService.class);
  private static final String NA = "N/A";

  public void logTransaction(TransactionType transactionType, TransactionStatus transactionStatus,
      String message, OrderResult orderResult) {
    try {
      putMdc(transactionType, transactionStatus, orderResult);
      log(transactionStatus, message);
    } finally {
      MDC.clear();
    }
  }

  // MDC: MAPPED Diagnostic Context
  private void putMdc(TransactionType transactionType, TransactionStatus transactionStatus
      , OrderResult orderResult) {
    Optional.ofNullable(orderResult)
        .ifPresentOrElse(this::putOrder, this::putNAOrder);
    putTransaction(transactionType, transactionStatus);
    putOrder(orderResult);
  }

  private void putOrder(OrderResult orderResult) {
    MDC.put("orderId", orderResult.getOrderId().toString());
    MDC.put("customerId", orderResult.getCustomerId().toString());
    MDC.put("totalAmount", orderResult.getTotalAmount().toString());
    MDC.put("paymentMethod", orderResult.getPaymentMethod().toString());
    MDC.put("productCount", orderResult.getProductCount().toString());
    MDC.put("totalItemQuantity", orderResult.getTotalItemQuantity().toString());
  }

  private void putNAOrder() {
    MDC.put("orderId", "N/A");
    MDC.put("customerId", "N/A");
    MDC.put("totalAmount", "N/A");
    MDC.put("paymentMethod", "N/A");
    MDC.put("productCount", "N/A");
    MDC.put("totalItemQuantity", "N/A");
  }

  private void putTransaction(TransactionType transactionType,
      TransactionStatus transactionStatus) {
    MDC.put("TransactionType", transactionType.name());
    MDC.put("TransactionStatus", transactionStatus.name());
  }

  private void log(TransactionStatus transactionStatus, String message) {
    if (transactionStatus == TransactionStatus.SUCCESS) {
      logger.info(message);
    } else {
      logger.error(message);
    }
  }

}
