package ka.masato.speech.api.client.interceptor;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthRequestHeaderInterceptor implements ClientHttpRequestInterceptor {

	@Value("${bing.recog.api.subscription}")
	private String subscriptionKey;
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		
		request.getHeaders().add("Ocp-Apim-Subscription-Key", subscriptionKey);
		request.getHeaders().add("Content-Type", "application/x-www-form-urlencoded");
		request.getHeaders().add("Content-Length","0");
							
		return execution.execute(request, body);
	}

}
