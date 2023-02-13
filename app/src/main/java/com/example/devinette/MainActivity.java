package com.example.devinette;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit1;
    RadioButton rdbtn1, rdbtn2;
    RadioGroup radioGroup;
    Button jouer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup=findViewById(R.id.radioGroup);
        rdbtn1 = findViewById(R.id.rdbtn1);
        rdbtn2 = findViewById(R.id.rdbtn2);
        jouer = findViewById(R.id.jouer);
        edit1=findViewById(R.id.edit1);

        jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences("MY_DATA",MODE_PRIVATE);
if(error()){
    if (rdbtn1.isChecked()) {
        Intent intent =new Intent(MainActivity.this,JouerActivity.class);
        String name=edit1.getText().toString();
        name=prefs.getString("my_name",name);
        intent.putExtra("my_name",name);
        ((EditText)findViewById(R.id.edit1)).setText(name);
        startActivity(intent);
    }else if(rdbtn2.isChecked()){
        Intent intent =new Intent(MainActivity.this,HardNiveauActivity.class);
        String name=edit1.getText().toString();
        name=prefs.getString("my_name",name);
        intent.putExtra("my_name",name);
        ((EditText)findViewById(R.id.edit1)).setText(name);
        startActivity(intent);
    }
}
            }
        });

    }

    private boolean error(){
if(edit1.getText().toString().length()==0){
    Toast.makeText(MainActivity.this," Veuillez entrer votre nom",Toast.LENGTH_LONG).show();
    return false;
}
else if (!rdbtn1.isChecked()&&!rdbtn2.isChecked()){
    Toast.makeText(MainActivity.this,"Veuillez selectionner votre niveau",Toast.LENGTH_LONG).show();
    return false;
}
return true;
    }
}