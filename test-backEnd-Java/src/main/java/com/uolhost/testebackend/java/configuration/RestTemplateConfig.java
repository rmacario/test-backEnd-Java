package com.uolhost.testebackend.java.configuration;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	
	private final TrustManager[] trustManager;
	private final SSLContext sc;
	
	@Bean
	public RestTemplate generate() {
		return new RestTemplate();
	}
	
	// Necessario para ignorar o certificado ssl
	public RestTemplateConfig() throws NoSuchAlgorithmException, KeyManagementException {
		this.trustManager = new TrustManager[] {new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}
		}};
		
		this.sc = SSLContext.getInstance("SSL");
		
		this.sc.init(null, trustManager, new SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(this.sc.getSocketFactory());
	}
}
