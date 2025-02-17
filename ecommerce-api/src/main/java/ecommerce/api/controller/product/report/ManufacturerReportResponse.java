package ecommerce.api.controller.product.report;


import ecommerce.api.service.product.report.ManufacturerReportResult;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ManufacturerReportResponse {

  private LocalDate stateDate = LocalDate.now();
  private String manufacturer;
  private Integer productCount;
  private BigDecimal avgSalesPrice;
  private Integer potentialSalesAmount;

  public static ManufacturerReportResponse  from(ManufacturerReportResult result) {
    return new ManufacturerReportResponse (
        result.getStateDate(),
        result.getManufacturer(),
        result.getProductCount(),
        result.getAvgSalesPrice(),
        result.getPotentialSalesAmount()
    );
  }
}
