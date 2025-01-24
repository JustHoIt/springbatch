package ecommerce.api.service.product;

import ecommerce.api.domain.product.Product;
import ecommerce.api.domain.product.ProductStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductResult {

  @Id
  @GeneratedValue
  private String productId;
  private Long sellerId;

  private String category;
  private String productName;
  private LocalDate salesStartDate;
  private LocalDate salesEndDate;

  @Enumerated(EnumType.STRING)
  private ProductStatus productStatus;
  private String brand;
  private String manufacturer;

  private int salesPrice;
  private int stockQuantity;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;


  public static ProductResult of(String productId, Long sellerId, String category,
      String productName,
      LocalDate salesStartDate, LocalDate salesEndDate, ProductStatus productStatus, String brand,
      String manufacturer, int salesPrice, int stockQuantity, LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    return new ProductResult(productId, sellerId, category, productName, salesStartDate,
        salesEndDate, productStatus, brand, manufacturer, salesPrice, stockQuantity, createdAt,
        updatedAt);
  }

  public static ProductResult from(Product product) {
    return new ProductResult(
        product.getProductId(),
        product.getSellerId(),
        product.getCategory(),
        product.getProductName(),
        product.getSalesStartDate(),
        product.getSalesEndDate(),
        product.getProductStatus(),
        product.getBrand(),
        product.getManufacturer(),
        product.getSalesPrice(),
        product.getStockQuantity(),
        product.getCreatedAt(),
        product.getUpdatedAt()
    );
  }
}
