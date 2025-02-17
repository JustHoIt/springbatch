package ecommerce.api.service.product.report;


import ecommerce.api.domain.product.report.ManufacturerReport;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ManufacturerReportResult{

  private LocalDate stateDate = LocalDate.now();
  private String manufacturer;
  private Integer productCount;
  private BigDecimal avgSalesPrice;
  private Integer potentialSalesAmount;

  public static ManufacturerReportResult from(ManufacturerReport report) {
    return new ManufacturerReportResult(
        report.getStateDate(),
        report.getManufacturer(),
        report.getProductCount(),
        report.getAvgSalesPrice(),
        report.getPotentialSalesAmount()
    );
  }
}
