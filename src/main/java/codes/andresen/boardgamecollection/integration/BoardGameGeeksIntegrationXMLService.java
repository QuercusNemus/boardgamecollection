package codes.andresen.boardgamecollection.integration;

import codes.andresen.boardgamecollection.model.xml.BoardGamesXML;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BoardGameGeeksIntegrationXMLService {

    private final RestTemplate restTemplate;

    public BoardGameGeeksIntegrationXMLService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void testXML() {
        BoardGamesXML boardGamesXML = restTemplate.getForObject("https://www.boardgamegeek.com/xmlapi/boardgame/2536", BoardGamesXML.class);
    }
}
