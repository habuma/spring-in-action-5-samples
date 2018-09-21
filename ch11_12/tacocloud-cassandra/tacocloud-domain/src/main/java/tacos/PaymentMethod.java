package tacos;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.utils.UUIDs;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Table("payment_method")
@Data
@NoArgsConstructor(force=true, access=AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class PaymentMethod {

  @PrimaryKeyColumn(type=PrimaryKeyType.PARTITIONED)
  private UUID id = UUIDs.timeBased();
  
  private final UserUDT user;
  private final String ccNumber;
  private final String ccCVV;
  private final String ccExpiration;
  
}
