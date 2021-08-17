package com.example.apicall;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.example.util.RequestMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class APIRequest {

	private String apiEndPoint;
	private RequestMethod httpRequest;

	public APIRequest(String apiEndPoint, RequestMethod httpRequest) {
		this.apiEndPoint = apiEndPoint;
		this.httpRequest = httpRequest;
	}

	public String getAPIEndPoint() {
		return this.apiEndPoint;
	}

	public RequestMethod getRequestMethod() {
		return httpRequest;
	}

	public JsonObject run(String host, String apiKey) throws Exception {

		String requestURL = host + getAPIEndPoint() + "&appid=" + apiKey;

		String json = "";

		StringEntity postingString;

		switch (getRequestMethod()) {
			case POST:
				HttpPost post = new HttpPost(requestURL);
				postingString = new StringEntity(json, "UTF-8");
				post.setEntity(postingString);
				post.setHeader("Content-type", "application/json");
				return performRequest(post);
			case PUT:
				HttpPut put = new HttpPut(requestURL);
				postingString = new StringEntity(json, "UTF-8");
				put.setEntity(postingString);
				put.setHeader("Content-type", "application/json");
				return performRequest(put);
			case GET:
				HttpGet get = new HttpGet(requestURL);
				get.setHeader("Content-type", "application/json");
				return performRequest(get);
			default:
				throw new Exception("Error: invalid request type");
		}
	}

	private JsonObject performRequest(HttpUriRequest httpUriRequest) throws IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpResponse response = httpClient.execute(httpUriRequest);
		String result = EntityUtils.toString(response.getEntity());
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(result);
		return element.getAsJsonObject();
	}

}
