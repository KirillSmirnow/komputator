package komputator.desktop.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate(@Value("${komputator.server-base-url}") String serverBaseUrl) {
        return new RestTemplateBuilder()
                .rootUri(serverBaseUrl)
                .errorHandler(new DefaultResponseErrorHandler() {
                    @Override
                    public void handleError(ClientHttpResponse response) throws IOException {
                        try (var reader = new BufferedReader(new InputStreamReader(response.getBody()))) {
                            var message = reader.readLine();
                            throw new RuntimeException(message);
                        }
                    }
                })
                .build();
    }
}
