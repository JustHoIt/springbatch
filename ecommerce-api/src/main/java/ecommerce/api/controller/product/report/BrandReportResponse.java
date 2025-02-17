package ecommerce.api.controller.product.report;


import ecommerce.api.service.product.report.BrandReportResult;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BrandReportResponse {

  private LocalDate stateDate;
  private String brand;
  private Integer productCount;
  private BigDecimal avgSalesPrice;
  private BigDecimal maxSalesPrice;
  private BigDecimal minSalesPrice;
  private Integer totalStockQuantity;
  private BigDecimal avgStockQuantity;

  public static BrandReportResponse from(BrandReportResult result) {
    return new BrandReportResponse(
        result.getStateDate(),
        result.getBrand(),
        result.getProductCount(),
        result.getAvgSalesPrice(),
        result.getMaxSalesPrice(),
        result.getMinSalesPrice(),
        result.getTotalStockQuantity(),
        result.getAvgStockQuantity()
    );
  }

}
