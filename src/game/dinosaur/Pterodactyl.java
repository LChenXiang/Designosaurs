package game.dinosaur;

/**
 * Represents a Carnivore Pterodactyl.
 * Pterodactyls only eat Corpse and fishes in Lakes.
 *
 * @author Lin Chen Xiang
 * @version 1.0
 * @see DinosaurStatus
 * @see Gender
 * @see CarnivoreDinosaur
 * @see Dinosaur
 * @since 10/05/2021
 */
// TODO: to Chen Xiang: Fix constructor, I added new stuff

// TODO: to Chen xiang: Implement the new methods
public class Pterodactyl extends CarnivoreDinosaur{

    public Pterodactyl(Enum<Gender> gender) {
        super("Pterodactyl", 'P', 100, gender);
    }

    public Pterodactyl() {
        super("Pterodactyl", 'P', 100);
    }

    @Override
    public int getCorpseRotTime() {
        return 0;
    }

    @Override
    public int getHungerThreshold() {
        return 0;
    }

    @Override
    public int getAdultAge() {
        return 0;
    }

    @Override
    public int getIncubationPeriod() {
        return 0;
    }

    @Override
    public int getStartingHP() {
        return 0;
    }

    @Override
    public int getBabyStartingHP() {
        return 0;
    }

    @Override
    public int getHungerUnConsciousThreshold() {
        return 0;
    }

    @Override
    public int getPregnancyLength() {
        return 0;
    }

    @Override
    public int getWellFeedHunger() {
        return 0;
    }

    @Override
    public Dinosaur getNewDinosaur() {
        return null;
    }

    @Override
    public int getCorpseHealAmount() {
        return 0;
    }

    @Override
    public int getEggPurchasePrice() {
        return 0;
    }

    @Override
    public int getEggHatchEcoPoint() {
        return 0;
    }
}
