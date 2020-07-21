package com.CTLI.FirebaseCloudMessaging.Service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PushNotiService {
	private static final String FIREBASE_SERVER_KEY = "AAAAScxITv8:APA91bFEnMz_yHCDY4iXOvJB2kHP-WhFurjZ0pvyvohVcr9UcvoNLQT6qLzljUNbgWR1pseoGf0vUQbCIzwdPRjmE73niVko4z8P7eBGN0k0-BqDkx8niBQ81fftCjXTmvU00ipBL0Kh";
	private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
	
	@Async
	public CompletableFuture<String> send(HttpEntity<String> entity) {

		RestTemplate restTemplate = new RestTemplate();

		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		restTemplate.setInterceptors(interceptors);

		String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);

		return CompletableFuture.completedFuture(firebaseResponse);
	}
}