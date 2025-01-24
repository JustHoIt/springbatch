package ecommerce.api.service.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderItemCommand {

  private Integer quantity;
  private String productId;

  public static OrderItemCommand of(Integer quantity, String productId) {
    return new OrderItemCommand(quantity, productId);
  }
}
