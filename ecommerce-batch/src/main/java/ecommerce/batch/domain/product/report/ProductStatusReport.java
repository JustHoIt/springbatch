package ecommerce.batch.domain.product.report;


import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductStatusReport {

  private LocalDate stateDate = LocalDate.now();

  private String productStatus;
  private Integer productCount;
  private BigDecimal avgStockQuantity;
  
}
