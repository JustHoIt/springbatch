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
@Table(name = "manufacturer_reports")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@IdClass(ManufacturerReportId.class)
public class ManufacturerReport implements Serializable {

  @Id
  private LocalDate stateDate = LocalDate.now();
  @Id
  private String manufacturer;
  private Integer productCount;
  private BigDecimal avgSalesPrice;
  private Integer potentialSalesAmount;

}
