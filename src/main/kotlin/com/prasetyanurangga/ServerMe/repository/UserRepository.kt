package com.prasetyanurangga.ServerMe.repository

import com.prasetyanurangga.ServerMe.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CrudRepository<User, Long>