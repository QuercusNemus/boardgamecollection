package codes.andresen.boardgamecollection.model;

import lombok.Data;

@Data
public class CollectionGameDetails {
    private String gameId;
    private String name;
    private String image;
    private String minPlayers;
    private String maxPlayers;
    private String playingTime;
    private boolean isExpansion;
    private String yearPublished;
    private String bggRating;
    private String averageRating;
    private String rank;
    private String numPlays;
    private String rating;
    private boolean owned;
    private boolean preOrdered;
    private boolean forTrade;
    private boolean previousOwned;
    private boolean want;
    private boolean wantToPlay;
    private boolean wantToBuy;
    private boolean wishList;
    private String userComment;
}
