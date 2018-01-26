package com.example.enriq.ecards;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CrearReunion extends AppCompatActivity {

    TextView tv, tve;
    Calendar Date, Time;
    int day, month, year, hour, minute;
    String format;
    Spinner mySpinner;
    Button CrearReunion;
    EditText Titulo, Lugar, Descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_reunion);

        Toolbar toolbar = (Toolbar) findViewById(R.id.include);
        //Toolbar toolbar = (Toolbar) viewToolbar.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Crear Reunion");

        CrearReunion = (Button)  findViewById(R.id.BTNCrearRunion);
        Titulo= (EditText)  findViewById(R.id.EDTtitulo);
        Lugar= (EditText)  findViewById(R.id.EDTlugar);
        Descripcion= (EditText)  findViewById(R.id.EDTdescripcion);

        CrearReunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if  (!MetodosGlobales.validarTelefono(Titulo.getText().toString())){
                    Toast.makeText(com.example.enriq.ecards.CrearReunion.this, "Ingrese un Titulo", Toast.LENGTH_SHORT).show();
                    return;
                }
                if  (!MetodosGlobales.validarTelefono(Lugar.getText().toString())){
                    Toast.makeText(com.example.enriq.ecards.CrearReunion.this, "Ingrese un lugar" , Toast.LENGTH_SHORT).show();
                    return;
                }
                EnviarDatosWS();
            }
        });

        //Fecha
        tv = (TextView) findViewById(R.id.EDTfecha);
        Date = Calendar.getInstance();
        day = Date.get(Calendar.DAY_OF_MONTH);
        month = Date.get(Calendar.MONTH);
        year = Date.get(Calendar.YEAR);

        month = month + 1;
        tv.setText(day + "/" + month + "/" + year);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(com.example.enriq.ecards.CrearReunion.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear + 1;
                        tv.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        //Hora
        tve = (TextView) findViewById(R.id.EDThora);
        Time = Calendar.getInstance();
        hour = Time.get(Calendar.HOUR_OF_DAY);
        minute = Time.get(Calendar.MINUTE);
        selectedTimeFormat(hour);
        tve.setText(hour + " : " + minute + " " + format);
        tve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(com.example.enriq.ecards.CrearReunion.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedTimeFormat(hourOfDay);
                        tve.setText(hourOfDay + " : " + minute + " " + format);
                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        });

        //Spinner

        mySpinner = (Spinner) findViewById(R.id.asistentes);

        List<String> list = new ArrayList<>();
        list.add("Valentina");
        list.add("Laura");
        list.add("Enrique");
        list.add("Ronald");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mySpinner.setAdapter(adapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String itemvalue = parent.getItemAtPosition(position).toString();

                Toast.makeText(com.example.enriq.ecards.CrearReunion.this, "Selected: " + itemvalue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Intent i = new Intent(CrearReunion.this, MenuAdmin.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Hora
    public void selectedTimeFormat(int hour){
        if (hour == 0){
            hour += 12;
            format = "AM";
        } else if (hour == 12){
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
    }

    private void EnviarDatosWS(){}
}
