package com.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeanLifeCycleApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(BeanLifeCycleApplication.class, args);
		var greet = context.getBean(Greet.class);
		greet.greet();
	}

/*
Bean Life cycle

1.bean instantiated - Object is created - Constructor
2.Dependency injected (if available)    - by using any way of DI
3.Bean initialized                      - @PostConstruct
4.Bean used
5.Bean Destroyed                        - @PreDestroy

- managed by spring Container


*/


}
