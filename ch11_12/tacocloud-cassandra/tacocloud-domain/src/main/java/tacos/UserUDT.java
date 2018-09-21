package tacos;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.Data;

@UserDefinedType("user")
@Data
public class UserUDT {
  
  private final String username;
  private final String fullname;
  private final String phoneNumber;
  
}