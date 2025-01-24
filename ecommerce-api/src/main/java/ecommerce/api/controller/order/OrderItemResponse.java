package ecommerce.api.controller.order;


import ecommerce.api.service.order.OrderItemResult;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderItemResponse {

  private Long orderItemId;
  private Integer quantity;
  private Integer unitPrice;
  private String productId;

  public static OrderItemResponse from(OrderItemResult orderItemResult) {
    return new OrderItemResponse(orderItemResult.getOrderItemId(), orderItemResult.getQuantity(),
        orderItemResult.getUnitPrice(), orderItemResult.getProductId());
  }

}
