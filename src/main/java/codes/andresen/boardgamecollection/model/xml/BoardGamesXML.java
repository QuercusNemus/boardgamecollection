package codes.andresen.boardgamecollection.model.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "boardgames")
public class BoardGamesXML {
    @XmlElement(name = "boardgame")
    private List<BoardGameXML> boardGame;
}
