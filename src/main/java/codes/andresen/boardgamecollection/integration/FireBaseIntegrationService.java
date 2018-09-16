package codes.andresen.boardgamecollection.integration;

import codes.andresen.boardgamecollection.model.BoardGame;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

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

    void writToDB(BoardGame boardGame) {
        db.collection(boardGame.getGameId()).document(boardGame.getName()).set(boardGame);
        System.out.println("Success: " + boardGame.getName() + " wer written to DB!");
    }

}
