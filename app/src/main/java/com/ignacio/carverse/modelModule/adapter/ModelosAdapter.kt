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
import com.ignacio.carverse.classes.Modelos
import com.ignacio.carverse.databinding.ItemModelBinding

class ModelosAdapter(private val modelos: List<Modelos>, private val listener: OnClickListener): RecyclerView.Adapter<ModelosAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemModelBinding.bind(view)

        fun setListener(modelos: Modelos){
            binding.root.setOnClickListener{
                listener.onClick(modelos)
            }

            binding.root.setOnLongClickListener{
                listener.onLongCLick(modelos)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_model, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = modelos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modelo = modelos[position]

        with(holder){
            setListener(modelo)
            binding.tvTituloCard.text = modelo.nombre
            binding.tvFecha.text = modelo.fecha
            Glide.with(context)
                .load(modelo.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imgCard)
        }
    }

}