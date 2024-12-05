package com.ignacio.carverse.mainModule.adapter

import com.ignacio.carverse.classes.Categorias

interface OnClickListener {
    fun onClick(categoria: Categorias)
    fun onLongClick(categoria: Categorias)
}