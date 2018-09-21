package sia5;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class SimpleFlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleFlowApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner writeData(FileWriterGateway gateway, Environment env) {
	  return args -> {
	    String[] activeProfiles = env.getActiveProfiles();
	    if (activeProfiles.length > 0) {
	      String profile = activeProfiles[0];
	      gateway.writeToFile("simple.txt", "Hello, Spring Integration! (" + profile + ")");
	    } else {
	      System.out.println("No active profile set. Should set active profile to one of xmlconfig, javaconfig, or javadsl.");
	    }
	  };
	}
	
}
