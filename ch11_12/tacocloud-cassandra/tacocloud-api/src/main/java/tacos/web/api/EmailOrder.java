package tacos.web.api;

import java.util.List;

import lombok.Data;

@Data
public class EmailOrder {

  private String email;
  private List<EmailTaco> tacos;
  
  @Data
  public static class EmailTaco {
    private String name;
    private List<String> ingredients;
  }
  
}
