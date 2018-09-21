package tacos.ingredients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class IngredientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IngredientServiceApplication.class, args);
	}
}
