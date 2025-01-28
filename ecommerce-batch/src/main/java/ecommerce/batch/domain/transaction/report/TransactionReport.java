package ecommerce.batch.domain.transaction.report;


import ecommerce.batch.dto.transaction.TransactionLog;
import ecommerce.batch.util.DateTimeUtils;
import jakarta.persistence.Transient;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionReport {

  private LocalDate transactionDate;
  private String transactionType;

  private Long transactionCount;
  private Long totalAmount;
  private Long customerCount;
  private Long orderCount;
  private Long paymentMethodCount;
  private BigDecimal avgProductCount;
  private Long totalItemQuantity;

  @Transient
  private Set<String> customerSet;
  @Transient
  private Set<String> orderSet;
  @Transient
  private Set<String> paymentMethodSet;
  @Transient
  private Long sumProductCount;


  public static TransactionReport from(TransactionLog log) {
    return new TransactionReport(
        DateTimeUtils.toLocalDateTime(log.getTimestamp()).toLocalDate(),
        log.getTransactionType(), 1L, Long.parseLong(log.getTotalAmount()),
        1L, 1L, 1L, new BigDecimal(Long.parseLong(log.getProductCount())),
        Long.parseLong(log.getTotalItemQuantity()),
        new HashSet<>(List.of(log.getCustomerId())),
        new HashSet<>(List.of(log.getOrderId())),
        new HashSet<>(List.of(log.getPaymentMethod())),
        Long.parseLong(log.getProductCount())
    );
  }

  public static TransactionReport of(LocalDate transactionDate, String transactionType,
      Long transactionCount, Long totalAmount,
      Long customerCount, Long orderCount, Long paymentMethodCount, BigDecimal avgProductCount,
      Long totalItemQuantity
      , Set<String> customerSet, Set<String> orderSet, Set<String> paymentMethodSet,
      Long sumProductCount) {
    return new TransactionReport(transactionDate, transactionType,
        transactionCount, totalAmount, customerCount, orderCount, paymentMethodCount,
        avgProductCount, totalItemQuantity
        , customerSet, orderSet, paymentMethodSet, sumProductCount
    );
  }

  public void add(TransactionReport report) {
    transactionCount += report.getTransactionCount();
    totalAmount += report.getTotalAmount();
    customerSet.addAll(report.getCustomerSet());
    orderSet.addAll(report.getOrderSet());
    paymentMethodSet.addAll(report.getPaymentMethodSet());
    customerCount = (long) customerSet.size();
    orderCount = (long) orderSet.size();
    paymentMethodCount = (long) paymentMethodSet.size();
    sumProductCount += report.getSumProductCount();
    avgProductCount = new BigDecimal((double) sumProductCount / transactionCount);
    totalItemQuantity += report.getTotalItemQuantity();
  }
}
