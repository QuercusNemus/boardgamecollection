package codes.andresen.boardgamecollection.facade;

import codes.andresen.boardgamecollection.integration.GameCollectionService;
import codes.andresen.boardgamecollection.model.BoardGame;
import codes.andresen.boardgamecollection.model.CollectionGameDetails;
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

    @GetMapping("/collection/add")
    public List<CollectionGameDetails> addCollection(@RequestHeader(value = "userName") String userName) {
        return gameCollectionService.addGameCollection(userName);
    }

    @GetMapping("/collection/add/{gameId}")
    public CollectionGameDetails addSingleGame(
            @RequestHeader(value = "userName") String userName,
            @PathVariable(value = "gameId") String gameId) {
        return gameCollectionService.addSingleGame(userName, gameId);
    }


    @GetMapping("/collection/read")
    public List<CollectionGameDetails> getCollection(@RequestHeader(value = "userName") String userName) throws ExecutionException, InterruptedException {
        return gameCollectionService.getGameCollection(userName);
    }

    @GetMapping("/search")
    public BoardGame getGameSearch(
            @RequestHeader(value = "gameId") String gameId    ) {
        return gameCollectionService.getBoardGameSearch(gameId);
    }
}
