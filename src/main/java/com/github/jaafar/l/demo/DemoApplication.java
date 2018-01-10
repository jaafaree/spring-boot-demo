package com.github.jaafar.l.demo;

import com.ace.cache.EnableAceCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Slf4j
@SpringBootApplication
@EnableAceCache
@EnableAspectJAutoProxy
@ComponentScan({"com.github.jaafar.l.ratelimiter", "com.github.jaafar.l.demo"})
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		log.info("============================= demo start success =============================");
	}
}
