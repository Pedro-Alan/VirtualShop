package com.example.virtualshop.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseUtil {
    public static boolean isLogged() {
        return FirebaseAuth.getInstance().getUid() != null;
    }

    public static CollectionReference allItemsCollectionReference() {
        return FirebaseFirestore.getInstance().collection("items");
    }
}