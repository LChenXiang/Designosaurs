package game.items;

import edu.monash.fit2099.engine.Location;

public class Corpse extends EdibleItem{
    private int corpseRotTime;

    public Corpse(String name, char displayChar ) {
        super("Corpse", 'X');
    }
    @Override
    public void addCapability(Enum<?> capability) {
        super.addCapability(ItemStats.CARNIVORE_CAN_EAT);
    }
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
    }


}
