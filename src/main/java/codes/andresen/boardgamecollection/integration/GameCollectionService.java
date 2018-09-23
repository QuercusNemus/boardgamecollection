package codes.andresen.boardgamecollection.integration;

import codes.andresen.boardgamecollection.model.BoardGame;
import codes.andresen.boardgamecollection.model.CollectionGameDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class GameCollectionService {

    private FireBaseIntegrationService fireBaseIntegrationService;
    private RestTemplate restTemplate;

    public GameCollectionService(FireBaseIntegrationService fireBaseIntegrationService, RestTemplate restTemplate) {
        this.fireBaseIntegrationService = fireBaseIntegrationService;
        this.restTemplate = restTemplate;
    }

    public List<CollectionGameDetails> addGameCollection(String userName) {
        RestTemplate restTemplate = new RestTemplate();
        CollectionGameDetails[] collectionGameDetails = restTemplate.getForObject("https://bgg-json.azurewebsites.net/collection/" + userName, CollectionGameDetails[].class);

        List<CollectionGameDetails> returnList = new ArrayList<>();
        assert collectionGameDetails != null;
        for (CollectionGameDetails collection : collectionGameDetails) {
            if (collection.isOwned()) {
                returnList.add(collection);
                fireBaseIntegrationService.writToDBSingleGame(collection, userName);
            }
        }
        return returnList;
    }

    public CollectionGameDetails addSingleGame(String userName, String gameId) {
        restTemplate = new RestTemplate();
        CollectionGameDetails gameDetails = restTemplate.getForObject("https://bgg-json.azurewebsites.net/thing/" + gameId, CollectionGameDetails.class);
        assert gameDetails != null;
        gameDetails.setOwned(true);
        fireBaseIntegrationService.writToDBSingleGame(gameDetails, userName);
        return gameDetails;
    }

    public List<CollectionGameDetails> getGameCollection(String userName) throws ExecutionException, InterruptedException {
        return fireBaseIntegrationService.getGameCollection(userName);
    }

    public BoardGame getBoardGameSearch(String id) {
        restTemplate = new RestTemplate();
        return restTemplate.getForObject("https://bgg-json.azurewebsites.net/thing/" + id, BoardGame.class);
    }
}
