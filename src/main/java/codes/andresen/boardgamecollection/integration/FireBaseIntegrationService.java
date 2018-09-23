package codes.andresen.boardgamecollection.integration;

import codes.andresen.boardgamecollection.model.BoardGame;
import codes.andresen.boardgamecollection.model.CollectionGameDetails;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
class FireBaseIntegrationService {
    private Firestore db;

    FireBaseIntegrationService() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/ServiceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://boardgamecollection-andresen.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();
    }

    void writToDBSingleGame(CollectionGameDetails boardGame, String userName) {
        db.collection(userName).document(boardGame.getName()).set(boardGame);
        System.out.println("Success: " + boardGame.getName() + " wer added to: " + userName + " collection.");
    }

    void deleteSingleGame(String userName, String gameName) {
        ApiFuture<WriteResult> writeResultApiFuture = db.collection(userName).document(gameName).delete();
    }

    void deleteCollection(String userName, int batchSize) {
        CollectionReference collection = db.collection(userName);
        try {
            // retrieve a small batch of documents to avoid out-of-memory errors
            ApiFuture<QuerySnapshot> future = collection.limit(batchSize).get();
            int deleted = 0;
            // future.get() blocks on document retrieval
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                document.getReference().delete();
                ++deleted;
            }
            if (deleted >= batchSize) {
                // retrieve and delete another batch
                deleteCollection(userName, batchSize);
            }
        } catch (Exception e) {
            System.err.println("Error deleting collection : " + e.getMessage());
        }
    }

    List<CollectionGameDetails> getGameCollection(String userName) throws ExecutionException, InterruptedException {
        CollectionReference documentReference = db.collection(userName);
        ApiFuture<QuerySnapshot> future = documentReference.get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();

        List<CollectionGameDetails> returnList = new ArrayList<>();

        for (QueryDocumentSnapshot doc : documentSnapshots) {
            CollectionGameDetails game = doc.toObject(CollectionGameDetails.class);
            returnList.add(game);
        }

        return returnList;
    }
}