package com.example.virtualshop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginPage extends AppCompatActivity {
    EditText emailField, passwordField;
    private FirebaseAuth fbAuth;

    //Inicializa a tela e seus componentes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_page);

        fbAuth = DatabaseAuth.DBAuthentication();
        initializeAttributes();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Vai para a tela de recuperar a senha
        TextView fypView = findViewById(R.id.fypView);
        fypView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LoginPage.this, FYP_Page.class);
                startActivity(in);
            }
        });
    }

    //Inicializa os atributos necessários
    public void initializeAttributes(){
        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
    }

    //Checa os campos de texto e os passa para a função de logIn
    public void checkCredentials(View v){
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if(!TextUtils.isEmpty(email)){
            if(!TextUtils.isEmpty(password)){
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);

                logIn(user);
            }
            else{
                Toast.makeText(LoginPage.this, "Insert your password!", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(LoginPage.this, "Insert your e-mail!", Toast.LENGTH_SHORT).show();
        }
    }

    //Confere se as informações recebidas se encontram no banco de dados e realiza diferentes ações com base no retorno
    private void logIn(User user){
        fbAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    toMainPage();
                }
                else {
                    String error;
                    try {
                        throw task.getException();
                    }
                    catch (FirebaseAuthInvalidUserException e) {
                        error = "User not registered.";
                    }
                    catch (FirebaseAuthInvalidCredentialsException e) {
                        error = "Wrong email and/or password.";
                    }
                    catch (Exception e) {
                        error = "Login error: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(LoginPage.this, error, Toast.LENGTH_SHORT).show();
                    emailField.setText(null);
                    passwordField.setText(null);
                }
            }
        });
    }

    //Vai para a página de produtos
    public void toMainPage(){
        Intent in = new Intent(LoginPage.this, MainPage.class);
        startActivity(in);
    }

    //Vai para a página de cadastro
    public void toRegScreen(View v){
        Intent in = new Intent(LoginPage.this, RegistrationPage.class);
        startActivity(in);
    }
}