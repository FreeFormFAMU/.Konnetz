package com.connetz.connetz.services;

import com.connetz.connetz.models.Category;
import com.connetz.connetz.models.post.Post;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

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

    public List<Category> getCategories() throws ExecutionException, InterruptedException {

        CollectionReference categoryCollection = firestore.collection("Category");

        ApiFuture<QuerySnapshot> future = categoryCollection.get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Category> categoryList = new ArrayList<>();

        for(DocumentSnapshot document: documents)
        {
            categoryList.add(documentSnapshotToCategory(document));
        }

        return categoryList;
    }
}
