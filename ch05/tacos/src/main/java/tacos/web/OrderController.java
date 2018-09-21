package tacos.web;
import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import tacos.Order;
import tacos.User;
import tacos.data.OrderRepository;

// tag::OrderController_base[]

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

  private OrderRepository orderRepo;

//end::OrderController_base[]

  // tag::ordersForUser_paged_withHolder[]
  private OrderProps props;

  public OrderController(OrderRepository orderRepo,
          OrderProps props) {
    this.orderRepo = orderRepo;
    this.props = props;
  }
  // end::ordersForUser_paged_withHolder[]

  @GetMapping("/current")
  public String orderForm(@AuthenticationPrincipal User user,
      @ModelAttribute Order order) {
    if (order.getDeliveryName() == null) {
      order.setDeliveryName(user.getFullname());
    }
    if (order.getDeliveryStreet() == null) {
      order.setDeliveryStreet(user.getStreet());
    }
    if (order.getDeliveryCity() == null) {
      order.setDeliveryCity(user.getCity());
    }
    if (order.getDeliveryState() == null) {
      order.setDeliveryState(user.getState());
    }
    if (order.getDeliveryZip() == null) {
      order.setDeliveryZip(user.getZip());
    }

    return "orderForm";
  }

  @PostMapping
  public String processOrder(@Valid Order order, Errors errors,
      SessionStatus sessionStatus,
      @AuthenticationPrincipal User user) {

    if (errors.hasErrors()) {
      return "orderForm";
    }

    order.setUser(user);

    orderRepo.save(order);
    sessionStatus.setComplete();

    return "redirect:/";
  }

  /*
  //tag::ordersForUser_paged_withHolder[]

    ...

  //end::ordersForUser_paged_withHolder[]
  
   */

  // tag::ordersForUser_paged_withHolder[]
  @GetMapping
  public String ordersForUser(
      @AuthenticationPrincipal User user, Model model) {

    Pageable pageable = PageRequest.of(0, props.getPageSize());
    model.addAttribute("orders",
        orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));

    return "orderList";
  }
  // end::ordersForUser_paged_withHolder[]

  /*
  // tag::ordersForUser_paged[]
  @GetMapping
  public String ordersForUser(
      @AuthenticationPrincipal User user, Model model) {

    Pageable pageable = PageRequest.of(0, 20);
    model.addAttribute("orders",
        orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));

    return "orderList";
  }
  // end::ordersForUser_paged[]

   */

  /*
  // tag::ordersForUser[]
  @GetMapping
  public String ordersForUser(
      @AuthenticationPrincipal User user, Model model) {

    model.addAttribute("orders",
        orderRepo.findByUserOrderByPlacedAtDesc(user));

    return "orderList";
  }
  // end::ordersForUser[]

   */

  /*
  //tag::OrderController_base[]

    ...

  //end::OrderController_base[]

   */


//tag::OrderController_base[]
}
//end::OrderController_base[]
