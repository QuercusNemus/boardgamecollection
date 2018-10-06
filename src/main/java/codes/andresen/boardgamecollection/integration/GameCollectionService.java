package codes.andresen.boardgamecollection.integration;

import codes.andresen.boardgamecollection.model.BoardGame;
import codes.andresen.boardgamecollection.model.CollectionGameDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class GameCollectionService {

    private FireBaseIntegrationService fireBaseIntegrationService;
    private BoardGameGeekIntegrationService boardGameGeekIntegrationService;
    private BoardGameGeeksIntegrationXMLService boardGameGeeksIntegrationXMLService;

    public GameCollectionService(FireBaseIntegrationService fireBaseIntegrationService, BoardGameGeekIntegrationService boardGameGeekIntegrationService, BoardGameGeeksIntegrationXMLService boardGameGeeksIntegrationXMLService) {
        this.fireBaseIntegrationService = fireBaseIntegrationService;
        this.boardGameGeekIntegrationService = boardGameGeekIntegrationService;
        this.boardGameGeeksIntegrationXMLService = boardGameGeeksIntegrationXMLService;
    }

    public List<CollectionGameDetails> addGameCollection(String userName) {
        CollectionGameDetails[] collectionGameDetails = boardGameGeekIntegrationService.getBoardGameCollection(userName);

        boardGameGeeksIntegrationXMLService.testXML();

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
        CollectionGameDetails gameDetails = boardGameGeekIntegrationService.getSingleCollectionDetails(gameId);
        assert gameDetails != null;
        gameDetails.setOwned(true);
        fireBaseIntegrationService.writToDBSingleGame(gameDetails, userName);
        return gameDetails;
    }

    public List<BoardGame> getGameCollection(String userName) throws ExecutionException, InterruptedException {
        List<CollectionGameDetails> collectionGameDetailsList = fireBaseIntegrationService.getGameCollection(userName);
        List<BoardGame> boardGameList = new ArrayList<>();

        for (CollectionGameDetails collectionGameDetails : collectionGameDetailsList) {
            BoardGame boardGame = boardGameGeekIntegrationService.getBoardGameSearch(collectionGameDetails.getGameId());
            boardGameList.add(boardGame);
        }
        boardGameGeeksIntegrationXMLService.testXML();
        return boardGameList;
    }

    public BoardGame getSingleGame(String gameId) {
        BoardGame boardGame = boardGameGeekIntegrationService.getBoardGameSearch(gameId);
        System.out.println(boardGame);
        return boardGame;
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
