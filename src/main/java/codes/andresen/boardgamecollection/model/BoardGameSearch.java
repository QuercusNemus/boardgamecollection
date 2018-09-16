package codes.andresen.boardgamecollection.model;

import lombok.Data;

@Data
public class BoardGameSearch {
    private String boardGameId;
    private String name;
    private String yearPublished;
}
