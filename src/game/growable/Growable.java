package game.growable;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * Represents any ground type that can grow fruits, such as a tree, bush, etc.
 *
 * @author NgYuKang
 * @see Ground
 * @see Location
 * @since 25/04/2021
 */
public abstract class Growable extends Ground {

    /**
     * The number of fruits this Growable currently holds.
     */
    private int numberOfRipeFruit = 0;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Growable(char displayChar) {
        super(displayChar);
    }

    /**
     * Override this to give each distinct growable its own chance
     *
     * @return Chance that a Fruit can grow each turn.
     */
    public abstract double growFruitChance();

    /**
     * Used to check if a Fruit would grow.
     */
    public void checkGrowFruit() {
        double chance = Math.random();
        if (chance < growFruitChance()) {
            numberOfRipeFruit++;
            // TODO: Increment EcoPoints
        }
    }

    /**
     * Used to decrease the number of fruit.
     * Mainly used by actions.
     */
    public void decrementNumberOfRipeFruit() {
        numberOfRipeFruit--;
    }

    /**
     *
     * @return The number of fruits currently held by the Growable.
     */
    public int getNumberOfRipeFruit(){
        return numberOfRipeFruit;
    }

    /**
     * Now even a Growable can experience time!
     * Informs the Growable the time has come and we have a job to do
     * Runs checkGrowFruit() every turn.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        checkGrowFruit();
        super.tick(location);
    }

}
