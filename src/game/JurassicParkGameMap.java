package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;
import edu.monash.fit2099.engine.Location;
import game.dinosaur.Dinosaur;

import java.io.IOException;
import java.util.List;

/**
 * An extended version of the original GameMap, to suit the
 * JurassicPark's game needs.
 *
 * @see GameMap
 */
public class JurassicParkGameMap extends GameMap {

    /**
     * Constructor.
     *
     * @param groundFactory Factory to create Ground objects
     * @param groundChar    Symbol that will represent empty Ground in this map
     * @param width         width of the GameMap, in characters
     * @param height        height of the GameMap, in characters
     */
    public JurassicParkGameMap(GroundFactory groundFactory, char groundChar, int width, int height) {
        super(groundFactory, groundChar, width, height);
        initialBushGrowth();
    }

    /**
     * Constructor that creates a map from a sequence of ASCII strings.
     *
     * @param groundFactory Factory to create Ground objects
     * @param lines         List of Strings representing rows of the map
     */
    public JurassicParkGameMap(GroundFactory groundFactory, List<String> lines) {
        super(groundFactory, lines);
        initialBushGrowth();
    }

    /**
     * Constructor that reads a map from file.
     *
     * @param groundFactory Factory to create Ground objects
     * @param mapFile       Name of a file containing an ASCII representation of a
     *                      level
     * @throws IOException when file I/O fails
     */
    public JurassicParkGameMap(GroundFactory groundFactory, String mapFile) throws IOException {
        super(groundFactory, mapFile);
        initialBushGrowth();
    }

    /**
     * Creates a new Location.
     *
     * Overridden to use JurassicParkLocation.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @return a new Location.
     */
    @Override
    protected Location makeNewLocation(int x, int y) {
        return new JurassicParkLocation(this, x, y);
    }

    private void initialBushGrowth(){
        for (int y : heights) {
            for (int x : widths) {
                JurassicParkLocation location = (JurassicParkLocation) this.at(x,y);
                if (location.getGround() instanceof Dirt){
                    location.checkBushGrowth();
                }
            }
        }
    }

}
