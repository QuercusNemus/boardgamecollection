package codes.andresen.boardgamecollection.model;

import lombok.Data;

@Data
public class BoardGame {
    private String gameId;
    private String name;
    private String description;
    private String image;
    private String thumbnail;
    private String minPlayers;
    private String maxPlayers;
    private String playingTime;
    private boolean isExpansion;
    private String yearPublished;
    private String bggRating;
    private String averageRating;
    private String rank;
}
