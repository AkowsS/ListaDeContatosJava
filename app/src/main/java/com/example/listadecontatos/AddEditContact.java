package com.example.listadecontatos;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddEditContact extends AppCompatActivity {
    private ImageView profileIv;
    private EditText nameEt, phoneEt, emailEt;
    private FloatingActionButton fab;

    private String name, phone, email;

    private ActionBar actionBar;

    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_contact);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Add Contact");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        dbHelper = new DbHelper(this);

        profileIv = findViewById(R.id.profileIv);
        nameEt = findViewById(R.id.nameEt);
        phoneEt = findViewById(R.id.phoneEt);
        emailEt = findViewById(R.id.emailEt);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData() {
        name = nameEt.getText().toString().trim();
        phone = phoneEt.getText().toString().trim();
        email = emailEt.getText().toString().trim();

        String timeStamp = "" + System.currentTimeMillis();

        if (!name.isEmpty() || !phone.isEmpty() || !email.isEmpty()) {
            long id = dbHelper.insertContact(
                    name,
                    phone,
                    email,
                    timeStamp,
                    timeStamp
            );
            Toast.makeText(getApplicationContext(), "Inserted " + id, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Nothing to save...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        return super.onSupportNavigateUp();
    }
}
