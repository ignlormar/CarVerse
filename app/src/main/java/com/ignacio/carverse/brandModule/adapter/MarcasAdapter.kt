package com.ignacio.carverse.brandModule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ignacio.carverse.R
import com.ignacio.carverse.classes.Marcas
import com.ignacio.carverse.databinding.ItemMainBinding

class MarcasAdapter(private val marcas: List<Marcas>, private val listener: OnClickListener): RecyclerView.Adapter<MarcasAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemMainBinding.bind(view)

        fun setListener(marcas: Marcas){
            binding.root.setOnClickListener{
                listener.onCLick(marcas)
            }

            binding.root.setOnLongClickListener{
                listener.onLongClick(marcas)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = marcas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val marca = marcas[position]

        with(holder){
            setListener(marca)
            binding.tvTituloCard.text = marca.nombre
            Glide.with(context)
                .load(marca.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imgCard)
        }
    }



}