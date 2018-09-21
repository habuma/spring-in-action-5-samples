package tacos.email;

import java.util.List;

import lombok.Data;

@Data
public class Taco {

  private final String name;
  private List<String> ingredients;
  
}
