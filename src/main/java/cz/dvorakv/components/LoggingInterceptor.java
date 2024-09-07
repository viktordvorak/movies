package cz.dvorakv.components;

import jakarta.validation.Payload;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    private void log(final HttpRequest request, final byte[] content, final ClientHttpResponse response) throws IOException {
        val message = new StringBuilder(String.format("{} uri={} status={}", request.getMethod(), request.getURI(), getStatus(response)));
        log.info(message.toString());
    }

    private String getStatus(final ClientHttpResponse response) throws IOException {
        return response.getStatusCode().toString();
    }


    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        val response = execution.execute(request, body);
        log(request, body, response);
        return response;
    }
}
