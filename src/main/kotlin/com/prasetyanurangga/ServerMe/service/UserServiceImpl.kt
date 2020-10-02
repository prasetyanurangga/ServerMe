package com.prasetyanurangga.ServerMe.service

import com.prasetyanurangga.ServerMe.model.User
import com.prasetyanurangga.ServerMe.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserServiceImpl: UserService {

    @Autowired
    private lateinit var userRepository: UserRepository


    override fun createUser(user: User) : User {
        return userRepository.save(user)
    }

    override fun updateUser(user: User) : User? {
        if(userRepository.existsById(user.id)){
            return userRepository.save(user)
        }
        else{
            return null
        }
    }

    override fun deleteUser(user: User) {
        return userRepository.delete(user)
    }

    override fun findById(id: Long): Optional<User> {
        return userRepository.findById(id)
    }

    override fun getAll(): Any {
        return userRepository.findAll()
    }
}