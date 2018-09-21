package sia5;

import org.junit.Ignore;
import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class FluxLoggingTests {

  @Test
  @Ignore
  public void logSimple() {
    Flux<String> beltColors = Flux.just(
        "white", "yellow", "orange", "green", "purple", "blue")
      .log();
    beltColors.subscribe();
  }
  
  @Test
  @Ignore
  public void logMapping() {
    Flux<String> beltColors = Flux.just(
        "white", "yellow", "orange", "green", "purple", "blue")
      .map(cb -> cb.toUpperCase())
      .log()
      ;
    beltColors.subscribe(); 
  }
  
  @Test
  public void logFlatMapping() throws Exception {
    Flux<String> beltColors = Flux.just(
        "white", "yellow", "orange", "green", "purple", "blue")
      .flatMap(cb -> Mono.just(cb)
          .map(c -> c.toUpperCase())
          .log()
          .subscribeOn(Schedulers.parallel())
      )
      ;
    beltColors.subscribe(); 
    
    Thread.sleep(3000L);
  }
  
}
