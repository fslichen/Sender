package evolution.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import evolution.dto.AnyDto;

@RestController
public class AnyController {
	@GetMapping("/get/{name}")
	public String get(@PathVariable("name") String name) {
		System.out.println(name);
		return "Hello World";
	}
	
	@PostMapping("/post")
	public String post(@RequestBody AnyDto dto) {
		System.out.println(dto);
		return "{'name' : 'Chen'}";
	}
}
