package com.example.virtualshop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class RegistrationPage extends AppCompatActivity {
    EditText regNameField, regEmailField, regPhoneField, regPasswordField, confirmPasswordField;
    FirebaseAuth fbAuth;


    //Inicializa a tela e seus componentes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration_page);

        initializeAttributes();
        fbAuth = DatabaseAuth.DBAuthentication();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Inicializa os atributos necessários
    public void initializeAttributes(){
        regNameField = findViewById(R.id.regNameField);
        regEmailField = findViewById(R.id.regEmailField);
        regPhoneField = findViewById(R.id.regPhoneField);
        regPasswordField = findViewById(R.id.regPasswordField);
        confirmPasswordField = findViewById(R.id.confirmPasswordField);
    }

    //Checa se os dados foram passados corretamente e os armazena no banco de dados
    public void registerUser(View v){
        String regName = regNameField.getText().toString();
        String regEmail = regEmailField.getText().toString();
        String regPhone = regPhoneField.getText().toString();
        String regPassword = regPasswordField.getText().toString();
        String confPassword = confirmPasswordField.getText().toString();

        if(!TextUtils.isEmpty(regName)){
            if(!TextUtils.isEmpty(regEmail)){
                if(!TextUtils.isEmpty(regPhone)){
                    if(!TextUtils.isEmpty(regPassword)){
                        if(!TextUtils.isEmpty(confPassword)){
                            if(regPassword.equals(confPassword)){
                                fbAuth.createUserWithEmailAndPassword(regEmail, regPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(RegistrationPage.this, "User successfully registered", Toast.LENGTH_SHORT).show();
                                            Intent in = new Intent(RegistrationPage.this, LoginPage.class);
                                            startActivity(in);
                                        }
                                        else{
                                            String error;

                                            try{
                                                throw task.getException();
                                            }
                                            catch(FirebaseAuthWeakPasswordException e){
                                                error = "Insert a stronger password!";
                                            }
                                            catch (Exception e) {
                                                error = "Login error: " + e.getMessage();
                                                e.printStackTrace();
                                            }

                                            Toast.makeText(RegistrationPage.this, error, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                            else{
                                Toast.makeText(RegistrationPage.this, "Passwords don't match!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(RegistrationPage.this, "Confirm you password!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(RegistrationPage.this, "Insert your password!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(RegistrationPage.this, "Insert your phone number!", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(RegistrationPage.this, "Insert your e-mail!", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(RegistrationPage.this, "Insert your name!", Toast.LENGTH_SHORT).show();
        }
    }

    //Vai para a página de login
    public void toLoginPage(View v){
        Intent in = new Intent(RegistrationPage.this, LoginPage.class);
        startActivity(in);
    }
}