package game.growable;

import edu.monash.fit2099.engine.Location;
import game.PortableItem;

/**
 * Represents a Tree.
 *
 * @author NgYuKang
 * @see Growable
 * @since 25/04/2021
 */
public class Tree extends Growable {
    /**
     * Age of a tree.
     */
    private int age = 0;

    /**
     * Constructor.
     * New tree starts with character +.
     * As they grow, it will turn into t, then T.
     * Has TALL enum, to be used by behaviours or actions.
     */
    public Tree() {
        super('+');
        addCapability(GrowableStatus.TALL);
    }

    /**
     *
     * @return Chance that a fruit will grow every turn in a tree.
     */
    @Override
    public double growFruitChance() {
        return 0.5;
    }

    /**
     *
     * @return Chance that a fruit will drop from a tree every turn.
     */
    public double dropFruitChance() {
        return 0.05;
    }

    /**
     *
     * @return Used to check if a fruit will drop from a tree.
     */
    public boolean canDropFruit() {
        // RNGesus
        double chance = Math.random();
        boolean res = false;
        if (getNumberOfRipeFruit() == 0){
            res = false;
        }
        if (chance < dropFruitChance()) {
            res = true;
        }
        return res;
    }

    /**
     * Used to check if a Fruit would grow.
     */
    @Override
    protected boolean checkGrowFruit() {
        boolean res = super.checkGrowFruit();
        if (res){
            res = true;
            // TODO: Increment ecopoint
        }
        return res;
    }

    /**
     * And now the tree will grow older.
     * Informs the tree the passage of time so that it will run its method every turn.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        age++;
        if (age == 10)
            displayChar = 't';
        if (age == 20)
            displayChar = 'T';

        if (canDropFruit()) {
            decrementNumberOfRipeFruit();
            location.addItem(new PortableItem("Fruit", 'a')); // Placeholder
        }
    }
}
