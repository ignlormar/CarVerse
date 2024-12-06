package com.ignacio.carverse.brandModule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ignacio.carverse.BrandsActivity
import com.ignacio.carverse.R
import com.ignacio.carverse.classes.Categorias
import com.ignacio.carverse.databinding.ItemMiniBinding

class CategoriasMiniAdapter(private val categorias: List<Categorias>, private val listener: BrandsActivity): RecyclerView.Adapter<CategoriasMiniAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemMiniBinding.bind(view)

        fun setListener(categoria: Categorias){
            binding.root.setOnClickListener{
                listener.onClick(categoria)
            }

            binding.root.setOnLongClickListener{
                listener.onLongClick(categoria)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_mini, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = categorias.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categoria = categorias[position]

        with(holder){
            setListener(categoria)
            binding.tvTituloCardMini.text = categoria.nombre
            Glide.with(context)
                .load(categoria.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imgCardMini)


        }
    }
}