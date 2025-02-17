package ecommerce.api.service.product.report;


import ecommerce.api.domain.product.report.CategoryReport;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryReportResult {

  private LocalDate stateDate;
  private String category;
  private Integer productCount;
  private BigDecimal avgSalesPrice;
  private BigDecimal maxSalesPrice;
  private BigDecimal minSalesPrice;
  private Integer totalStockQuantity;
  private BigDecimal potentialSalesAmount;

  public static CategoryReportResult from(CategoryReport report) {
    return new CategoryReportResult(
        report.getStateDate(),
        report.getCategory(),
        report.getProductCount(),
        report.getAvgSalesPrice(),
        report.getMaxSalesPrice(),
        report.getMinSalesPrice(),
        report.getTotalStockQuantity(),
        report.getPotentialSalesAmount()
    );
  }
}
