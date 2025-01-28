package ecommerce.api.service.order;

import ecommerce.api.domain.order.Order;
import ecommerce.api.domain.order.OrderItem;
import ecommerce.api.domain.order.OrderRepository;
import ecommerce.api.domain.payment.PaymentMethod;
import ecommerce.api.service.product.ProductResult;
import ecommerce.api.service.product.ProductService;
import ecommerce.api.service.transaction.TransactionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final ProductService productService;
  private final TransactionService transactionService;

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


  @Transactional
  public OrderResult completePayment(Long orderId, boolean success) {
    Order order = findById(orderId);

    order.completePayment(success);
    decreaseStock(success, order);

    return save(order);
  }

  private Order findById(Long orderId) {
    Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new OrderNotFoundException(orderId));
    return order;
  }

  @Transactional
  public OrderResult completeOrder(Long orderId) {
    Order order = findById(orderId);
    order.complete();
    return save(order);
  }

  @Transactional
  public OrderResult cancelOrder(Long orderId) {
    Order order = findById(orderId);
    order.cancel();
    increaseStock(order);

    return save(order);
  }

  private void increaseStock(Order order) {
    for (OrderItem orderItem : order.getOrderItems()) {
      productService.increaseStock(orderItem.getProductId(), orderItem.getQuantity());
    }
  }

  private void decreaseStock(boolean success, Order order) {
    if (success) {
      for (OrderItem orderItem : order.getOrderItems()) {
        productService.decreaseStock(orderItem.getProductId(), orderItem.getQuantity());
      }
    }
  }

}
