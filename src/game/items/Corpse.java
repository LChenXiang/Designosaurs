package game.items;

import edu.monash.fit2099.engine.Location;

public class Corpse extends EdibleItem{
    private int corpseRotAge;
    private int corpseRotTime;
    private int corpseHealAmount;

    public Corpse(String name, int corpseRotTime, int corpseHealAmount) {
        super(name, 'X');
    }
    @Override
    public void addCapability(Enum<?> capability) {
        super.addCapability(ItemStats.CARNIVORE_CAN_EAT);
    }
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        corpseRotAge++;
        if (corpseRotAge > corpseRotTime) {
            currentLocation.removeItem(this);

        }
    }
    private void setCorpseRotTime(int newCorpseRotTime){
        this.corpseRotTime = newCorpseRotTime;
    }
    private void setCorpseHealAmount(int newCorpseHealAmount){
        this.corpseHealAmount = newCorpseHealAmount;
    }

}
