package com.example.virtualshop;

import com.google.firebase.auth.FirebaseAuth;

public class DatabaseAuth {
    private static FirebaseAuth auth;

    //Permite a utilização dos métodos de autenticação do Firebase
    public static FirebaseAuth DBAuthentication(){
        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
}
