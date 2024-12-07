package com.ignacio.carverse

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ignacio.carverse.classes.Fichas
import com.ignacio.carverse.databinding.ActivityDatosBinding

class DatosActivity : AppCompatActivity() {

    private lateinit var datBinding: ActivityDatosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        datBinding = ActivityDatosBinding.inflate(layoutInflater)
        setContentView(datBinding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val idModelo = intent.getLongExtra("modeloId", -1L)

        val fichaTecnica = getFichasTecnicas(idModelo)

        if (fichaTecnica.modelo.isEmpty()) {
            datBinding.tvModeloFicha.text = "Modelo no encontrado"
        } else {
            datBinding.tvModeloFicha.text = fichaTecnica.modelo
            datBinding.tvAnyos.text = fichaTecnica.anyos
            datBinding.tvPotencia.text = fichaTecnica.potencia
            datBinding.tvVelocidadMax.text = fichaTecnica.velocidadMaxima
            datBinding.tvTraccion.text = fichaTecnica.traccion
            datBinding.tvPrecio.text = fichaTecnica.precio

            Glide.with(this)
                .load(fichaTecnica.imagen)
                .centerCrop()
                .into(datBinding.imgFicha)
        }



    }

    private fun getFichasTecnicas(idModelo: Long): Fichas {

        val context = this

        val recursosFichas = listOf(
            context.getString(R.string.ficha_nissan_skyline_r34),
            context.getString(R.string.ficha_nissan_350z),
            context.getString(R.string.ficha_nissan_silvia),
            context.getString(R.string.ficha_nissan_240sx),
            context.getString(R.string.ficha_nissan_gtr_r35),
            context.getString(R.string.ficha_toyota_supra_a80),
            context.getString(R.string.ficha_toyota_ae86),
            context.getString(R.string.ficha_toyota_celica_gt4),
            context.getString(R.string.ficha_toyota_mr2),
            context.getString(R.string.ficha_toyota_chaser),
            context.getString(R.string.ficha_honda_civic_type_r),
            context.getString(R.string.ficha_honda_nsx_na1),
            context.getString(R.string.ficha_honda_integra_type_r),
            context.getString(R.string.ficha_honda_s2000),
            context.getString(R.string.ficha_honda_crx),
            context.getString(R.string.ficha_mazda_rx7),
            context.getString(R.string.ficha_mazda_mx5),
            context.getString(R.string.ficha_mazda_rx8),
            context.getString(R.string.ficha_mazda_m3_mps),
            context.getString(R.string.ficha_mazda_cosmo),
            context.getString(R.string.ficha_subaru_impreza_wrx_sti),
            context.getString(R.string.ficha_subaru_legacy_gt_b),
            context.getString(R.string.ficha_subaru_brz),
            context.getString(R.string.ficha_subaru_forester_xt),
            context.getString(R.string.ficha_subaru_svx),
            context.getString(R.string.ficha_mitsubishi_lancer_evo),
            context.getString(R.string.ficha_mitsubishi_3000gt),
            context.getString(R.string.ficha_mitsubishi_eclipse),
            context.getString(R.string.ficha_mitsubishi_starion),
            context.getString(R.string.ficha_mitsubishi_galant_vr4),
            context.getString(R.string.ficha_volkswagen_golf_gti),
            context.getString(R.string.ficha_volkswagen_beetle),
            context.getString(R.string.ficha_volkswagen_scirocco),
            context.getString(R.string.ficha_volkswagen_phaeton),
            context.getString(R.string.ficha_volkswagen_corrado_vr6),
            context.getString(R.string.ficha_audi_quattro),
            context.getString(R.string.ficha_audi_rs2_avant),
            context.getString(R.string.ficha_audi_r8),
            context.getString(R.string.ficha_audi_tt_mk1),
            context.getString(R.string.ficha_audi_a4_dtm),
            context.getString(R.string.ficha_mercedes_190e_evo2),
            context.getString(R.string.ficha_mercedes_amg_gt),
            context.getString(R.string.ficha_mercedes_s_class_w140),
            context.getString(R.string.ficha_mercedes_sl500_r129),
            context.getString(R.string.ficha_mercedes_cls63_amg),
            context.getString(R.string.ficha_bmw_m3_e30),
            context.getString(R.string.ficha_bmw_m5_e39),
            context.getString(R.string.ficha_bmw_2002_turbo),
            context.getString(R.string.ficha_bmw_z3_m_coupe),
            context.getString(R.string.ficha_bmw_8_series_e31),
            context.getString(R.string.ficha_peugeot_205_gti),
            context.getString(R.string.ficha_peugeot_206_wrc),
            context.getString(R.string.ficha_peugeot_405_mi16),
            context.getString(R.string.ficha_peugeot_306_gti_6),
            context.getString(R.string.ficha_peugeot_508_pse),
            context.getString(R.string.ficha_renault_clio_v6),
            context.getString(R.string.ficha_renault_megane_rs),
            context.getString(R.string.ficha_renault_alpine_a110),
            context.getString(R.string.ficha_renault_5_turbo),
            context.getString(R.string.ficha_renault_safrane_biturbo),
            context.getString(R.string.ficha_bugatti_veyron),
            context.getString(R.string.ficha_bugatti_chiron),
            context.getString(R.string.ficha_bugatti_eb110),
            context.getString(R.string.ficha_bugatti_type_57),
            context.getString(R.string.ficha_bugatti_bolide),
            context.getString(R.string.ficha_mclaren_f1),
            context.getString(R.string.ficha_mclaren_p1),
            context.getString(R.string.ficha_mclaren_mp4_12c),
            context.getString(R.string.ficha_mclaren_720s),
            context.getString(R.string.ficha_mclaren_senna),
            context.getString(R.string.ficha_lamborghini_countach),
            context.getString(R.string.ficha_lamborghini_diablo_sv),
            context.getString(R.string.ficha_lamborghini_murcielago),
            context.getString(R.string.ficha_lamborghini_aventador),
            context.getString(R.string.ficha_lamborghini_miura),
            context.getString(R.string.ficha_koenigsegg_ccx),
            context.getString(R.string.ficha_koenigsegg_agera),
            context.getString(R.string.ficha_koenigsegg_regera),
            context.getString(R.string.ficha_koenigsegg_gemera),
            context.getString(R.string.ficha_koenigsegg_jesko),
            context.getString(R.string.ficha_pagani_zonda_cinque),
            context.getString(R.string.ficha_pagani_huayra),
            context.getString(R.string.ficha_pagani_zonda_r),
            context.getString(R.string.ficha_pagani_zonda_f),
            context.getString(R.string.ficha_pagani_huayra_bc),
            context.getString(R.string.ficha_ferrari_f40),
            context.getString(R.string.ficha_ferrari_458_italia),
            context.getString(R.string.ficha_ferrari_enzo),
            context.getString(R.string.ficha_ferrari_testarossa),
            context.getString(R.string.ficha_ferrari_laferrari)
        )



        for (ficha in recursosFichas) {
            val fichaData = ficha.split("|")
            val idModeloFicha = fichaData[1].toLong()
            if (idModeloFicha == idModelo){
                return Fichas(
                    idFicha = fichaData[0].toLong(),
                    idModelo = fichaData[1].toLong(),
                    modelo = fichaData[2],
                    anyos = fichaData[3],
                    potencia = fichaData[4],
                    traccion = fichaData[5],
                    consumo = fichaData[6],
                    velocidadMaxima = fichaData[7],
                    imagen = fichaData[8],
                    precio = fichaData[9]
                )
            }
        }

        return Fichas(
            idFicha = 0,
            idModelo = 0,
            modelo = "",
            anyos = "",
            potencia = "",
            traccion = "",
            consumo = "",
            velocidadMaxima = "",
            imagen = "",
            precio = ""
        )
    }
}