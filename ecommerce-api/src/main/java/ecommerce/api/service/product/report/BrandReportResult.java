package ecommerce.api.service.product.report;


import ecommerce.api.domain.product.report.BrandReport;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BrandReportResult {

  private LocalDate stateDate;
  private String brand;
  private Integer productCount;
  private BigDecimal avgSalesPrice;
  private BigDecimal maxSalesPrice;
  private BigDecimal minSalesPrice;
  private Integer totalStockQuantity;
  private BigDecimal avgStockQuantity;

  public static BrandReportResult from(BrandReport report) {
    return new BrandReportResult(
        report.getStateDate(),
        report.getBrand(),
        report.getProductCount(),
        report.getAvgSalesPrice(),
        report.getMaxSalesPrice(),
        report.getMinSalesPrice(),
        report.getTotalStockQuantity(),
        report.getAvgStockQuantity()
    );
  }

}
