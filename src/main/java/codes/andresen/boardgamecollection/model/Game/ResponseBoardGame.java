package codes.andresen.boardgamecollection.model.Game;

import lombok.Data;

import java.util.List;

@Data
public class ResponseBoardGame {
    private int gameId;
    private int yearPublished;
    private int minPlayers;
    private int maxPlayers;
    private int playingTime;
    private int age;
    private String description;
    private String thumbnail;
    private String image;
    private String primaryGameName;
    private List<Name> nameList;

    public static ResponseBoardGame fromBoardGame(BoardGame boardGame) {
        ResponseBoardGame responseBoardGame = new ResponseBoardGame();

        responseBoardGame.setGameId(boardGame.getGameId());
        responseBoardGame.setYearPublished(boardGame.getYearPublished());
        responseBoardGame.setMinPlayers(boardGame.getMinPlayers());
        responseBoardGame.setMaxPlayers(boardGame.getMaxPlayers());
        responseBoardGame.setPlayingTime(boardGame.getPlayingTime());
        responseBoardGame.setAge(boardGame.getAge());
        responseBoardGame.setDescription(boardGame.getDescription());
        responseBoardGame.setThumbnail(boardGame.getThumbnail());
        responseBoardGame.setImage(boardGame.getImage());
        responseBoardGame.setPrimaryGameName(boardGame.getPrimaryGameName());
        responseBoardGame.setNameList(boardGame.getNameList());

        return responseBoardGame;
    }
}
