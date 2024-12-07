package com.ignacio.carverse.modelModule.adapter

import com.ignacio.carverse.classes.Marcas
import com.ignacio.carverse.classes.Modelos

interface OnClickListener {
    fun onClick(modelo: Modelos)
    fun onLongCLick(modelo: Modelos)
    fun onClick(marca: Marcas)
    fun onLongCLick(marca: Marcas)

}