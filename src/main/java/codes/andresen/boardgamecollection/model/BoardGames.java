package codes.andresen.boardgamecollection.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "boardgames")
@XmlAccessorType(XmlAccessType.FIELD)
public class BoardGames {
    @XmlElement(name = "boardgame")
    private List<BoardGame> boardGame;

    public List<BoardGame> getBoardGame() {
        return boardGame;
    }
}
