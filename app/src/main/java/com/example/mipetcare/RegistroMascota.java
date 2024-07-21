package com.example.mipetcare;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistroMascota extends AppCompatActivity {

    EditText txtnombremas;
    TextView txtregistrousuario, txtanimal, txtraza, txtsexo, txtedad;
    Button btnregis;
    Spinner spinnerRaza, spinnerAnimal, spinnerEdad, spinnerSexo;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_mascota);

        db = new DatabaseHelper(this);

        txtnombremas = (EditText) findViewById(R.id.txtnombremas);
        txtregistrousuario = (TextView) findViewById(R.id.txtregistrousuario);
        txtanimal = (TextView) findViewById(R.id.txtanimal);
        txtraza = (TextView) findViewById(R.id.txtraza);
        txtsexo = (TextView) findViewById(R.id.txtsexo);
        txtedad = (TextView) findViewById(R.id.txtedad);
        btnregis = (Button) findViewById(R.id.btnregis);
        spinnerRaza = (Spinner) findViewById(R.id.spinnerRaza);
        spinnerAnimal = (Spinner) findViewById(R.id.spinnerAnimal);
        spinnerEdad = (Spinner) findViewById(R.id.spinnerEdad);
        spinnerSexo = (Spinner) findViewById(R.id.spinnerSexo);

        String[] opcionesAnimal = {"Perro", "Gato"};
        String[] opcionesRaza = {"Labrador", "Pastor Alemán", "Bulldog", "Husky siberiano", "Golden retriever", "Caniche", "Yorkshire terrier", "Dalmata", "Boxer", "Chihuahua", " Bulldog ingles", " Beagle", "Poodle", "Pug", "Schnauzer", "Samoyedo", "Persa", "Siames", "Azul ruso", "Gato Siberiano", "Bengali", "Gato Exotico", "Gato Oriental", "Gato Ragdoll", "Himalayo", "Khao Manee", "Otro"};
        String[] opcionesEdad = {"1 mes", "2 meses", "3 meses", "4 meses", "5 meses", "6 meses", "7 meses", "8 meses", "9 meses", "10 meses", "11 meses", "12 meses", "2 años", "3 años", "4 años", "5 años", "6 años", "7 años", "8 años", "9 años", "10 años", "Otro"};
        String[] opcionesSexo = {"Macho", "Hembra"};

        configureSpinner(spinnerAnimal, opcionesAnimal);
        configureSpinner(spinnerRaza, opcionesRaza);
        configureSpinner(spinnerEdad, opcionesEdad);
        configureSpinner(spinnerSexo, opcionesSexo);

        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreMascota = txtnombremas.getText().toString().trim();
                String animal = spinnerAnimal.getSelectedItem().toString();
                String raza = spinnerRaza.getSelectedItem().toString();
                String edad = spinnerEdad.getSelectedItem().toString();
                String sexo = spinnerSexo.getSelectedItem().toString();

                if (nombreMascota.isEmpty()) {
                    Toast.makeText(RegistroMascota.this, "Por favor, ingrese el nombre de la mascota", Toast.LENGTH_SHORT).show();
                } else {
                    boolean registroExitoso = db.insertarMascota(nombreMascota, animal, raza, edad, sexo);
                    if (registroExitoso) {
                        Log.d("RegistroMascota", "Registro exitoso");
                        Toast.makeText(RegistroMascota.this, "Mascota registrada con éxito", Toast.LENGTH_SHORT).show();

                        // Crear un Intent para ir a LoginActivity
                        Intent intent = new Intent(RegistroMascota.this, LoginActivity.class);

                        // Limpiar la pila de actividades
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        startActivity(intent);
                        finish(); // Cierra la actividad actual
                    } else {
                        Log.e("RegistroMascota", "Fallo en el registro");
                        Toast.makeText(RegistroMascota.this, "Error al registrar la mascota", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Estilo), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void configureSpinner(Spinner spinner, String[] opciones) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}