package codes.andresen.boardgamecollection.integration;

import codes.andresen.boardgamecollection.model.BoardGames;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BoardGameGeeksIntegrationService {

    private final RestTemplate restTemplate;

    public BoardGameGeeksIntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    BoardGames getBoardGames(String gameId) {
        return restTemplate.getForObject("https://www.boardgamegeek.com/xmlapi/boardgame/" + gameId, BoardGames.class);
    }
}
