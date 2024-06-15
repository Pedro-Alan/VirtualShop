package com.example.virtualshop.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseUtil {

    //Checa o banco de itens
    public static CollectionReference allItemsCollectionReference() {
        return FirebaseFirestore.getInstance().collection("items");
    }
}