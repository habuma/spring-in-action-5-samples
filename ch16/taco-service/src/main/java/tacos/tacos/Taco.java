package tacos.tacos;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

import lombok.Data;

@Data
@Document
@RestResource(rel="tacos", path="tacos")
public class Taco {

  @Id
  private String id;
  
  private String name;
  
  private Date createdAt = new Date();

  private List<Ingredient> ingredients;

}
