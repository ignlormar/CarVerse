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

        mBinding.rcv.apply {
            layoutManager = linearLayoutManager
            adapter = categoriasAdapter
        }
    }

    private fun getCategorias(): MutableList<Categorias>{
        val categorias = mutableListOf<Categorias>()
        val categoriaJDM = Categorias(1,
            "JDM (Japanese Domestic Market)",
            "https://i.ebayimg.com/images/g/EUkAAOSwi0xZ6PNj/s-l1200.jpg")

        categorias.add(categoriaJDM)

        return categorias
    }

    override fun onClick(categoria: Categorias) {
        val url = "${categoria.imagen}"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    override fun onLongClick(categoria: Categorias) {
        Toast.makeText(this, "${categoria.nombre}", Toast.LENGTH_SHORT).show()    }
}