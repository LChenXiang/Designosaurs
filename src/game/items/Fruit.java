package game.items;

import edu.monash.fit2099.engine.Location;

public class Fruit extends EdibleItem {
    private int rotTime;

    public Fruit() {
        super("Fruit", 'a');

    }

    @Override
    public void addCapability(Enum<?> capability) {
        super.addCapability(ItemStats.HERBIVORE_CAN_EAT);
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        rotTime++;
        if(rotTime > 15){
            currentLocation.removeItem(this);

        }
    }
}
