package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller            // <1>
public class HomeController {

  @GetMapping("/")     // <2>
  public String home() {
    return "home";     // <3>
  }

}
