package com.example.virtualshop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class FYP_Page extends AppCompatActivity {
    EditText fypEmailField;
    FirebaseAuth fbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fyp_page);

        initializeAttributes();
        fbAuth = DatabaseAuth.DBAuthentication();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void initializeAttributes(){
        fypEmailField = findViewById(R.id.fypEmailField);
    }

    public void changePassword(View v){
        String fypEmail = fypEmailField.getText().toString();

        if(!TextUtils.isEmpty(fypEmail)){
            fbAuth.sendPasswordResetEmail(fypEmail).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(FYP_Page.this, "Reset password link has been sent to the registered e-mail", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(FYP_Page.this, LoginPage.class);
                    startActivity(in);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(FYP_Page.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Toast.makeText(FYP_Page.this, "Insert your e-mail!", Toast.LENGTH_SHORT).show();
        }
    }

    public void toLoginPage(View v){
        Intent in = new Intent(FYP_Page.this, LoginPage.class);
        startActivity(in);
    }
}