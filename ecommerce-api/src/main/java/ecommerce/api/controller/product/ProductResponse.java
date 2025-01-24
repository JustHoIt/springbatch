package ecommerce.api.controller.product;

import ecommerce.api.domain.product.ProductStatus;
import ecommerce.api.service.product.ProductResult;
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
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductResponse {

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


  public static ProductResponse of(String productId, Long sellerId, String category,
      String productName,
      LocalDate salesStartDate, LocalDate salesEndDate, ProductStatus productStatus, String brand,
      String manufacturer, int salesPrice, int stockQuantity, LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    return new ProductResponse(productId, sellerId, category, productName, salesStartDate,
        salesEndDate, productStatus, brand, manufacturer, salesPrice, stockQuantity, createdAt,
        updatedAt);
  }

  public static ProductResponse from(ProductResult productResult) {
    return new ProductResponse(
        productResult.getProductId(),
        productResult.getSellerId(),
        productResult.getCategory(),
        productResult.getProductName(),
        productResult.getSalesStartDate(),
        productResult.getSalesEndDate(),
        productResult.getProductStatus(),
        productResult.getBrand(),
        productResult.getManufacturer(),
        productResult.getSalesPrice(),
        productResult.getStockQuantity(),
        productResult.getCreatedAt(),
        productResult.getUpdatedAt()
    );
  }
}
