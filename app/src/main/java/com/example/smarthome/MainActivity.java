package com.example.smarthome;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ImageView iv_mic;
    private TextView Text;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_mic = findViewById(R.id.iv_mic);
        Text = findViewById(R.id.text);

        iv_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent
                        = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                        Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

                try {
                    startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
                }
                catch (Exception e) {
                    Toast
                            .makeText(MainActivity.this, " " + e.getMessage(),
                                    Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                Text.setText(
                        Objects.requireNonNull(result).get(0));
                writeToFirebase(result);
            }
        }
    }
    private void writeToFirebase(ArrayList<String> result){
        databaseReference = database.getReference().child("Command");

        if (result.contains("turn on the air conditioner") || result.contains("turn on the air conditioners")){
            databaseReference.setValue("1");
        }
        if (result.contains("turn off the air conditioner") || result.contains("turn off the air conditioners")){
            databaseReference.setValue("0");
        }
        if (result.contains("open the lights") || result.contains("turn on the lights")){
            databaseReference.setValue("2");
        }
        if (result.contains("turn off the lights") || result.contains("close the lights")){
            databaseReference.setValue("3");
        }
        if (result.contains("open the door")){
            databaseReference.setValue("4");
        }
        if (result.contains("close the door") || result.contains("shut the door")){
            databaseReference.setValue("5");
        }
    }
}