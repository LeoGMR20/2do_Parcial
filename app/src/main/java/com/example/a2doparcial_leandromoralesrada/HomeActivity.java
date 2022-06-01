package com.example.a2doparcial_leandromoralesrada;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class HomeActivity extends AppCompatActivity {

    //Animaciones

    private Animation diagonal;

    //Componentes visuales
    private TextView tvMostrarDatos, tvBeca;
    private Button btnMostrar;
    private ImageView ivImagen;

    //Paso de parámetros

    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        inicializarVistas();
        inicializarAnimaciones();
        recibirDatosPantallaA();
        mostrarDatos();
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerBeca();
                ejecutarAnimacionDiagonal();
            }
        });
    }

    private void inicializarVistas() {
        tvMostrarDatos = findViewById(R.id.tvMostrarDatos);
        tvBeca = findViewById(R.id.tvBeca);
        btnMostrar = findViewById(R.id.btnMostrar);
        ivImagen = findViewById(R.id.ivImagen);
    }

    private void recibirDatosPantallaA() {
        cliente = (Cliente) this.getIntent().getExtras().getSerializable("objeto_cliente");
    }

    private void mostrarDatos() {
        tvMostrarDatos.setText(cliente.toString());
        if(cliente.isInteresado()){
            tvBeca.setVisibility(View.VISIBLE);
            btnMostrar.setVisibility(View.VISIBLE);
            ivImagen.setVisibility(View.VISIBLE);
        }
    }
    private void obtenerBeca() {
        int valor = 0, valorFinal;
        int aux = cliente.getEdad();
        Random random = new Random();
        int numRandom = random.nextInt(10)+1;
        for (int i = 0; i < String.valueOf(cliente.getEdad()).length(); i++) {
            valor += aux % 10;
            aux /= 10;
        }

        valorFinal = numRandom * valor;
        tvBeca.setText("Suma de dígitos de la edad = "+valor+
                "\nValor aleatorio ="+numRandom+"\nValor final = "+valorFinal);
        if(valorFinal % 2 == 0){
            Toast.makeText(this,"Ganó la beca",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"No obtuvo la beca",Toast.LENGTH_LONG).show();
        }
    }

    private void inicializarAnimaciones() {
        diagonal = AnimationUtils.loadAnimation(this,R.anim.diagonal);
    }
    private void ejecutarAnimacionDiagonal() {
        diagonal.setDuration(4000);
        //La vista en cuestión debe arrancar la animación
        ivImagen.startAnimation(diagonal);
    }
}
