package codes.andresen.boardgamecollection.integration;

import codes.andresen.boardgamecollection.model.BoardGame;
import codes.andresen.boardgamecollection.model.BoardGames;
import org.springframework.stereotype.Service;

@Service
public class GameCollectionService {

    private FireBaseIntegrationService fireBaseIntegrationService;
    private BoardGameGeeksIntegrationService boardGameGeeksIntegrationService;

    public GameCollectionService(FireBaseIntegrationService fireBaseIntegrationService, BoardGameGeeksIntegrationService boardGameGeeksIntegrationService) {
        this.fireBaseIntegrationService = fireBaseIntegrationService;
        this.boardGameGeeksIntegrationService = boardGameGeeksIntegrationService;
    }

    public BoardGame getBoardGame(String gameId, String userName) {
        BoardGames boardGames = boardGameGeeksIntegrationService.getBoardGames(gameId);
        fireBaseIntegrationService.writToDBSingleGame(boardGames.getBoardGame().get(0), userName);
        return boardGames.getBoardGame().get(0);
    }

    public String deleteSingleGame(String userName, String gameName) {
        fireBaseIntegrationService.deleteSingleGame(userName, gameName);
        return "Success!";
    }

    public String deleteCollection(String userName) {
        fireBaseIntegrationService.deleteCollection(userName, 100);
        return "Succes!";
    }
}
