package br.com.lumean.enews.models

import java.io.Serializable

data class Player(
    val name : String = "",
    val imageUrl : String = "",
    val twitterUrl : String = "",
    val twitterTag : String = ""
) : Serializable
