package ecommerce.api.controller.product.report;


import ecommerce.api.service.product.report.ProductStatusReportResult;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductStatusReportResponse {

  private LocalDate stateDate;
  private String productStatus;
  private Integer productCount;
  private BigDecimal avgStockQuantity;

  public static ProductStatusReportResponse from(ProductStatusReportResult result) {
    return new ProductStatusReportResponse(
        result.getStateDate(),
        result.getProductStatus(),
        result.getProductCount(),
        result.getAvgStockQuantity()
    );
  }
}
