package miu.edu.cs473.lab5.model

import java.io.Serializable

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
) : Serializable