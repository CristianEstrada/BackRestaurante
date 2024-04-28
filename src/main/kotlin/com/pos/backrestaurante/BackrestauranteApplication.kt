package com.pos.backrestaurante

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class BackrestauranteApplication

fun main(args: Array<String>) {
	runApplication<BackrestauranteApplication>(*args)
}

@RestController
class MessageController {
	@GetMapping("/saludo/")
	fun index(@RequestParam("name") name: String) = "Hello, $name!"
}

@RestController
class ManejandoPost{
	@GetMapping("/")
	fun index(): String {
		return "Bienvenido al main"
	}
}


