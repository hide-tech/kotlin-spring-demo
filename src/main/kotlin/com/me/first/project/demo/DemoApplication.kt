package com.me.first.project.demo

import feign.Logger
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)

	@Bean
	fun feignLoggerLevel(): Logger.Level {
		return Logger.Level.FULL;
	}
}

