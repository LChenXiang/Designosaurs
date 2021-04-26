package game.growable;

/**
 * Represents a bush.
 *
 * @author NgYuKang
 * @see Growable
 * @since 25/04/2021
 */
public class Bush extends Growable {

    /**
     * Constructor.
     * Bush will have a display character of w.
     */
    public Bush() {
        super('w');
        addCapability(GrowableStatus.SHORT);
    }

    /**
     *
     * @return chance that a fruit will grow every turn in a bush.
     */
    @Override
    public double growFruitChance() {
        return 0.1;
    }
}
