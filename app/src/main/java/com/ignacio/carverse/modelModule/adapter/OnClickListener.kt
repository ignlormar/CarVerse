package com.ignacio.carverse.modelModule.adapter

import com.ignacio.carverse.classes.Marcas
import com.ignacio.carverse.classes.Modelos

interface OnClickListener {
    fun onCLick(marcas: Marcas)
    fun onLongClick(marcas: Marcas)
    fun onLongClick(modelos: Modelos)
    fun onClick(modelos: Modelos)
}