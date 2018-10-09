package codes.andresen.boardgamecollection.model.Game;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name = "boardgame")
@XmlAccessorType(XmlAccessType.FIELD)
public class BoardGame {
    @XmlAttribute(name = "objectid")
    private int gameId;
    @XmlElement(name = "yearpublished")
    private int yearPublished;
    @XmlElement(name = "name")
    private List<Name> nameList;
    @XmlElement(name = "minplayers")
    private int minPlayers;
    @XmlElement(name = "maxplayers")
    private int maxPlayers;
    @XmlElement(name = "playingtime")
    private int playingTime;
    @XmlElement(name = "age")
    private int age;
    @XmlElement(name = "description")
    private String description;
    @XmlElement(name = "thumbnail")
    private String thumbnail;
    @XmlElement(name = "image")
    private String image;

    public String getPrimaryGameName() {
        for (Name name : nameList) {
            if (name.isPrimary()) {
                return name.getName();
            }
        }
        return nameList.get(0).getName();
    }
}
