package tacos;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Ingredient implements Persistable<String> {
  
  @Id
  private String id;
  private String name;
  private Type type;
  
  @Override
	public boolean isNew() {
		return true;
	}
  
  public static enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }

}
