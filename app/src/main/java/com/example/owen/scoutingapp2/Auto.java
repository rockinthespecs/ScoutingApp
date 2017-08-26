package com.example.owen.scoutingapp2;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Auto extends AppCompatActivity {
    public EditText editText;
    public EditText editText2;
    public TextView textView;
    public Button save;
    public boolean checked=false;
    public Spinner spinner;
    public String text3;
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/aaTutorial";
    public String checkbox1="";
    ArrayAdapter<CharSequence> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);
        editText = (EditText) ((findViewById(R.id.editText)));
        editText2 = (EditText) ((findViewById(R.id.editText2)));

        textView = (TextView) findViewById(R.id.textView);
        save = (Button) findViewById(R.id.save);

        File dir = new File(path);
        dir.mkdirs();

        spinner=(Spinner)findViewById(R.id.spinner);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);

        adapter=ArrayAdapter.createFromResource(this,R.array.Gear_Location,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                text3=String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+" selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void gearUp(View view){
        try {
            editText.setText(String.valueOf((Integer.parseInt(String.valueOf(editText.getText()))) + 1));
        }
        catch(NumberFormatException e){
            System.out.println("Not a number");
        }
    }
    public void
    gearDown(View view){
        try {
            editText.setText(String.valueOf((Integer.parseInt(String.valueOf(editText.getText()))) - 1));
        }
        catch(NumberFormatException e){
            System.out.println("Not a number");
        }
    }

    public void ballDown(View view){
        try {
            editText2.setText(String.valueOf((Integer.parseInt(String.valueOf(editText2.getText()))) - 10));
        }
        catch(NumberFormatException e){
            System.out.println("Not a number");
        }
    }
    public void ballUp(View view){
        try {
            editText2.setText(String.valueOf((Integer.parseInt(String.valueOf(editText2.getText()))) + 10));
        }
        catch(NumberFormatException e){
            System.out.println("Not a number");
        }
    }
    public void baseline (View view){

        if(((CheckBox)view).isChecked()){
            checkbox1="crossed";
        }
    }

    public void buttonSave (View view)
    {
        File file = new File (path + "/savedFile.txt");
        String  text=(String.valueOf(editText.getText()));
        String  text2=(String.valueOf(editText2.getText()));


        String [] saveText = (text+text2+text3+checkbox1).split(System.getProperty("line.separator"));

        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();

        Save (file, saveText);


    }


    public static void Save(File file, String[] data)
    {
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(file);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        try
        {
            try
            {
                for (int i = 0; i<data.length; i++)
                {
                    fos.write(data[i].getBytes());
                    if (i < data.length-1)
                    {
                        fos.write("\n".getBytes());
                    }
                }
            }
            catch (IOException e) {e.printStackTrace();}
        }
        finally
        {
            try
            {
                fos.close();
            }
            catch (IOException e) {e.printStackTrace();}
        }
    }

}

