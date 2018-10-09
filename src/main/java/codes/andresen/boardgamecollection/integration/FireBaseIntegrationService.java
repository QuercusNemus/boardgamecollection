package codes.andresen.boardgamecollection.integration;

import codes.andresen.boardgamecollection.model.Game.BoardGame;
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

    String addSingleGameToCollection(BoardGame boardGame, String userName) {
        db.collection(userName).document(boardGame.getPrimaryGameName()).set(boardGame);
        return boardGame.getPrimaryGameName() + " wer added to: " + userName + " collection.\n";
    }

    void deleteSingleGame(String userName, String gameName) {
        db.collection(userName).document(gameName).delete();
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

    List<BoardGame> getGameCollection(String userName) throws ExecutionException, InterruptedException {
        CollectionReference documentReference = db.collection(userName);
        ApiFuture<QuerySnapshot> future = documentReference.get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();

        List<BoardGame> returnList = new ArrayList<>();

        for (QueryDocumentSnapshot doc : documentSnapshots) {
            BoardGame game = doc.toObject(BoardGame.class);
            returnList.add(game);
        }

        return returnList;
    }
}