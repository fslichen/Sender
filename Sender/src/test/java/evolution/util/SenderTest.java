package evolution.util;

import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.junit.Test;

import evolution.dto.AnyDto;

public class SenderTest {
	@Test
	public void testGet() {
		Sender sender = new Sender();
		Future<HttpResponse> response = sender.get("http://localhost:8080/get/anyName");
		String str = sender.getString(response);
		System.out.println(str);
	}
	
	@Test
	public void testPost() {
		Sender sender = new Sender();
		Future<HttpResponse> response = sender.post("http://localhost:8080/post", new AnyDto("Chen"));
		AnyDto anyDto = sender.getDto(response, AnyDto.class);
		System.out.println(anyDto);
	}
}
