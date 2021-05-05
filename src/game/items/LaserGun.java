package game.items;

import edu.monash.fit2099.engine.WeaponItem;

public class LaserGun extends WeaponItem {
    /**
     *
     */
    public LaserGun() {
        super("Laser Gun", 'F', 160, "zaps");
    }
    @Override
    public void addCapability(Enum<?> capability) {
        super.addCapability(ItemStats.IS_WEAPON);
    }
}
