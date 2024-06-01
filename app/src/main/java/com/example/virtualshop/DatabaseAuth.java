package com.example.virtualshop;

import com.google.firebase.auth.FirebaseAuth;

public class DatabaseAuth {
    private static FirebaseAuth auth;

    public static FirebaseAuth DBAuthentication(){
        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
}
