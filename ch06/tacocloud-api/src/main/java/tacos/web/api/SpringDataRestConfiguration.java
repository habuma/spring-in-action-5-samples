package tacos.web.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

import tacos.Taco;

@Configuration
public class SpringDataRestConfiguration {

  @Bean
  public ResourceProcessor<PagedResources<Resource<Taco>>>
    tacoProcessor(EntityLinks links) {

    return new ResourceProcessor<PagedResources<Resource<Taco>>>() {
      @Override
      public PagedResources<Resource<Taco>> process(
                          PagedResources<Resource<Taco>> resource) {
        resource.add(
            links.linkFor(Taco.class)
                 .slash("recent")
                 .withRel("recents"));
        return resource;
      }
    };
  }
  
}
