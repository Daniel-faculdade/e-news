package br.com.lumean.enews.models;

import java.io.Serializable


data class Team (
    val name : String = "",
    val imageUrl : String = "",
    val twitterUrl : String = "",
    val backgroundUrl : String = "",
    val players : List<Player> = emptyList()
) : Serializable