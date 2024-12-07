package com.ignacio.carverse.classes

data class Fichas(
    val idFicha: Long,
    val idModelo: Long,
    val modelo: String,
    val anyos: String,
    val potencia: String,
    val traccion: String,
    val consumo: String,
    val velocidadMaxima: String,
    val imagen: String,
    val precio: String
)
