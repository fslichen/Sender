package evolution.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
	public AnyDto post(@RequestBody AnyDto dto) {
		System.out.println(dto);
		return dto;
	}
	
	@PatchMapping("/patch")
	public Map<Integer, String> patch() {
		Map<Integer, String> map = new LinkedHashMap<>();
		map.put(0, "Bob");
		map.put(0, "Ann");
		return map;
	}
}
