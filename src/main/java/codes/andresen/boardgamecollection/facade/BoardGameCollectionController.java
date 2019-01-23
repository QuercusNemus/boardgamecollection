package codes.andresen.boardgamecollection.facade;

import codes.andresen.boardgamecollection.integration.GameCollectionService;
import codes.andresen.boardgamecollection.model.Game.BoardGame;
import codes.andresen.boardgamecollection.model.Game.BoardGames;
import io.netty.handler.codec.Headers;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @CrossOrigin()
    @PostMapping("/collection/add")
    public StringBuilder addCollection(
            @RequestHeader(value = "userName") String userName) {
        return gameCollectionService.getCollection(userName);
    }

    @CrossOrigin()
    @PostMapping("/collection/add/{gameId}")
    public String addSingleGame(
            @RequestHeader(value = "userName") String userName,
            @PathVariable(value = "gameId") int gameId) {
        return gameCollectionService.getBoardGame(gameId, userName);
    }

    @CrossOrigin()
    @GetMapping("/collection/read")
    public List<BoardGame> getCollection(
            @RequestHeader(value = "userName") String userName) throws ExecutionException, InterruptedException {
        return gameCollectionService.getGameCollection(userName);
    }

    @CrossOrigin()
    @GetMapping("/search/game")
    public BoardGames getSearchGame(
            @RequestHeader(value = "searchString") String searchString) {
        return gameCollectionService.searchBoardGames(searchString);
    }

    @CrossOrigin()
    @GetMapping("/get/game")
    public BoardGames getGame(
            @RequestHeader(value = "gameId") int gameId) {
        return gameCollectionService.getGame(gameId);
    }

    @CrossOrigin()
    @DeleteMapping(value = "/collection/delete/game/{gameName}")
    public String deleteSingleGame(
            @RequestHeader(value = "userName") String userName,
            @PathVariable(value = "gameName") String gameName) {
     return gameCollectionService.deleteSingleGame(userName, gameName);
    }

    @CrossOrigin()
    @DeleteMapping(value = "/collection/delete")
    public String deleteCollection(
            @RequestHeader(value = "userName") String userName) {
        return gameCollectionService.deleteCollection(userName);
    }
}
