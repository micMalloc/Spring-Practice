package kr.heesu.practice.webflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class WebFluxPracticeApplication implements ApplicationRunner {

    public static void main(String[] args) {
//        SpringApplication.exit(SpringApplication.run(WebFluxPracticeApplication.class, args));
        SpringApplication.run(WebFluxPracticeApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
//        HttpClient httpClient = HttpClient.newConnection()
//            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
//            .responseTimeout(Duration.ofMillis(5000));
//
//        WebClient client = WebClient.builder()
//            .clientConnector(new ReactorClientHttpConnector(httpClient))
//            .build();
//
//        int count = Integer.parseInt(args.getOptionValues("requests").get(0));
//        log.info("count = " + count);
//
//        MultipartBodyBuilder builder = new MultipartBodyBuilder();
//        builder.part("mode", "upload");
//        builder.part("file_key","BigImageInternalTest");
//        builder.part("file_url","https://file-examples-com.github.io/uploads/2017/10/file_example_PNG_3MB.png");
//        MultiValueMap<String, HttpEntity<?>> body = builder.build();
//
//        long start = System.currentTimeMillis();
//
//        Flux.range(0, count)
//            .flatMap(prodNo -> client.post()
//                .uri("http://localhost:8080/File/Receiver")
//                .contentType(MediaType.MULTIPART_FORM_DATA)
//                .body(BodyInserters.fromMultipartData(body))
//                .retrieve()
//                .toBodilessEntity())
//            .collectList().block();
//
//        long end = System.currentTimeMillis();
//
//        log.info("수행시간: " + (end - start) + " ms");
    }
}
