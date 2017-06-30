package com.shang.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
		// 使用{}占位符。避免字符串连接操作，减少String对象（不可变）带来的内存开销
		log.info("--------------------------服务{}启动","DemoApplication");
		SpringApplication.run(DemoApplication.class, args);
	}
}
