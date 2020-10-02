package com.prasetyanurangga.ServerMe.controller

import com.prasetyanurangga.ServerMe.exception.UserExpectionController
import com.prasetyanurangga.ServerMe.exception.UserNotFoundException
import com.prasetyanurangga.ServerMe.model.User
import com.prasetyanurangga.ServerMe.repository.UserRepository
import com.prasetyanurangga.ServerMe.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import org.springframework.web.client.getForEntity
import java.util.*
import javax.validation.Valid


@RestController
@RequestMapping("/api")
class UserController {
    private val logger: Logger = LoggerFactory.getLogger(UserController::class.java)

    @Autowired
    private lateinit var userService: UserService


    var restTemplate: RestTemplate = RestTemplate()

    @GetMapping("/get_all")
    fun getAll(): ResponseEntity<Any>
    {
        logger.info(userService.getAll().toString());
        return ResponseEntity.ok(
                userService.getAll()
        )
    }

    @GetMapping("/get_id_path/{id}")
    fun GetByIdPath(@PathVariable("id") id: Long): ResponseEntity<User>
    {
        return userService.findById(id).map { user ->
            ResponseEntity.ok(user)
        }.orElse(ResponseEntity.notFound().build())
    }

    @GetMapping("/get_id_query")
    fun GetByIdQuery(@RequestParam("id") id: Long): ResponseEntity<User>
    {
        return userService.findById(id).map { user ->
            ResponseEntity.ok(user)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping("/save_user")
    fun saveUser(@Valid @RequestBody user: User): ResponseEntity<Long>
    {
        return userService.createUser(user).let {
            ResponseEntity(it.id, HttpStatus.CREATED)
        }
    }

    @PostMapping("/update_user")
    fun updateUser(@Valid @RequestBody user: User): ResponseEntity<Long>
    {
        val updateUser = userService.updateUser(user)
        if(updateUser !=  null){
            return ResponseEntity(updateUser.id, HttpStatus.CREATED)
        }
        else{
            throw UserNotFoundException(message = "User Tidak Ada")
        }

    }

    @PostMapping("/remove_user")
    fun removeUser(@Valid @RequestBody user: User): ResponseEntity<String>
    {
        return userService.deleteUser(user).let {
            ResponseEntity("Delete", HttpStatus.CREATED)
        }

    }

    @GetMapping("/consume_user")
    fun comsumeUser(): ResponseEntity<User>
    {
        val header : HttpHeaders = HttpHeaders()
        header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON))
        val entity : HttpEntity<String> = HttpEntity<String>(header)
        return restTemplate.getForEntity("http://localhost:9091/get_all", String::class)
    }

}