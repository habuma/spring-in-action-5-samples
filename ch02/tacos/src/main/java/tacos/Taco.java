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
  //@NotNull
  @NotBlank(message="")
  @Size(min=5, message="Name must be at least 5 characters long")
  // tag::allButValidation[]
  private String name;
  // end::allButValidation[]
  @NotNull(message="You must choose at least 1 ingredient")
  //@Size(min=1, message="You must choose at least 1 ingredient")
  // tag::allButValidation[]
  private List<String> ingredients;

}
//end::allButValidation[]
//tag::end[]
