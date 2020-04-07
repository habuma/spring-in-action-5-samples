package tacos.web.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;

import tacos.Taco;

@Configuration
public class SpringDataRestConfiguration {

  @Bean
  public RepresentationModelProcessor<PagedModel<EntityModel<Taco>>>
    tacoProcessor(EntityLinks links) {

    return new RepresentationModelProcessor<PagedModel<EntityModel<Taco>>>() {
      @Override
      public PagedModel<EntityModel<Taco>> process(
    		  PagedModel<EntityModel<Taco>> resource) {
        resource.add(
            links.linkFor(Taco.class)
                 .slash("recent")
                 .withRel("recents"));
        return resource;
      }
    };
  }
  
}
