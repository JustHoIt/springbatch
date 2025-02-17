package ecommerce.api.domain.product.report;


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
@Table(name = "product_status_reports")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@IdClass(ProductStatusReportId.class)
public class ProductStatusReport implements Serializable {

  @Id
  private LocalDate stateDate;
  @Id
  private String productStatus;

  private Integer productCount;
  private BigDecimal avgStockQuantity;
  
}
