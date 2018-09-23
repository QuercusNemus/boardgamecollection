package codes.andresen.boardgamecollection.facade;

import codes.andresen.boardgamecollection.integration.GameCollectionService;
import codes.andresen.boardgamecollection.model.BoardGame;
import codes.andresen.boardgamecollection.model.CollectionGameDetails;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class BoardGameCollectionController {

    private GameCollectionService gameCollectionService;

    public BoardGameCollectionController(GameCollectionService gameCollectionService) {
        this.gameCollectionService = gameCollectionService;
    }

    @PostMapping("/collection/add")
    public List<CollectionGameDetails> addCollection(
            @RequestHeader(value = "userName") String userName) {
        return gameCollectionService.addGameCollection(userName);
    }

    @PostMapping("/collection/add/{gameId}")
    public CollectionGameDetails addSingleGame(
            @RequestHeader(value = "userName") String userName,
            @PathVariable(value = "gameId") String gameId) {
        return gameCollectionService.addSingleGame(userName, gameId);
    }

    @GetMapping("/collection/read")
    public List<BoardGame> getCollection(
            @RequestHeader(value = "userName") String userName) throws ExecutionException, InterruptedException {
        return gameCollectionService.getGameCollection(userName);
    }

    @GetMapping("/search")
    public BoardGame getGameSearch(
            @RequestHeader(value = "gameId") String gameId) {
        return gameCollectionService.getSingleGame(gameId);
    }

    @DeleteMapping(value = "/collection/delete/game/{gameName}")
    public String deleteSingleGame(
            @RequestHeader(value = "userName") String userName,
            @PathVariable(value = "gameName") String gameName) {
     return gameCollectionService.deleteSingleGame(userName, gameName);
    }

    @DeleteMapping(value = "/collection/delete")
    public String deleteCollection(
            @RequestHeader(value = "userName") String userName) {
        return gameCollectionService.deleteCollection(userName);
    }
}
