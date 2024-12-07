package com.ignacio.carverse

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ignacio.carverse.classes.Categorias
import com.ignacio.carverse.databinding.ActivityMainBinding
import com.ignacio.carverse.mainModule.adapter.CategoriasAdapter
import com.ignacio.carverse.mainModule.adapter.OnClickListener

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var mBinding: ActivityMainBinding

    private lateinit var categoriasAdapter: CategoriasAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        categoriasAdapter = CategoriasAdapter(getCategorias(), this)
        linearLayoutManager = LinearLayoutManager(this)

        mBinding.rcvMain.apply {
            layoutManager = linearLayoutManager
            adapter = categoriasAdapter
        }
    }

    private fun getCategorias(): MutableList<Categorias>{
        val categorias = mutableListOf<Categorias>()
        val context = this
        val recursosCategorias = listOf(context.getString(R.string.categoria_jdm),
            context.getString(R.string.categoria_euro),
            context.getString(R.string.categoria_muscle),
            context.getString(R.string.categoria_exotic))

        for (categoria in recursosCategorias){
            val categoriaData = categoria.split("|")
            val categoriaFinal = Categorias(categoriaData[0].toLong(), categoriaData[1], categoriaData[2], categoriaData[3])
            categorias.add(categoriaFinal)
        }

        return categorias
    }

    override fun onClick(categoria: Categorias) {
        navigateToBrands(categoria.id)
    }

    override fun onLongClick(categoria: Categorias) {
        Toast.makeText(this, "${categoria.nombre}", Toast.LENGTH_SHORT).show()    }

    private fun navigateToBrands(categoriaId: Long) {
        val intent = Intent(this, BrandsActivity::class.java).apply {
            putExtra("categoriaId", categoriaId)
        }
        startActivity(intent)
    }
}