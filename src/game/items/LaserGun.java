package game.items;

import edu.monash.fit2099.engine.WeaponItem;

public class LaserGun extends WeaponItem {
    /**
     * Constructor for Laser Gun
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     */
    public LaserGun(String name, char displayChar, int damage, String verb) {
        super("Laser Gun", 'F', 160, "zaps");
    }
    @Override
    public void addCapability(Enum<?> capability) {
        super.addCapability(ItemStats.IS_WEAPON);
    }
}
