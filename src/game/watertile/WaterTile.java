package game.watertile;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.JurassicParkGameMap;
import game.dinosaur.DinosaurStatus;

/**
 * Used to represent any ground tiles that has water and bears fish (Lakes, rivers, Seas).
 */
public abstract class WaterTile extends Ground {

    /**
     * How much water capacity this tile holds.
     */
    protected int sipCapacity;

    /**
     * How much fish in this tile
     */
    protected int fishCount;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public WaterTile(char displayChar, int startingSip, int startingFish) {
        super(displayChar);
        sipCapacity = startingSip;
        fishCount = startingFish;
    }

    protected abstract void increaseSipCapacity();

    protected abstract void checkFishGrowth();

    public int getSipCapacity() {
        return sipCapacity;
    }

    public int getFishCount() {
        return fishCount;
    }

    public void decreaseFishCount(int count) {
        fishCount -= count;
        fishCount = Math.max(0, fishCount);
    }

    public void decreaseSipCount(){
        sipCapacity--;
    }

    /**
     * Override this to implement impassable terrain, or terrain that is only passable if conditions are met.
     * Checks if actor can traverse the lake (can fly)
     *
     * @param actor the Actor to check
     * @return true if flying, else false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(DinosaurStatus.CAN_FLY);
    }

    /**
     * Ground can also experience the joy of time.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.map() instanceof JurassicParkGameMap) {
            if (((JurassicParkGameMap) location.map()).isRain()) {
                increaseSipCapacity();
            }
        }
        checkFishGrowth();
    }

}