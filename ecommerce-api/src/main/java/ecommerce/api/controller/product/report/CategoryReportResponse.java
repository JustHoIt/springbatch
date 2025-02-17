package ecommerce.api.controller.product.report;


import ecommerce.api.service.product.report.CategoryReportResult;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryReportResponse {

  private LocalDate stateDate;
  private String category;
  private Integer productCount;
  private BigDecimal avgSalesPrice;
  private BigDecimal maxSalesPrice;
  private BigDecimal minSalesPrice;
  private Integer totalStockQuantity;
  private BigDecimal potentialSalesAmount;

  public static CategoryReportResponse from(CategoryReportResult result) {
    return new CategoryReportResponse(
        result.getStateDate(),
        result.getCategory(),
        result.getProductCount(),
        result.getAvgSalesPrice(),
        result.getMaxSalesPrice(),
        result.getMinSalesPrice(),
        result.getTotalStockQuantity(),
        result.getPotentialSalesAmount()
    );
  }
}
