package ecommerce.batch.service.transaction;


import ecommerce.batch.domain.transaction.report.TransactionReport;
import ecommerce.batch.domain.transaction.report.TransactionReportMapRepository;
import ecommerce.batch.dto.transaction.TransactionLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionReportAccumulator {

  private final TransactionReportMapRepository repository;

  public void accumulate(TransactionLog transactionLog) {
    if (!"SUCCESS".equalsIgnoreCase(transactionLog.getTransactionStatus())) {
      return;
    }

    repository.put(TransactionReport.from(transactionLog));
  }
}
