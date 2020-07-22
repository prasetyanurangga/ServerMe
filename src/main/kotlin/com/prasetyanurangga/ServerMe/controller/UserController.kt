package com.prasetyanurangga.ServerMe.controller

import com.prasetyanurangga.ServerMe.model.User
import com.prasetyanurangga.ServerMe.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api")
class UserController {
    @Autowired
    private lateinit var userRepository: UserRepository

    @GetMapping("/get_all")
    fun getAll(): ResponseEntity<Any>
    {
        return ResponseEntity.ok(
                userRepository.findAll()
        )
    }

    @GetMapping("/get_id_path/{id}")
    fun GetByIdPath(@PathVariable("id") id: Long): ResponseEntity<User>
    {
        return userRepository.findById(id).map { user ->
            ResponseEntity.ok(user)
        }.orElse(ResponseEntity.notFound().build())
    }

    @GetMapping("/get_id_query")
    fun GetByIdQuery(@RequestParam("id") id: Long): ResponseEntity<User>
    {
        return userRepository.findById(id).map { user ->
            ResponseEntity.ok(user)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping("/save_user")
    fun saveUser(@Valid @RequestBody user: User): ResponseEntity<Long>
    {
        return userRepository.save(user).let {
            ResponseEntity(it.id, HttpStatus.CREATED)
        }
    }

    @PostMapping("/update_user")
    fun updateUser(@Valid @RequestBody user: User): ResponseEntity<Long>
    {
        if(userRepository.existsById(user.id)){
            return userRepository.save(user).let {
                ResponseEntity(it.id, HttpStatus.CREATED)
            }
        }
        else{
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

    }

}