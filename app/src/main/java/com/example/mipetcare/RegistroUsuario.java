package com.example.mipetcare;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mipetcare.databinding.ActivityRegistroUsuarioBinding;

public class RegistroUsuario extends AppCompatActivity {
    private EditText editTextNombreUsuario, editTextCorreo, editTextContraseña;
    private TextView textViewRegistroUsuario;
    private Button buttonRegistro;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_usuario);

        // Initialize the DatabaseHelper instance
        databaseHelper = new DatabaseHelper(this);

        // Initialize UI components
        textViewRegistroUsuario = findViewById(R.id.txtregistrousuario);
        editTextNombreUsuario = findViewById(R.id.txtnombreusuario);
        editTextCorreo = findViewById(R.id.editTcorreo);
        editTextContraseña = findViewById(R.id.txtcontra);
        buttonRegistro = findViewById(R.id.btnRegistro);

        // Set click listener for the registration button
        buttonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input values
                String usuario = editTextNombreUsuario.getText().toString().trim();
                String correo = editTextCorreo.getText().toString().trim();
                String contraseña = editTextContraseña.getText().toString().trim();

                // Check if any field is empty
                if (usuario.isEmpty() || correo.isEmpty() || contraseña.isEmpty()) {
                    Toast.makeText(RegistroUsuario.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Insert data into the database
                    boolean insertado = databaseHelper.insertarDatos(usuario, correo, contraseña);
                    if (insertado) {
                        // Show success message and navigate to MainActivity
                        Toast.makeText(RegistroUsuario.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistroUsuario.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        // Show error message
                        Toast.makeText(RegistroUsuario.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cerrar la base de datos para evitar fugas de memoria
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }

}


