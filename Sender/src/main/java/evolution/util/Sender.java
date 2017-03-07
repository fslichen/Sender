package evolution.util;

import java.util.concurrent.Future;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sender {
	public static Gson gson;
	
	static {
		gson = new Gson();
	}
	
	@SuppressWarnings("deprecation")
	public String getString(Future<HttpResponse> response) {
		try {
			return IOUtils.toString(response.get().getEntity().getContent());
		} catch (Exception e) {
			log.error("Failed to convert response into string. The complete trace is {}", e);
			return null;
		}
	}
	
	public <T> T getDto(Future<HttpResponse> response, Class<T> clazz) {
		return gson.fromJson(getString(response), clazz);
	}
	
	public Future<HttpResponse> get(String url) {
		try {
			CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
			httpclient.start();
			HttpGet get = new HttpGet(url);
			return httpclient.execute(get, null);
		} catch (Exception e) {
			log.error("HTTP GET has encountered a problem. The complete trace is {}", e);
			return null;
		}
	}
	
	public Future<HttpResponse> post(String url, Object dto) {
		try {
			CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
			httpclient.start();
			HttpPost post = new HttpPost(url);
			post.setHeader("content-type", "application/json");
			post.setEntity(new StringEntity(gson.toJson(dto)));
			return httpclient.execute(post, null);
		} catch (Exception e) {
			log.error("HTTP POST has encountered a problem. The complete trace is {}", e);
			return null;
		}
	}
	
	public Future<HttpResponse> patch(String url, Object dto) {
		try {
			CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
			httpclient.start();
			HttpPatch patch = new HttpPatch(url);
			patch.setHeader("content-type", "application/json");
			patch.setEntity(new StringEntity(gson.toJson(dto)));
			return httpclient.execute(patch, null);
		} catch (Exception e) {
			log.error("HTTP PATCH has encountered a problem. The complete trace is {}", e);
			return null;
		}
	}
	
	public Future<HttpResponse> delete(String url) {
		try {
			CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
			httpclient.start();
			HttpDelete delete = new HttpDelete(url);
			return httpclient.execute(delete, null);
		} catch (Exception e) {
			log.error("HTTP DELETE has encountered a problem. The complete trace is {}", e);
			return null;
		}
	}
}
