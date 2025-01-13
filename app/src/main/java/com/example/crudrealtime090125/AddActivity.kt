package com.example.crudrealtime090125

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crudrealtime090125.databinding.ActivityAddBinding
import com.example.crudrealtime090125.models.Agenda
import com.example.crudrealtime090125.utils.encodeEmail
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    private var nombre=""
    private var ciudad=""
    private var email=""
    private var sueldo=0F


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding=ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setListeners()
    }
    //----------------------------------------------------------------------------------------------
    private fun setListeners() {
        binding.btnCancelar.setOnClickListener {
            finish()
        }
        binding.btnAdd.setOnClickListener {
            addItem()
        }
    }
    //----------------------------------------------------------------------------------------------
    private fun addItem() {
        if(!datosOk()) return
        //Datos correctos
        val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("agenda")
        val item=Agenda(email, nombre, ciudad, sueldo)
        val nodo=email.encodeEmail()
        database.child(nodo).addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    Toast.makeText(this@AddActivity, "El email ya está registrado", Toast.LENGTH_SHORT).show()
                }else{
                    database.child(nodo).setValue(item).addOnSuccessListener {
                        finish()
                    }
                        .addOnFailureListener {
                            Toast.makeText(this@AddActivity, "Error al guardar", Toast.LENGTH_SHORT).show()
                        }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
    //----------------------------------------------------------------------------------------------
    private fun datosOk(): Boolean {
        nombre=binding.etNombre.text.toString().trim()
        if(nombre.length<3){
            binding.etNombre.error="Error, el nombre debe tener 3 caracteres"
            return false
        }
        ciudad=binding.etCiudad.text.toString().trim()
        if(ciudad.length<3){
            binding.etCiudad.error="Error, el campo debe tener al menos 3 caracteres"
            return false
        }
        sueldo=binding.etSueldo.text.toString().toFloat()
        if(sueldo<100 || sueldo>10000){
            binding.etSueldo.error="Error, la cantidad debe estar entre 100 y 10000"
            return false
        }
        email=binding.etEmail.text.toString().trim()
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmail.error="Error, se esperaba un email válido"
            return false
        }
        return true
    }
    //----------------------------------------------------------------------------------------------
}