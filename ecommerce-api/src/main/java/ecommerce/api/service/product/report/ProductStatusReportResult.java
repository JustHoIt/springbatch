package ecommerce.api.service.product.report;


import ecommerce.api.domain.product.report.ProductStatusReport;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductStatusReportResult {

  private LocalDate stateDate;
  private String productStatus;
  private Integer productCount;
  private BigDecimal avgStockQuantity;

  public static ProductStatusReportResult from(ProductStatusReport report) {
    return new ProductStatusReportResult(
        report.getStateDate(),
        report.getProductStatus(),
        report.getProductCount(),
        report.getAvgStockQuantity()
    );
  }
}
