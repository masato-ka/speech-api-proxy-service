package ka.masato.speech.api.client;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ka.masato.speech.api.client.interceptor.AuthRequestHeaderInterceptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BingRecogTokenManager {

	@Value("${bing.recog.api.subscription}")
	private String subScription; 
	@Value("${bing.auth.url:https://api.cognitive.microsoft.com/sts/v1.0/issueToken}")
	private String authUrl;
	
	private RestTemplate restTemplate;
	
	public BingRecogTokenManager(RestTemplateBuilder restTemplateBuilder, AuthRequestHeaderInterceptor interceptors){
		restTemplate = restTemplateBuilder.additionalInterceptors(interceptors).build();
	}
	
	@Cacheable(value="bingJwtToken")
	public String getAuthenticationToken(){
	    URI uri = UriComponentsBuilder.fromUriString(authUrl).build().toUri();	
		String result = restTemplate.postForObject(uri, null, String.class);
		return result;
	}

	@CacheEvict(value="bingJwtToken")
	public void clearCacheTokenInfo() {
		log.info("Rfresh token cache.");
		// Clear token info from cache.
		//This implementation process transfer CacheEvict
	}
	
}
