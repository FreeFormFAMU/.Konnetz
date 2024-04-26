package com.connetz.connetz.services;

import com.connetz.connetz.models.Category;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.jvnet.hk2.annotations.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CategoryServices {
    private final Firestore firestore;

    public CategoryServices(){this.firestore = FirestoreClient.getFirestore();}

    public Category documentSnapshotToCategory(DocumentSnapshot document)
    {
        if(document.exists())
            return document.toObject(Category.class);
        return null;
    }

    public ArrayList<Category> getCategories() throws ExecutionException, InterruptedException {

        Query query = firestore.collection("Category").orderBy("title", Query.Direction.ASCENDING);

        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        ArrayList<Category> categories = documents.size() > 0 ? new ArrayList<>() : null;

        for(QueryDocumentSnapshot doc : documents){
            categories.add(doc.toObject(Category.class));
        }
        return categories;
    }
}
