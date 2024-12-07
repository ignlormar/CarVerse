package com.ignacio.carverse

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ignacio.carverse.classes.Marcas
import com.ignacio.carverse.classes.Modelos
import com.ignacio.carverse.databinding.ActivityModelsBinding
import com.ignacio.carverse.modelModule.adapter.MarcasMiniAdapter
import com.ignacio.carverse.modelModule.adapter.ModelosAdapter
import com.ignacio.carverse.modelModule.adapter.OnClickListener

class ModelsActivity : AppCompatActivity(), OnClickListener {

    private lateinit var modBinding: ActivityModelsBinding

    private lateinit var marcasMiniAdapter: MarcasMiniAdapter
    private lateinit var modelosAdapter: ModelosAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        modBinding = ActivityModelsBinding.inflate(layoutInflater)
        setContentView(modBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val marcaId = intent.getLongExtra("marcaId", -1)

        modelosAdapter = ModelosAdapter(getModelos(marcaId), this)
        linearLayoutManager = LinearLayoutManager(this)

        val introMarca = getMarcaDescripcion(marcaId)

        modBinding.rcvModels.apply {
            layoutManager = linearLayoutManager
            adapter = modelosAdapter
        }

        marcasMiniAdapter = MarcasMiniAdapter(getMarcas(marcaId), this)

        val horizontalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        modBinding.rcvAnterior.apply {
            layoutManager = horizontalLayoutManager
            adapter = marcasMiniAdapter
        }

        modBinding.tvIntroMarca.text = introMarca
    }

    private fun getModelos(marcaId: Long): MutableList<Modelos>{
        val modelos = mutableListOf<Modelos>()
        val context = this

        val recursosModelos = listOf(
            context.getString(R.string.nissan_skyline_r34),
            context.getString(R.string.nissan_350z_370z),
            context.getString(R.string.nissan_silvia),
            context.getString(R.string.nissan_240sx),
            context.getString(R.string.nissan_gtr_r35),
            context.getString(R.string.toyota_supra_a80),
            context.getString(R.string.toyota_ae86),
            context.getString(R.string.toyota_celica_gt4),
            context.getString(R.string.toyota_mr2),
            context.getString(R.string.toyota_chaser),
            context.getString(R.string.honda_civic_type_r),
            context.getString(R.string.honda_nsx),
            context.getString(R.string.honda_integra_type_r),
            context.getString(R.string.honda_s2000),
            context.getString(R.string.honda_crx),
            context.getString(R.string.mazda_rx7),
            context.getString(R.string.mazda_mx5),
            context.getString(R.string.mazda_rx8),
            context.getString(R.string.mazda_m3_mps),
            context.getString(R.string.mazda_cosmo),
            context.getString(R.string.subaru_impreza_wrx_sti),
            context.getString(R.string.subaru_legacy_gt_b),
            context.getString(R.string.subaru_brz),
            context.getString(R.string.subaru_forester_xt),
            context.getString(R.string.subaru_svx),
            context.getString(R.string.mitsubishi_lancer_evo),
            context.getString(R.string.mitsubishi_3000gt),
            context.getString(R.string.mitsubishi_eclipse),
            context.getString(R.string.mitsubishi_starion),
            context.getString(R.string.mitsubishi_galant_vr4),
            context.getString(R.string.volkswagen_golf_gti),
            context.getString(R.string.volkswagen_beetle),
            context.getString(R.string.volkswagen_scirocco),
            context.getString(R.string.volkswagen_phaeton),
            context.getString(R.string.volkswagen_corrado_vr6),
            context.getString(R.string.audi_quattro),
            context.getString(R.string.audi_rs2_avant),
            context.getString(R.string.audi_r8),
            context.getString(R.string.audi_tt_mk1),
            context.getString(R.string.audi_a4_dtm),
            context.getString(R.string.mercedes_190e_evo2),
            context.getString(R.string.mercedes_amg_gt),
            context.getString(R.string.mercedes_s_class_w140),
            context.getString(R.string.mercedes_sl500_r129),
            context.getString(R.string.mercedes_g_class),
            context.getString(R.string.bmw_m3_e30),
            context.getString(R.string.bmw_m5_e39),
            context.getString(R.string.bmw_2002_turbo),
            context.getString(R.string.bmw_z3_m_coupe),
            context.getString(R.string.bmw_8_series_e31),
            context.getString(R.string.peugeot_205_gti),
            context.getString(R.string.peugeot_206_wrc),
            context.getString(R.string.peugeot_405_mi16),
            context.getString(R.string.peugeot_306_gti_6),
            context.getString(R.string.peugeot_508_pse),
            context.getString(R.string.renault_clio_v6),
            context.getString(R.string.renault_megane_rs),
            context.getString(R.string.renault_alpine_a110),
            context.getString(R.string.renault_5_turbo),
            context.getString(R.string.renault_safrane_biturbo),
            context.getString(R.string.ford_mustang),
            context.getString(R.string.ford_gt40),
            context.getString(R.string.ford_thunderbird),
            context.getString(R.string.ford_cobra),
            context.getString(R.string.ford_falcon_xb),
            context.getString(R.string.chevrolet_camaro),
            context.getString(R.string.chevrolet_corvette),
            context.getString(R.string.chevrolet_chevelle_ss),
            context.getString(R.string.chevrolet_impala_ss),
            context.getString(R.string.chevrolet_el_camino),
            context.getString(R.string.dodge_charger),
            context.getString(R.string.dodge_challenger_hellcat),
            context.getString(R.string.dodge_viper_gts),
            context.getString(R.string.dodge_dart_swinger),
            context.getString(R.string.dodge_daytona),
            context.getString(R.string.pontiac_firebird_trans_am),
            context.getString(R.string.pontiac_gto_judge),
            context.getString(R.string.pontiac_grand_prix_sj),
            context.getString(R.string.pontiac_bonneville),
            context.getString(R.string.pontiac_solstice_gxp),
            context.getString(R.string.plymouth_barracuda),
            context.getString(R.string.plymouth_road_runner),
            context.getString(R.string.plymouth_fury),
            context.getString(R.string.plymouth_duster),
            context.getString(R.string.plymouth_belvedere_gtx),
            context.getString(R.string.buick_gnx),
            context.getString(R.string.buick_riviera_boat_tail),
            context.getString(R.string.buick_regal_t_type),
            context.getString(R.string.buick_skylark_gsx),
            context.getString(R.string.buick_wildcat),
            context.getString(R.string.bugatti_veyron),
            context.getString(R.string.bugatti_chiron),
            context.getString(R.string.bugatti_eb110),
            context.getString(R.string.bugatti_type_57),
            context.getString(R.string.bugatti_bolide),
            context.getString(R.string.mclaren_f1),
            context.getString(R.string.mclaren_p1),
            context.getString(R.string.mclaren_mp4_12c),
            context.getString(R.string.mclaren_720s),
            context.getString(R.string.mclaren_senna),
            context.getString(R.string.lamborghini_countach),
            context.getString(R.string.lamborghini_diablo_sv),
            context.getString(R.string.lamborghini_murcielago),
            context.getString(R.string.lamborghini_aventador),
            context.getString(R.string.lamborghini_miura),
            context.getString(R.string.koenigsegg_ccx),
            context.getString(R.string.koenigsegg_agera),
            context.getString(R.string.koenigsegg_regera),
            context.getString(R.string.koenigsegg_gemera),
            context.getString(R.string.koenigsegg_jesko),
            context.getString(R.string.pagani_zonda_cinque),
            context.getString(R.string.pagani_huayra),
            context.getString(R.string.pagani_zonda_r),
            context.getString(R.string.pagani_zonda_f),
            context.getString(R.string.pagani_huayra_bc),
            context.getString(R.string.ferrari_f40),
            context.getString(R.string.ferrari_458_italia),
            context.getString(R.string.ferrari_enzo),
            context.getString(R.string.ferrari_testarossa),
            context.getString(R.string.ferrari_laferrari)
        )

        for (modelo in recursosModelos){
            val modeloData = modelo.split("|")
            val modeloFinal = Modelos(modeloData[0].toLong(), modeloData[1].toLong(), modeloData[2], modeloData[3], modeloData[4])

            if (modeloFinal.idMarca == marcaId){
                modelos.add(modeloFinal)
            }
        }
        return modelos
    }

    private fun getMarcaDescripcion(marcaId: Long): String {
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
            context.getString(R.string.exotic_ferrari)
        )

        for (marca in recursosMarcas) {
            val marcaData = marca.split("|")
            if (marcaData[0].toLong() == marcaId){
                return marcaData[4]
            }
        }

        return "Descripcion no disponible"
    }

    private fun getMarcas(currentMarcaId: Long): MutableList<Marcas>{
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
            context.getString(R.string.exotic_ferrari)
        )


        for (marca in recursosMarcas){
            val marcaData = marca.split("|")
            val marcaFinal = Marcas(marcaData[0].toLong(), marcaData[1].toLong(), marcaData[2], marcaData[3], marcaData[4])

            if (marcaFinal.id != currentMarcaId) {
                marcas.add(marcaFinal)
            }
        }

        return marcas
    }

    private fun navigateToDatos(modeloId: Long){
        val intent = Intent(this, DatosActivity::class.java).apply {
            putExtra("modeloId", modeloId)
        }

        startActivity(intent)
    }

    override fun onClick(modelo: Modelos) {
        navigateToDatos(modelo.id)
    }

    override fun onLongCLick(modelo: Modelos) {
        Toast.makeText(this, "${modelo.nombre}", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToMarca(marcaId: Long){
        val intent = Intent(this, ModelsActivity::class.java).apply {
            putExtra("marcaId", marcaId)
        }
        startActivity(intent)
    }

    override fun onClick(marca: Marcas) {
        navigateToMarca(marca.id)
    }

    override fun onLongCLick(marca: Marcas) {
        Toast.makeText(this, "${marca.nombre}", Toast.LENGTH_SHORT).show()
    }
}