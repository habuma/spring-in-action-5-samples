package tacos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor(force=true, access=AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class PaymentMethod {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long id;
  
  @ManyToOne
  private final User user;
  private final String ccNumber;
  private final String ccCVV;
  private final String ccExpiration;
  
}
