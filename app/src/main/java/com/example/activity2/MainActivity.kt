package com.example.activity2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.activity2.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Firebase.database
        val myRef = database.reference

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.save.setOnClickListener {
            val name = binding.name.text.toString()
            val id = binding.id.text.toString()
            val age = binding.age.text.toString()

            val person = hashMapOf(
                "name" to name,
                "id" to id,
                "age" to age
            )
            myRef.child("person").child("$count").setValue(person)
            count++
            Toast.makeText(applicationContext, "Success!", Toast.LENGTH_LONG).show()
        }


        binding.get.setOnClickListener {

            myRef.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val value = snapshot.value
                    binding.textData.text = value.toString()
                    Toast.makeText(applicationContext, "Success!", Toast.LENGTH_LONG).show()

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, "Faille!", Toast.LENGTH_LONG).show()

                }
            })
        }

    }
}