package com.prasetyanurangga.ServerMe.model

import javax.persistence.*


@Entity
@Table(name = "tbl_user")
data class User (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id", nullable = false, updatable = false)
        val id: Long = 0,

        @Column(name = "user_name", nullable = false, unique = true)
        val username: String = "",

        @Column(name = "user_pass", nullable = false)
        val password: String = ""
)