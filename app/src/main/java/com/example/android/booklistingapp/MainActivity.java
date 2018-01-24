package com.example.android.booklistingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText queryEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void search(View view){

        queryEditText = (EditText) findViewById(R.id.search_text);
        String queryString = queryEditText.getText().toString();

        Intent resultIntent = new Intent(this, ResultActivity.class);
        resultIntent.putExtra("message", queryString);
        startActivity(resultIntent);
        Toast.makeText(this, "Showing results for: " + queryString,  Toast.LENGTH_LONG).show();
    }
}
