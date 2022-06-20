package fundamentosBackendTF.pe.edu.upc.payments.query.api;

import fundamentosBackendTF.pe.edu.upc.payments.query.dto.Post;
import fundamentosBackendTF.pe.edu.upc.payments.query.projections.PaymentView;
import fundamentosBackendTF.pe.edu.upc.payments.query.projections.PaymentViewRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/*
@RestController
@RequestMapping("/payments")
@Tag(name="Payments")
public class PaymentQueryController {
    private final PaymentViewRepository paymentViewRepository;
    @Autowired
    private ReactiveCircuitBreakerFactory circuitBreakerFactory;
    @Autowired
    private WebClient.Builder webClientBuilder;
    public PaymentQueryController(PaymentViewRepository paymentViewRepository){
        this.paymentViewRepository = paymentViewRepository;
    }
    @GetMapping("")
    @Operation(summary="Get all payments")
    public ResponseEntity<List<PaymentView>> getAll(){
        try{
            List<PaymentView> payments = paymentViewRepository.findAll().stream().map(payment->{
                Post post = getPost(payment).block();
                payment.setPostId(post.getPostId());
                return payment;
            }).collect(Collectors.toList());
            return new ResponseEntity<List<PaymentView>>(payments, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get account by id")
    public ResponseEntity<PaymentView> getById(@PathVariable("id") String id) {
        try {
            Optional<PaymentView> paymentViewOptional = paymentViewRepository.findById(id);
            if (paymentViewOptional.isPresent()) {
                PaymentView paymentView = paymentViewOptional.get();
                Post post= getPost(paymentView).block();
                paymentView.setPostId(post.getPostId());

                return new ResponseEntity<PaymentView>(paymentView, HttpStatus.OK);
            }
            return new ResponseEntity("NOT_FOUND", HttpStatus.NOT_FOUND);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Mono<Post> getPost(PaymentView payment) {
        ReactiveCircuitBreaker reactiveCircuitBreaker = circuitBreakerFactory.create("clientCircuitBreaker");
        String customerEndpoint = "http://localhost:8080/posts/id/" + payment.getPostId();
        Mono<Post> customer = reactiveCircuitBreaker.run(webClientBuilder.build().get()
                        .uri(customerEndpoint)
                        .retrieve()
                        .bodyToMono(Post.class)
                , throwable -> getDefaultPost());
        return customer;
    }
    private Mono<Post>getDefaultPost(){
        System.out.println("Fallback method called");
        return Mono.just(new Post("none"));
    }
}

 */
