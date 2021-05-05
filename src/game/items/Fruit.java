package game.items;

import edu.monash.fit2099.engine.Location;

public class Fruit extends EdibleItem {
    private int rotTime;

    public Fruit(String name, char displayChar) {
        super("Fruit", 'a');

    }

    @Override
    public void addCapability(Enum<?> capability) {
        super.addCapability(ItemStats.HERBIVORE_CAN_EAT);
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        Fruit fruit = new Fruit("Fruit", 'a');
        if(rotTime > 15){
            currentLocation.removeItem(fruit);

        }
    }
}
