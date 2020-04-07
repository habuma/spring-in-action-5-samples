package tacos.web.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tacos.Taco;
import tacos.data.TacoRepository;

@RestController
@RequestMapping(path="/design",                      // <1>
                produces="application/json")
@CrossOrigin(origins="*")        // <2>
public class DesignTacoController {
  private TacoRepository tacoRepo;
  
  @Autowired
  EntityLinks entityLinks;

  public DesignTacoController(TacoRepository tacoRepo) {
    this.tacoRepo = tacoRepo;
  }

  @GetMapping("/recent")
  public Iterable<Taco> recentTacos() {                 //<3>
    PageRequest page = PageRequest.of(
            0, 12, Sort.by("createdAt").descending());
    return tacoRepo.findAll(page).getContent();
  }
  
  /*
  @GetMapping("/recent")
  public CollectionModel<EntityModel<Taco>> recentTacos() {
    PageRequest page = PageRequest.of(
            0, 12, Sort.by("createdAt").descending());

    List<Taco> tacos = tacoRepo.findAll(page).getContent();
    CollectionModel<EntityModel<Taco>> recentResources = 
    	CollectionModel.wrap(tacos);

    recentResources.add(
        new Link("http://localhost:8080/design/recent", "recents"));
    return recentResources;
  }
  */
  
  
  /*
  @GetMapping("/recent")
  public CollectionModel<EntityModel<Taco>> recentTacos() {
    PageRequest page = PageRequest.of(
            0, 12, Sort.by("createdAt").descending());

    List<Taco> tacos = tacoRepo.findAll(page).getContent();

    CollectionModel<EntityModel<Taco>> recentResources = CollectionModel.wrap(tacos);
    recentResources.add(
        WebMvcLinkBuilder.linkTo(DesignTacoController.class)
    					 .slash("recent")
    					 .withRel("recents"));
    return recentResources;
  }
   */
  
  /*
  @GetMapping("/recent")
  public CollectionModel<EntityModel<Taco>> recentTacos() {
    PageRequest page = PageRequest.of(
            0, 12, Sort.by("createdAt").descending());

    List<Taco> tacos = tacoRepo.findAll(page).getContent();

    CollectionModel<EntityModel<Taco>> recentResources = CollectionModel.wrap(tacos);
    recentResources.add(
        linkTo(methodOn(DesignTacoController.class).recentTacos())
			 .withRel("recents"));
    return recentResources;
  }
  */
   

//  @GetMapping("/recenth")
//  public Resources<TacoResource> recentTacosH() {
//    PageRequest page = PageRequest.of(
//            0, 12, Sort.by("createdAt").descending());
//    List<Taco> tacos = tacoRepo.findAll(page).getContent();
//    
//    List<TacoResource> tacoResources = 
//        new TacoResourceAssembler().toResources(tacos);
//    Resources<TacoResource> recentResources = 
//        new Resources<TacoResource>(tacoResources);
//    recentResources.add(
//        linkTo(methodOn(DesignTacoController.class).recentTacos())
//        .withRel("recents"));
//    return recentResources;
//  }

  
  
//ControllerLinkBuilder.linkTo(DesignTacoController.class)
//.slash("recent")
//.withRel("recents"));

  
  
  
//  @GetMapping("/recenth")
//  public Resources<TacoResource> recenthTacos() {
//    PageRequest page = PageRequest.of(
//            0, 12, Sort.by("createdAt").descending());
//    List<Taco> tacos = tacoRepo.findAll(page).getContent();
//
//    List<TacoResource> tacoResources = new TacoResourceAssembler().toResources(tacos);
//    
//    Resources<TacoResource> tacosResources = new Resources<>(tacoResources);
////    Link recentsLink = ControllerLinkBuilder
////        .linkTo(DesignTacoController.class)
////        .slash("recent")
////        .withRel("recents");
//
//    Link recentsLink = 
//        linkTo(methodOn(DesignTacoController.class).recentTacos())
//        .withRel("recents");
//    tacosResources.add(recentsLink);
//    return tacosResources;
//  }
  
  @PostMapping(consumes="application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Taco postTaco(@RequestBody Taco taco) {
    return tacoRepo.save(taco);
  }
  
  @GetMapping("/{id}")
  public Taco tacoById(@PathVariable("id") Long id) {
    Optional<Taco> optTaco = tacoRepo.findById(id);
    if (optTaco.isPresent()) {
      return optTaco.get();
    }
    return null;
  }

  /*
  @GetMapping("/{id}")
  public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
    Optional<Taco> optTaco = tacoRepo.findById(id);
    if (optTaco.isPresent()) {
      return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }
  */

  
}

