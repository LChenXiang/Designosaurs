package game.items;

import game.PortableItem;

public abstract class EdibleItem extends PortableItem {
    public EdibleItem(String name, char displayChar) {
        super(name, displayChar);

    }
    @Override
    public void addCapability(Enum<?> capability) {
        super.addCapability(ItemStats.CARNIVORE_CAN_EAT);
        super.addCapability(ItemStats.HERBIVORE_CAN_EAT);
    }


}
