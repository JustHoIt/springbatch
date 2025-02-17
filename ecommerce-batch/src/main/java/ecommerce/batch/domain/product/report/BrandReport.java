package ecommerce.batch.domain.product.report;


import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandReport {

  private LocalDate stateDate = LocalDate.now();

  private String brand;
  private Integer productCount;
  private BigDecimal avgSalesPrice;
  private BigDecimal maxSalesPrice;
  private BigDecimal minSalesPrice;
  private Integer totalStockQuantity;
  private BigDecimal avgStockQuantity;
  private BigDecimal potentialSalesAmount;

}
