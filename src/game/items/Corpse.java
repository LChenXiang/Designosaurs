package game.items;

import edu.monash.fit2099.engine.Actor;

/**
 * Represents a corpse.
 * Our version of corpse will not rot in inventory. We treat inventory as a portable fridge.
 *
 * @author NgYuKang, Amos Leong Zheng Khang
 * @version 1.0
 * @see PerishableFoodItem
 * @since 05/05/2021
 */
public class Corpse extends PerishableFoodItem {

    /**
     * How much the corpse can heal. Taken from dinosaur, since it varies.
     */
    private final int healAmount;

    public Corpse(String name, int rotTime, int healAmount) {
        super(name, 'X', rotTime);
        addCapability(ItemStats.CARNIVORE_CAN_EAT);
        this.healAmount = healAmount;
    }

    /**
     * @param actor Target of who we are healing
     * @return How much can we heal based on the target
     */
    @Override
    public int getHealAmount(Actor actor) {
        return healAmount;
    }

}
