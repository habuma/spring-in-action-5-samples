// tag::all[]
// tag::allButValidation[]
package tacos;
import java.util.List;
// end::allButValidation[]
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
// tag::allButValidation[]
import lombok.Data;

@Data
public class Taco {

  // end::allButValidation[]
  @NotNull
  @Size(min=5, message="Name must be at least 5 characters long")
  // tag::allButValidation[]
  private String name;
  // end::allButValidation[]
  @Size(min=1, message="You must choose at least 1 ingredient")
  // tag::allButValidation[]
  private List<String> ingredients;

}
//end::allButValidation[]
//tag::end[]