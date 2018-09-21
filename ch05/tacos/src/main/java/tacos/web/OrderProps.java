// tag::validated[]
// tag::notValidated[]
package tacos.web;
// end::notValidated[]
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

//tag::notValidated[]
import org.springframework.boot.context.properties.
                                        ConfigurationProperties;
import org.springframework.stereotype.Component;
//end::notValidated[]
import org.springframework.validation.annotation.Validated;

//tag::notValidated[]
import lombok.Data;

@Component
@ConfigurationProperties(prefix="taco.orders")
@Data
// end::notValidated[]
@Validated
//tag::notValidated[]
public class OrderProps {
  
//end::notValidated[]
  @Min(value=5, message="must be between 5 and 25")
  @Max(value=25, message="must be between 5 and 25")
//tag::notValidated[]
  private int pageSize = 20;

}
//end::notValidated[]
//end::validated[]