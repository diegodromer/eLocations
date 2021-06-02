package com.diegolima.elocations.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.diegolima.elocations.R
import com.diegolima.elocations.view.form.DadosFormActivity
import com.diegolima.elocations.view.form.FormActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.activity_main.*

//FragmentActivity()
class MainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
    View.OnClickListener {

    //tarefas
    //TODO: criar tela de cadastro com os campos e banco de dados
    //TODO: criar CRUD completo e ver o tempo para criação da room para deixar o back menos custoso
    //TODO: criar activity de visualização dos cadastro com edição
    //TODO: criar activity de cadastro gerando e salvando o local atual (Double)
    //TODO: (perguntar da necessidade dessa implentação, remover ou mudar, buscando se for o caso direto do marker (talvez menos interessante))
    //TODO: implementar o slide de visualização das infos de cada pin com botao pra edicao, chamando formulario {activity_dados_form}?
    //TODO: e remover botao de visualizacao dos cadastros
    //TODO: implementar layout único para cadastro dos dados e imagens e remover as duas activities -> dando menos trabalho na manutencao
    //TODO: criar recyclerview para carregamento de fotos inicial da galeria e depois mudar para carregamento da camera /ou galeria
    //TODO: e remover layout de captura de teste
    //TODO: implementar a edição de cada estabelecimento selecionado a partir do slide à ser implementado {layout_info}


    //-----

    //Protóticos
    //locations
    //locais dos estabelecimentos
    private val mlistLocal = arrayListOf<Double>()

    //Lanchonete
    private val burgerKing = LatLng(-23.56372228087847, -46.65313417364628)

    //Hotel
    private val maksoudPlazaHotel = LatLng(-23.5628784806739, -46.6511831630152)

    //Supermercado
    private val paoDeAcucar = LatLng(-23.56468388336188, -46.65457806138418)

    //Roupas
    private val lojasRenner = LatLng(-23.564180578392975, -46.65200644292263)

    //Universidade
    private val universidadeCruzeiroDoSul = LatLng(-23.562724636834655, -46.65504904134638)

    //markers
    private lateinit var markerBurgerKing: Marker
    private lateinit var markerMaksoudPlazaHotel: Marker
    private lateinit var markerPaoDeAcucar: Marker
    private lateinit var markerLojasRenner: Marker
    private lateinit var markerUniversidadeCruzeiroDoSul: Marker

    //private lateinit var view: CoordinatorLayout

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolBarPrincipal.setTitle("eLocations")
        setSupportActionBar(toolBarPrincipal)

        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentMap) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

        listeners()

    }

    private fun listeners() {
        fb_add_estabelecimentos.setOnClickListener(this)
        button_view_estabelecimentos.setOnClickListener(this)
        fb_form.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.fb_add_estabelecimentos -> {
                intent = Intent(this, DadosFormActivity::class.java)
                startActivity(intent)
            }
            R.id.button_view_estabelecimentos -> {
                intent = Intent(this, AllDadosActivity::class.java)
                startActivity(intent)
                //finish()
            }
            R.id.fb_form -> {
                intent = Intent(this, FormActivity::class.java)
                startActivity(intent)
                //finish()
            }
        }


        button_view_estabelecimentos

        /*val activityOptionsCompat = ActivityOptions.makeCustomAnimation(this, R.anim.fade_in, R.anim.mover_direita).toBundle()
        ActivityCompat.startActivity(this, intent, activityOptionsCompat.toBundle())*/

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        //TODO: buscar os pins com banco de dados e criar um laço de marcação

        //--------

        //protótico
        //Burger King (BK)
        map.addMarker(
            MarkerOptions()
                .position(burgerKing)
                .title("Burger King (BK)")
                .snippet("{Images - Description - Categories - Directions}")
        ).also { markerBurgerKing = it }
        markerBurgerKing.tag = 0


        //Maksoud Plaza Hotel
        map.addMarker(
            MarkerOptions()
                .position(maksoudPlazaHotel)
                .title("Maksoud Plaza Hotel")
                .snippet("{Images - Description - Categories - Directions}")
        ).also { markerMaksoudPlazaHotel = it }
        markerMaksoudPlazaHotel.tag = 0

        //Pão de Açúcar
        map.addMarker(
            MarkerOptions()
                .position(paoDeAcucar)
                .title("Pão de Açúcar")
                .snippet("{Images - Description - Categories - Directions}")
        ).also { markerPaoDeAcucar = it }
        markerPaoDeAcucar.tag = 0

        //Lojas Renner
        map.addMarker(
            MarkerOptions()
                .position(lojasRenner)
                .title("Lojas Renner")
                .snippet("{Images - Description - Categories - Directions}")
        ).also { markerLojasRenner = it }
        markerLojasRenner.tag = 0

        //o also, referencia tudo dentro laço e atribui ao marcador de uma forma mais estrutural

        //Universidade Cruzeiro do Sul
        markerUniversidadeCruzeiroDoSul = map.addMarker(
            MarkerOptions()
                .position(universidadeCruzeiroDoSul)
                .title("Universidade Cruzeiro do Sul")
                .snippet("{Images - Description - Categories - Directions}")
            //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        )
        markerUniversidadeCruzeiroDoSul.tag = 0

        configCameraMap()

        map.setOnMarkerClickListener(this)
    }

    private fun configCameraMap() {

        val cameraPosition =
            CameraPosition.Builder()//seta uma boa visualizacao geral dos comercios marcados
                .target(burgerKing)         // Sets the center of the map to Mountain View
                .zoom(17f)            // Sets the zoom
                .bearing(90f)       // Sets the orientation of the camera to east
                .tilt(30f)              // Sets the tilt of the camera to 30 degrees
                .build()                     // Creates a CameraPosition from the builder
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    override fun onMarkerClick(marker: Marker): Boolean {

        Toast.makeText(
            this,
            marker.title,
            Toast.LENGTH_SHORT
        ).show()

        //TODO: mostrar slide referente as informações de cada marker selecionado

        return false
    }

}