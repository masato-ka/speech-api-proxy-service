package ka.masato.speech.api.client.interceptor;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import ka.masato.speech.api.client.BingRecogTokenManager;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class TokenHeaderInterceptor implements ClientHttpRequestInterceptor {

	private BingRecogTokenManager bingRecogTokenManager;
	
	public TokenHeaderInterceptor(BingRecogTokenManager bingRecogTokenManager){
		this.bingRecogTokenManager = bingRecogTokenManager;
	}
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {

		request.getHeaders().add("Authorization", "Bearer "+bingRecogTokenManager.getAuthenticationToken());
		
		
		ClientHttpResponse response = execution.execute(request, body);
	    //Override response header because Content-Type in response header from Bing API is always text/plain(ioi).
		response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
		return response;
	}

}
