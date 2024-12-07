package com.ignacio.carverse.modelModule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ignacio.carverse.ModelsActivity
import com.ignacio.carverse.R
import com.ignacio.carverse.classes.Marcas
import com.ignacio.carverse.databinding.ItemMiniBinding

class MarcasMiniAdapter(private val marcas: List<Marcas>, private val listener: OnClickListener): RecyclerView.Adapter<MarcasMiniAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemMiniBinding.bind(view)

        fun setListener(marca: Marcas){
            binding.root.setOnClickListener{
                listener.onClick(marca)
            }

            binding.root.setOnLongClickListener{
                listener.onLongCLick(marca)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_mini, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = marcas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val marca = marcas[position]

        with(holder){
            setListener(marca)
            binding.tvTituloCardMini.text = marca.nombre
            Glide.with(context)
                .load(marca.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imgCardMini)
        }
    }
}