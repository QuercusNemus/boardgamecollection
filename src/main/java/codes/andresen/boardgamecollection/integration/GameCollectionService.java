package codes.andresen.boardgamecollection.integration;

import codes.andresen.boardgamecollection.model.BoardGame;
import codes.andresen.boardgamecollection.model.CollectionGameDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameCollectionService {

    public GameCollectionService(FireBaseIntegrationService fireBaseIntegrationService) {
        this.fireBaseIntegrationService = fireBaseIntegrationService;
    }

    private FireBaseIntegrationService fireBaseIntegrationService;

    public List<CollectionGameDetails> getGameCollection(String userName) {
        RestTemplate restTemplate = new RestTemplate();
        CollectionGameDetails[] collectionGameDetails = restTemplate.getForObject("https://bgg-json.azurewebsites.net/collection/" + userName, CollectionGameDetails[].class);

        List<CollectionGameDetails> returnList = new ArrayList<>();
        assert collectionGameDetails != null;
        for (CollectionGameDetails collection : collectionGameDetails) {
            if (collection.isOwned()) {
                returnList.add(collection);
            }
        }
        return returnList;
    }

    public BoardGame getBoardGameSearch(String id) {
        RestTemplate restTemplate = new RestTemplate();

        fireBaseIntegrationService.writToDB("Eric Test");

        return restTemplate.getForObject("https://bgg-json.azurewebsites.net/thing/" + id, BoardGame.class);
    }
}
