package tacos.web.api;

import java.util.List;

import org.springframework.hateoas.CollectionModel;

public class TacoCollectionModel extends CollectionModel<TacoEntityModel> {
  public TacoCollectionModel(List<TacoEntityModel> tacoResources) {
    super(tacoResources);
  }
}
