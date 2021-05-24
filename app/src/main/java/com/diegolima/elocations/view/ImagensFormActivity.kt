package com.diegolima.elocations.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.diegolima.elocations.R
import com.diegolima.elocations.R.drawable
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_imagens_form.*

class ImagensFormActivity : AppCompatActivity(), View.OnClickListener {

    private val REQUEST_IMAGE_CAPTURE = 1
    private var SelecionarUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagens_form)

        supportActionBar!!.hide()
        toobarImagens.setNavigationIcon(getDrawable(drawable.ic_arrow))

        toobarImagens.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        val buttonDados: Button = findViewById(R.id.button_dados)
        buttonDados.setOnClickListener(this)

        val buttonImagens: Button = findViewById(R.id.button_imagens)
        buttonImagens.setOnClickListener(this)

        val fbAddFotos: FloatingActionButton = findViewById(R.id.fb_add_fotos)
        fbAddFotos.setOnClickListener(this)

        val buttonDeleteImage1: Button = findViewById(R.id.button_delete_image1)
        buttonDeleteImage1.setOnClickListener(this)

/*        val foto_estabelecimentoImage: ImageView = findViewById(R.id.foto_estabelecimento)
        foto_estabelecimentoImage.setOnClickListener(this)*/

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            val imageBitmap = data?.extras?.get("data") as Bitmap
//            foto_estabelecimento.setImageBitmap(imageBitmap)
//        }
        //metodo buscar foto na galeria
        if(requestCode == 0){
            SelecionarUri = data?.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, SelecionarUri)

            if(bitmap != null) {
                foto_ex1.setImageBitmap(bitmap)
            }
        }
    }

    private fun SelecionarFotoDaGaleria() {
      val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 0)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_dados -> {
                startActivity(Intent(this, DadosFormActivity::class.java))
                finish()
            }
            R.id.button_imagens -> {
                Toast.makeText(this, "Você já está na tela de Imagens", Toast.LENGTH_LONG)
                    .show()
            }
            R.id.fb_add_fotos -> {

                SelecionarFotoDaGaleria()

            /*val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)*/

            }
            R.id.button_delete_image1 ->{
                foto_ex1.setImageResource(R.drawable.ic_map)
            }
        }
    }
}