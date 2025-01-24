package ecommerce.api.service.order;

import ecommerce.api.domain.order.Order;
import ecommerce.api.domain.order.OrderRepository;
import ecommerce.api.domain.payment.PaymentMethod;
import ecommerce.api.service.product.ProductResult;
import ecommerce.api.service.product.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final ProductService productService;

  @Transactional
  public OrderResult order(Long customerId, List<OrderItemCommand> orderItems,
      PaymentMethod paymentMethod) {
    Order order = Order.createOrder(customerId);

    for (OrderItemCommand orderItem : orderItems) {
      ProductResult productResult = productService.findProduct(orderItem.getProductId());
      order.addOrderItem(productResult.getProductId(), orderItem.getQuantity(),
          productResult.getSalesPrice());

    }

    order.initPayment(paymentMethod);
    return save(order);
  }

  private OrderResult save(Order order) {
    return OrderResult.from(orderRepository.save(order));
  }

}
