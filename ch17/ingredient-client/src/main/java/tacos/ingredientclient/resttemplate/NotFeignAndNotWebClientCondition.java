package tacos.ingredientclient.resttemplate;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class NotFeignAndNotWebClientCondition implements Condition {

  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    return !context.getEnvironment().acceptsProfiles("feign") 
        && !context.getEnvironment().acceptsProfiles("webclient");
  }
  
}
