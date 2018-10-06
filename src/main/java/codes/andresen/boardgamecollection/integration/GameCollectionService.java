package codes.andresen.boardgamecollection.integration;

import codes.andresen.boardgamecollection.model.Game.BoardGame;
import codes.andresen.boardgamecollection.model.Game.BoardGames;
import codes.andresen.boardgamecollection.model.collection.Item;
import codes.andresen.boardgamecollection.model.collection.Items;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class GameCollectionService {

    private FireBaseIntegrationService fireBaseIntegrationService;
    private BoardGameGeeksIntegrationService boardGameGeeksIntegrationService;

    public GameCollectionService(FireBaseIntegrationService fireBaseIntegrationService, BoardGameGeeksIntegrationService boardGameGeeksIntegrationService) {
        this.fireBaseIntegrationService = fireBaseIntegrationService;
        this.boardGameGeeksIntegrationService = boardGameGeeksIntegrationService;
    }

    public String getBoardGame(int gameId, String userName) {
        BoardGames boardGames = boardGameGeeksIntegrationService.getBoardGames(gameId);
        return fireBaseIntegrationService.addSingleGameToCollection(boardGames.getBoardGame().get(0), userName);
    }

    public StringBuilder getCollection(String userName) {
        Items collection = boardGameGeeksIntegrationService.getCollection(userName);
        StringBuilder returnString = new StringBuilder();
        for (Item game : collection.getItemList()) {
            if (!game.getStatus().isOwned()) {
                continue;
            }
            BoardGames boardGames = boardGameGeeksIntegrationService.getBoardGames(game.getGameId());
            returnString.append(fireBaseIntegrationService.addSingleGameToCollection(boardGames.getBoardGame().get(0), userName));
        }
        return returnString;
    }

    public List<BoardGame> getGameCollection(String userName) throws ExecutionException, InterruptedException {
        return fireBaseIntegrationService.getGameCollection(userName);
    }

    public String deleteSingleGame(String userName, String gameName) {
        fireBaseIntegrationService.deleteSingleGame(userName, gameName);
        return "Success!";
    }

    public String deleteCollection(String userName) {
        fireBaseIntegrationService.deleteCollection(userName, 100);
        return "Success!";
    }
}
