package org.wecancodeit.fishes;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FishesApplication {

	@Resource
	private MyComponent foo;
	
	public static void main(String[] args) {
		SpringApplication.run(FishesApplication.class, args);
	}
//
//	@Bean
//	public MyComponent createdWithBean() {
//		// do stuff to set up my component
//		return new MyComponent();
//	}
}
