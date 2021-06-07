package br.com.lumean.enews.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

data class Article (
    val title : String = "",
    val caption : String = "",
    val content : String = "",
    val imageUrl : String = "",
    val publishedDate: String = ""
): Serializable
