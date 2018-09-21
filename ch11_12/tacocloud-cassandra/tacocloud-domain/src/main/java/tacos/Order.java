package tacos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.utils.UUIDs;

import lombok.Data;

@Data
@Table("tacoorders")
public class Order implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @PrimaryKey
  private UUID id = UUIDs.timeBased();
  private Date placedAt = new Date();
  
  @Column("user")
  private UserUDT user;

  private String deliveryName;

  private String deliveryStreet;

  private String deliveryCity;

  private String deliveryState;

  private String deliveryZip;

  private String ccNumber;

  private String ccExpiration;

  private String ccCVV;


  @Column("tacos")
  private List<TacoUDT> tacos = new ArrayList<>();
  
  public void addDesign(TacoUDT design) {
    this.tacos.add(design);
}

}
