package com.ugb.catalogodeproducto;



import androidx.appcompat.app.AppCompatActivity;




import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;

import android.widget.Spinner;

import android.widget.TextView;

import android.widget.Toast;




import com.google.android.material.floatingactionbutton.FloatingActionButton;


//Integrantes del grupo
// Henry Anderson Sanchez Cortez
// Anna Patricia Gaitan Hernandez
// Xiomara Lisseth Machado De la Paz
//Lesly Carolina Bermudez Membreño




public class MainActivity extends AppCompatActivity {

    DB db_agenda;

    String accion="nuevo";

    String id="";

    Button btn;

    TextView temp;

    FloatingActionButton fab;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        try {

            btn = findViewById(R.id.btnGuardar);

            btn.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View view) {

                    guardar_agenda();

                }

            });

            fab = findViewById(R.id.fabRegresarListaAmigos);

            fab.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View view) {

                    regresarListaAmigos();






















                }

            });

        }catch (Exception e){

            Toast.makeText(this, "Error al cargar: "+ e.getMessage(), Toast.LENGTH_LONG).show();

        }

        mostrar_datos_amigos();

    }

    void mostrar_datos_amigos(){

        try {

            Bundle parametros = getIntent().getExtras();

            accion = parametros.getString("accion");

            if (accion.equals("modificar")) {

                String amigos[] = parametros.getStringArray("amigos");

                id = amigos[0];




                temp = findViewById(R.id.txtnombre);

                temp.setText(amigos[1]);




                temp = findViewById(R.id.txtdireccion);

                temp.setText(amigos[2]);




                temp = findViewById(R.id.txtTelefono);

                temp.setText(amigos[3]);




                temp = findViewById(R.id.txtemail);

                temp.setText(amigos[4]);


                temp = findViewById(R.id.txtmarca);

                temp.setText(amigos[5]);

            }

        }catch (Exception e){

            Toast.makeText(this, "Error al mostrar datos: "+ e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    void guardar_agenda(){

        try {

            temp = (TextView) findViewById(R.id.txtnombre);

            String nombre = temp.getText().toString();




            temp = (TextView) findViewById(R.id.txtdireccion);

            String direccion = temp.getText().toString();




            temp = (TextView) findViewById(R.id.txtTelefono);

            String telefono = temp.getText().toString();




            temp = (TextView) findViewById(R.id.txtemail);

            String email = temp.getText().toString();


            temp = (TextView) findViewById(R.id.txtmarca);

            String marca = temp.getText().toString();




            db_agenda = new DB(MainActivity.this, "",null,1);

            String result = db_agenda.administrar_agenda(id, nombre, direccion, telefono, email,marca, accion);

            String msg = result;

            if( result.equals("ok") ){

                msg = "Registro guardado con exito";

                regresarListaAmigos();

            }

            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

        }catch (Exception e){

            Toast.makeText(this, "Error en guardar agenda: "+ e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    void regresarListaAmigos(){

        Intent iListaAmigos = new Intent(MainActivity.this, lista_amigos.class);

        startActivity(iListaAmigos);

    }

}