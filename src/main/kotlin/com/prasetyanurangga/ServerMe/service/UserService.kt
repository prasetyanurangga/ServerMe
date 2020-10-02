package com.prasetyanurangga.ServerMe.service

import com.prasetyanurangga.ServerMe.model.User
import java.util.*

interface UserService {
    fun createUser(user: User) : User
    fun updateUser(user: User) : User?
    fun deleteUser(user: User)
    fun findById(id: Long): Optional<User>
    fun getAll() : Any
}