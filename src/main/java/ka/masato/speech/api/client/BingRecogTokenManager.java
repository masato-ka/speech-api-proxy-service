package ka.masato.speech.api.client;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ka.masato.speech.api.client.interceptor.AuthRequestHeaderInterceptor;

@Component
public class BingRecogTokenManager {

	@Value("${bing.recog.api.subscription}")
	private String subScription; 
	//@Value("${azure.face.api.ServerUrl}/face/v1.0/identify")
	private String authUrl = "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";
	
	private RestTemplate restTemplate;
	
	public BingRecogTokenManager(RestTemplateBuilder restTemplateBuilder, AuthRequestHeaderInterceptor interceptors){
		restTemplate = restTemplateBuilder.additionalInterceptors(interceptors).build();
	}
	
	public String getAuthenticationToken(){
	    URI uri = UriComponentsBuilder.fromUriString(authUrl).build().toUri();	
		String result = restTemplate.postForObject(uri, null, String.class);
		return result;
	}
	
}
