package com.ignacio.carverse

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ignacio.carverse.brandModule.adapter.CategoriasMiniAdapter
import com.ignacio.carverse.brandModule.adapter.MarcasAdapter
import com.ignacio.carverse.brandModule.adapter.OnClickListener
import com.ignacio.carverse.classes.Categorias
import com.ignacio.carverse.classes.Marcas
import com.ignacio.carverse.databinding.ActivityBrandsBinding


class BrandsActivity : AppCompatActivity(), OnClickListener {

    private lateinit var bBinding: ActivityBrandsBinding

    private lateinit var categoriasMiniAdapter: CategoriasMiniAdapter
    private lateinit var marcasAdapter: MarcasAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        bBinding = ActivityBrandsBinding.inflate(layoutInflater)
        setContentView(bBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val categoriaId = intent.getLongExtra("categoriaId", -1)

        marcasAdapter = MarcasAdapter(getMarcas(categoriaId), this)
        linearLayoutManager = LinearLayoutManager(this)

        bBinding.rcvBrands.apply {
            layoutManager = linearLayoutManager
            adapter = marcasAdapter
        }

        categoriasMiniAdapter = CategoriasMiniAdapter(getCategorias(categoriaId), this)

        val horizontalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        bBinding.rcvAnterior.apply {
            layoutManager = horizontalLayoutManager
            adapter = categoriasMiniAdapter
        }
    }

    private fun getMarcas(categoriaId: Long): MutableList<Marcas>{
        val marcas = mutableListOf<Marcas>()
        val context = this

        val recursosMarcas = listOf(
            context.getString(R.string.jdm_nissan),
            context.getString(R.string.jdm_toyota),
            context.getString(R.string.jdm_honda),
            context.getString(R.string.jdm_mazda),
            context.getString(R.string.jdm_subaru),
            context.getString(R.string.jdm_mitsubishi),
            context.getString(R.string.muscle_ford),
            context.getString(R.string.muscle_chevrolet),
            context.getString(R.string.muscle_dodge),
            context.getString(R.string.muscle_pontiac),
            context.getString(R.string.muscle_plymouth),
            context.getString(R.string.muscle_buick),
            context.getString(R.string.euro_volkswagen),
            context.getString(R.string.euro_mercedes),
            context.getString(R.string.euro_audi),
            context.getString(R.string.euro_bmw),
            context.getString(R.string.euro_peugeot),
            context.getString(R.string.euro_renault),
            context.getString(R.string.exotic_bugatti),
            context.getString(R.string.exotic_mclaren),
            context.getString(R.string.exotic_lamborghini),
            context.getString(R.string.exotic_pagani),
            context.getString(R.string.exotic_koenigsegg),
            context.getString(R.string.exotic_ferrari))

        for (marca in recursosMarcas){
            val marcaData = marca.split("|")
            val marcaFinal = Marcas(marcaData[0].toLong(), marcaData[1].toLong(), marcaData[2], marcaData[3])

            if (marcaFinal.idCategoria == categoriaId){
                marcas.add(marcaFinal)
            }
        }
        return marcas
    }

    private fun getCategorias(currentCategoriaId: Long): MutableList<Categorias>{
        val categorias = mutableListOf<Categorias>()
        val context = this

        val recursosCategorias = listOf(context.getString(R.string.categoria_jdm),
            context.getString(R.string.categoria_euro),
            context.getString(R.string.categoria_muscle),
            context.getString(R.string.categoria_exotic))

        for (categoria in recursosCategorias){
            val categoriaData = categoria.split("|")
            val categoriaFinal = Categorias(categoriaData[0].toLong(), categoriaData[1], categoriaData[2])

            if (categoriaFinal.id != currentCategoriaId){
                categorias.add(categoriaFinal)
            }
        }

        return categorias
    }

    override fun onCLick(marca: Marcas) {
        Toast.makeText(this, "${marca.nombre}", Toast.LENGTH_SHORT).show()    }

    override fun onLongClick(marca: Marcas) {
        Toast.makeText(this, "${marca.nombre}", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToCategoria(){
        val intent = Intent(this, )
        startActivity(intent)
        finish()
    }

    override fun onClick(categoria: Categorias) {

    }

    override fun onLongClick(categoria: Categorias) {
        Toast.makeText(this, "${categoria.nombre}", Toast.LENGTH_SHORT).show()
    }
}