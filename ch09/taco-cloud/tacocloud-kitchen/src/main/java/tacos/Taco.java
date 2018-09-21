package tacos;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Taco {

  private String name;
  
  private Date createdAt;

  private List<Ingredient> ingredients;

}
