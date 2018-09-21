package tacos.web.api;

import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tacos.Order;
import tacos.data.OrderRepository;
import tacos.messaging.OrderMessagingService;

@RestController
@RequestMapping(path="/orders",
                produces="application/json")
@CrossOrigin(origins="*")
public class OrderApiController {

  private OrderRepository repo;
  private OrderMessagingService orderMessages;
  private EmailOrderService emailOrderService;

  public OrderApiController(OrderRepository repo,
                            OrderMessagingService orderMessages,
                            EmailOrderService emailOrderService) {
    this.repo = repo;
    this.orderMessages = orderMessages;
    this.emailOrderService = emailOrderService;
  }

  @GetMapping(produces="application/json")
  public Flux<Order> allOrders() {
    return repo.findAll();
  }

//  @PostMapping(consumes="application/json")
//  @ResponseStatus(HttpStatus.CREATED)
//  public Mono<Order> postOrder(@RequestBody Mono<Order> order) {
//    order.subscribe(orderMessages::sendOrder); // TODO: not ideal...work into reactive flow below
//    return order
//        .flatMap(repo::save);
//  }

  @PostMapping(consumes="application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Order> postOrder(@RequestBody Order order) {
    orderMessages.sendOrder(order);
    return repo.save(order);
  }

  @PostMapping(path="fromEmail", consumes="application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Order> postOrderFromEmail(@RequestBody Mono<EmailOrder> emailOrder) {
    Mono<Order> order = emailOrderService.convertEmailOrderToDomainOrder(emailOrder);
    order.subscribe(orderMessages::sendOrder); // TODO: not ideal...work into reactive flow below
    return order
        .flatMap(repo::save);
  }

  @PutMapping(path="/{orderId}", consumes="application/json")
  public Mono<Order> putOrder(@RequestBody Mono<Order> order) {
    return order.flatMap(repo::save);
  }

  @PatchMapping(path="/{orderId}", consumes="application/json")
  public Mono<Order> patchOrder(@PathVariable("orderId") UUID orderId,
                          @RequestBody Order patch) {

    return repo.findById(orderId)
        .map(order -> {
          if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
          }
          if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
          }
          if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
          }
          if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
          }
          if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryState());
          }
          if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
          }
          if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
          }
          if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
          }
          return order;
        })
        .flatMap(repo::save);
  }

  @DeleteMapping("/{orderId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteOrder(@PathVariable("orderId") UUID orderId) {
    try {
      repo.deleteById(orderId);
    } catch (EmptyResultDataAccessException e) {}
  }

}
