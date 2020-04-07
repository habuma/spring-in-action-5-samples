package tacos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) // <1>
@SpringBootTest                 // <2>
public class TacoCloudApplicationTests {

  @Test                         // <3>
  public void contextLoads() {
  }

}
