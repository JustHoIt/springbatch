package ecommerce.batch.domain.product.report;


import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerReport {

  private LocalDate stateDate = LocalDate.now();

  private String manufacturer;
  private Integer productCount;
  private BigDecimal avgSalesPrice;
  private Integer potentialSalesAmount;

}
