package fundamentosBackendTF.pe.edu.upc;

import fundamentosBackendTF.pe.edu.upc.payments.config.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
//@Import({ AxonConfig.class })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
/*
	@Bean
	@LoadBalanced
	public WebClient.Builder loadBalanceWebClientBuilder(){return WebClient.builder();}
	*/

}
