package codes.andresen.boardgamecollection.integration;

import codes.andresen.boardgamecollection.model.BoardGame;
import codes.andresen.boardgamecollection.model.CollectionGameDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BoardGameGeekIntegrationService {

    private final RestTemplate restTemplate;

    @Autowired
    public BoardGameGeekIntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    BoardGame getBoardGameSearch(String gameId) {
        return restTemplate.getForObject("https://bgg-json.azurewebsites.net/thing/" + gameId, BoardGame.class);
    }

    CollectionGameDetails getSingleCollectionDetails(String gameId) {
        return restTemplate.getForObject("https://bgg-json.azurewebsites.net/thing/" + gameId, CollectionGameDetails.class);
    }

    CollectionGameDetails[] getBoardGameCollection(String userName) {
        return restTemplate.getForObject("https://bgg-json.azurewebsites.net/collection/" + userName, CollectionGameDetails[].class);
    }
}
