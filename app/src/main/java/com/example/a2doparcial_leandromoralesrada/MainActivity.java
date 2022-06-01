package com.example.a2doparcial_leandromoralesrada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //Atributos

    private ArrayList<String> opciones;

    //Componentes visuales

    private EditText etNombre, etApellido, etEdad, etCI;
    private Spinner spPais;
    private Switch swInteres;
    private Button btnRegistrar;

    //Paso de parámetros

    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarVistas();
        popularSpinner();
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerInformacion();
            }
        });
    }

    private void popularSpinner() {
        opciones = new ArrayList<>(Arrays.asList("¿De qué país es?","Bolivia","Argentina","Brasil"));
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                opciones
        );
        spPais.setAdapter(adaptador);
    }

    private void inicializarVistas() {
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etEdad = findViewById(R.id.etEdad);
        etCI = findViewById(R.id.etCI);
        spPais = findViewById(R.id.spPais);
        swInteres = findViewById(R.id.swInteres);
        btnRegistrar = findViewById(R.id.btnRegistrar);
    }

    private void obtenerInformacion() {
        if(etNombre.getText().toString().isEmpty() || etApellido.getText().toString().isEmpty() ||
            etEdad.getText().toString().isEmpty() || etCI.getText().toString().isEmpty()){
            Toast.makeText(this,"Debe ingresar todos los datos",Toast.LENGTH_LONG).show();
        }
        else if(etNombre.getText().toString().length() < 3){
            Toast.makeText(this,"El nombre ingresado es demasiado pequeño",Toast.LENGTH_LONG).show();
        }
        else if(etApellido.getText().toString().length() < 3){
            Toast.makeText(this,"El apellido ingresado es demasiado pequeño",Toast.LENGTH_LONG).show();
        }
        else if(Integer.parseInt(etEdad.getText().toString()) < 15){
            Toast.makeText(this,"Usted es demasiado menor para estar aquí",Toast.LENGTH_LONG).show();
        }
        else if(Integer.parseInt(etEdad.getText().toString()) >= 100){
            Toast.makeText(this,"Valor ingresado para edad no válido",Toast.LENGTH_LONG).show();
        }
        else if(spPais.getSelectedItem().toString().equals("¿De qué país es?")){
            Toast.makeText(this,"Seleccione un país de origen",Toast.LENGTH_LONG).show();
        }
        else if(etCI.getText().toString().length() < 7){
            Toast.makeText(this,"El CI ingresado es demasiado pequeño",Toast.LENGTH_LONG).show();
        }
        else{
            cliente = new Cliente(etNombre.getText().toString(),
                    etApellido.getText().toString(),
                    Integer.parseInt(etEdad.getText().toString()),
                    etCI.getText().toString(),
                    spPais.getSelectedItem().toString(),
                    swInteres.isChecked()
            );
            pasarOtraPantalla();
        }
    }

    private void pasarOtraPantalla() {
        Intent intencion = new Intent(this, HomeActivity.class);
        Bundle archivoTemporal = new Bundle();
        archivoTemporal.putSerializable("objeto_cliente", cliente);
        intencion.putExtras(archivoTemporal);
        startActivity(intencion);
    }
}
