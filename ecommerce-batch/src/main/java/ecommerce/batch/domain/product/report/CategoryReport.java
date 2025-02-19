package ecommerce.batch.domain.product.report;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category_reports")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@IdClass(CategoryReportId.class)
public class CategoryReport implements Serializable {

  @Id
  private LocalDate stateDate;
  @Id
  private String category;
  private Integer productCount;
  private BigDecimal avgSalesPrice;
  private BigDecimal maxSalesPrice;
  private BigDecimal minSalesPrice;
  private Integer totalStockQuantity;
  private BigDecimal potentialSalesAmount;


  public CategoryReport(String category, Integer productCount, BigDecimal avgSalesPrice,
      BigDecimal maxSalesPrice, BigDecimal minSalesPrice, Integer totalStockQuantity,
      BigDecimal potentialSalesAmount) {
    this(LocalDate.now(), category, productCount, avgSalesPrice, maxSalesPrice, minSalesPrice,
        totalStockQuantity, potentialSalesAmount);
  }
}
