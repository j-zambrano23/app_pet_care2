package com.example.mipetcare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsuario, editTextContrasena;
    private TextView textViewRegistrar, textViewOlvidar;
    private Button buttonIniciar;
    private DatabaseHelper databaseHelper;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);

        databaseHelper = new DatabaseHelper(this);

        editTextUsuario = findViewById(R.id.txtusu);
        editTextContrasena = findViewById(R.id.txtcontrasena);
        textViewRegistrar = findViewById(R.id.lblregistrar);
        textViewOlvidar = findViewById(R.id.lblolvidar);
        buttonIniciar = findViewById(R.id.btnIniciar);

        buttonIniciar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String username = editTextUsuario.getText().toString().trim();
                String password = editTextContrasena.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else if (databaseHelper.verificarUsuario(username, password)) {
                    Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Cierra la LoginActivity para que no se pueda regresar con el botón atrás
                } else {
                    Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        textViewRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistroUsuario.class);
                startActivity(intent);
            }
        });
    }
}


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Estilo), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });


//    public void irAMain(View View) {
//        Intent i = new Intent(this,MainActivity.class);
//        startActivity(i);
//
//    }
//
//
//    public void irARegistrarse(View View) {
//        Intent i = new Intent(this, RegistroUsuario.class);
//        startActivity(i);
//
//    }
//
//    public void irAPrueba(View view) {
//        Intent intent = new Intent(this, Alimentacion.class);
//        startActivity(intent);
//    }
//    }

//    private void irAMain(View view) {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        finish(); // Cierra esta actividad para que no se pueda volver atrás
//
//
//    }
//}


