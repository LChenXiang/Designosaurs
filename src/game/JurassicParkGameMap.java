package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;
import edu.monash.fit2099.engine.Location;
import game.dinosaur.Dinosaur;
import game.growable.GrowableStatus;
import game.watertile.WaterTileStatus;

import java.io.IOException;
import java.util.List;

/**
 * An extended version of the original GameMap, to suit the
 * JurassicPark's game needs.
 *
 * @author NgYuKang
 * @version 1.0
 * @see GameMap
 * @see JurassicParkLocation
 * @since 25/04/2021
 */
public class JurassicParkGameMap extends GameMap {

//    private boolean rain;
    private int turnElapsed;

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
     * <p>
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

    /**
     * Used to grow all the bushes at the start
     */
    private void initialBushGrowth() {
        for (int y : heights) {
            for (int x : widths) {
                JurassicParkLocation location = (JurassicParkLocation) this.at(x, y);
                if (location.getGround().hasCapability(GrowableStatus.DIRT)) {
                    location.checkBushGrowth();
                }
            }
        }
    }

    /**
     * Called once per turn, so that maps can experience the passage of time.
     */
    @Override
    public void tick() {
        removeRainEnum();
        turnElapsed++;
        if (turnElapsed % 10 == 0){
            double chance = Math.random();
            if (chance < 0.2){
                addRainEnum();
            }
        }
        super.tick();
    }

    /**
     * Adds rain enum to all grounds.
     */
    private void addRainEnum(){
        for (int y : heights) {
            for (int x : widths) {
                this.at(x, y).getGround().addCapability(WaterTileStatus.RAIN);
            }
        }
    }

    /**
     * Removes rain enum to all grounds.
     */
    private void removeRainEnum(){
        for (int y : heights) {
            for (int x : widths) {
                this.at(x, y).getGround().removeCapability(WaterTileStatus.RAIN);
            }
        }
    }

    /**
     *  This is probably not going to be used, but is here regardless.
     *
     * @return How much turn has elapsed for this map. Should be same for all maps.
     */
    public int getTurnElapsed() {
        return turnElapsed;
    }
}
