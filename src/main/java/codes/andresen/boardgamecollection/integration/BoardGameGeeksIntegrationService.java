package codes.andresen.boardgamecollection.integration;

import codes.andresen.boardgamecollection.model.Game.BoardGames;
import codes.andresen.boardgamecollection.model.collection.Items;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BoardGameGeeksIntegrationService {

    private final RestTemplate restTemplate;

    public BoardGameGeeksIntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    BoardGames getBoardGames(int gameId) {
        return restTemplate.getForObject("https://www.boardgamegeek.com/xmlapi/boardgame/" + gameId, BoardGames.class);
    }

    Items getCollection(String userName) {
        return restTemplate.getForObject("https://www.boardgamegeek.com/xmlapi/collection/" + userName, Items.class);
    }
}
