package tacos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)    // <1>
@SpringBootTest                 // <2>
public class TacoCloudApplicationTests {

  @Test                         // <3>
  public void contextLoads() {
  }

}
