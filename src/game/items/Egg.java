package game.items;

import edu.monash.fit2099.engine.Location;
import game.PortableItem;

import game.dinosaur.Stegosaur;

public class Egg extends PortableItem {
    public Egg(String name, char displayChar) {
        super("Egg", 'O');

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