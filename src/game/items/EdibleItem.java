package game.items;

import edu.monash.fit2099.engine.Actor;
import game.PortableItem;


/**
 * Represents a food item.
 *
 * @author NgYuKang, Amos Leong Zheng Khang
 * @version 1.0
 * @see PortableItem
 * @since 05/05/2021
 */
public abstract class EdibleItem extends PortableItem {

    /**
     * Constructor.
     *
     * @param name        The name of the item
     * @param displayChar What it should show on the map
     */
    public EdibleItem(String name, char displayChar) {
        super(name, displayChar);
        addCapability(ItemStats.IS_EDIBLE);
    }

    /**
     * Returns how much this item can heal the target depending on its stats.
     * Enter null if checking player feeding dinosaur.
     * Some item will return the same amount regardless of actor since it is a fixed int.
     *
     * @param actor Target
     * @return heal amount
     */
    public abstract int getHealAmount(Actor actor);

}
