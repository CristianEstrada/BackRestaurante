package com.pos.backrestaurante


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID


@SpringBootApplication
class BackrestauranteApplication

fun main(args: Array<String>) {
    runApplication<BackrestauranteApplication>(*args)
}

data class Mensaje(val id: String?, val text: String)

@RestController
class MensajeController(val service: MensajeServicio) {
    @GetMapping("/")
    fun index(): List<Mensaje> = service.BuscarMensaje()

    @GetMapping("/{id}")
    fun index(@PathVariable id: String): List<Mensaje> = service.BuscarMensajeId(id)

    @PostMapping("/")
    fun post(@RequestBody mensaje: Mensaje) {
        service.guardar(mensaje)
    }

}


@Service
class MensajeServicio(val db: JdbcTemplate) {
    fun BuscarMensaje(): List<Mensaje> = db.query("SELECT * FROM MENSAJES") { response, _ ->
        Mensaje(
            response.getString("id"),
            response.getString("text")
        )
    }

    fun BuscarMensajeId(id: String): List<Mensaje> =
        db.query("SELECT * FROM MENSAJES WHERE id = ? ", id) { response, _ ->
            Mensaje(
                response.getString("id"),
                response.getString("text")
            )
        }


    fun guardar(mensaje: Mensaje) {
        val id = mensaje.id ?: UUID.randomUUID().toString()
        db.update(
            "INSERT INTO MENSAJES VALUES ( ?, ? )",
            id, mensaje.text
        )
    }

}