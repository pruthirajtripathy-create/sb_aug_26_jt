package com.jt.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;


@SpringBootApplication
public class HelloApplication {

	public static void main(String[] args) {
	 ApplicationContext context = SpringApplication.run(HelloApplication.class, args);
     Greet greet=context.getBean(Greet.class);
	 greet.sayHello();
		System.out.println( "Hello JavaSpring");
	}
    

	
}
