package ecommerce.api.controller.order;

import ecommerce.api.domain.payment.PaymentMethod;
import ecommerce.api.service.order.OrderItemCommand;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderRequest {

  private Long customerId;
  private List<OrderItemRequest> orderItems;
  private PaymentMethod paymentMethod;


  public List<OrderItemCommand> getOrderItemCommands() {
    return orderItems.stream()
        .map(item -> OrderItemCommand.of(item.getQuantity(), item.getProductId()))
        .collect(Collectors.toList());
  }
}
