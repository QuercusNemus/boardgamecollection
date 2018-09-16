package codes.andresen.boardgamecollection.integration;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

@Service
class FireBaseIntegrationService {
    private Firestore db = FirestoreClient.getFirestore();

    FireBaseIntegrationService() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("ServiceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://boardgamecollection-andresen.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    }

    void writToDB(String test) {
        ApiFuture<WriteResult> future = db.collection("boardGames").document("boardGamesDetails")
                .set(test);

        System.out.println("Success: " + test + " wer written to DB!");
    }

}
