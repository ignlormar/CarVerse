package com.ignacio.carverse.brandModule.adapter

import com.ignacio.carverse.classes.Categorias
import com.ignacio.carverse.classes.Marcas

interface OnClickListener {
    fun onCLick(marcas: Marcas)
    fun onLongClick(marcas: Marcas)
    fun onLongClick(categoria: Categorias)
    fun onClick(categoria: Categorias)
}