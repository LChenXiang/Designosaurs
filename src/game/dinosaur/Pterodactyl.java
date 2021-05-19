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

    /**
     * Constructor for an adult Pterodactyl with a set gender
     * Pterodactyls is shown as 'P' on map and has max HP and thirst of 100
     * Spawns with 50 HP and 60 Thirst
     * @param gender
     */
    public Pterodactyl(Enum<Gender> gender) {
        super("Pterodactyl", 'P', 100, gender, 100);
    }

    /**
     * Constructor for a baby Pterodactyl
     * Shown as 'P' on map and has max HP and thirst of 100
     * Spawns with 20 HP and 60 Thirst
     */
    public Pterodactyl() {
        super("Pterodactyl", 'P', 100, 100);
    }

    /**
     * @return Time taken for Pterodactyl Corpse to rot
     */
    @Override
    public int getCorpseRotTime() {
        return 20;
    }

    /**
     * @return HP considered for Pterodactyl to be hungry
     */
    @Override
    public int getHungerThreshold() {
        return 70;
    }

    /**
     * @return Number of turns needed to turn baby Pterodactyl into adult
     */
    @Override
    public int getAdultAge() {
        return 25;
    }

    /**
     * @return Number of turns needed to hatch a Pterodactyl Egg
     */
    @Override
    public int getIncubationPeriod() {
        return 20;
    }

    /**
     * @return Starting HP of adult Pterodactyl
     */
    @Override
    public int getStartingHP() {
        return 50;
    }

    /**
     * @return Starting HP of baby Pterodactyl
     */
    @Override
    public int getBabyStartingHP() {
        return 20;
    }

    /**
     * @return Number of turns allowed for Pterodactyl to be unconscious for due to hunger
     */
    @Override
    public int getHungerUnConsciousThreshold() {
        return 15;
    }

    /**
     * @return Number of turns needed for Pregnant Pterodactyl to carry baby before laying egg
     */
    @Override
    public int getPregnancyLength() {
        return 10;
    }

    /**
     * @return HP considered whether Pterodactyl is well fed or not
     */
    @Override
    public int getWellFeedHunger() {
        return 50;
    }

    /**
     * @return creates a new baby Pterodactyl
     */
    @Override
    public Dinosaur getNewDinosaur() {
        return new Pterodactyl();
    }

    /**
     * @return How much a Pterodactyl Corpse heals when eaten
     */
    @Override
    public int getCorpseHealAmount() {
        return 30;
    }

    /**
     * @return Price of a Pterodactyl Egg in Vending Machine
     */
    @Override
    public int getEggPurchasePrice() {
        return 200;
    }

    /**
     * @return Amount of Eco Points earned when Pterodactyl Egg hatches
     */
    @Override
    public int getEggHatchEcoPoint() {
        return 100;
    }

    /**
     * @return Starting Thirst of any Pterodactyl
     */
    @Override
    public int getStartingThirst() {
        return 60;
    }

    /**
     * @return How much a Pterodactyl can drink in one turn
     */
    @Override
    public int getMaxDrinkAmount() {
        return 30;
    }

    /**
     * @return Maximum tiles allowed for Pterodactyl to fly across
     */
    @Override
    public int getMaxFlyingTile() {
        return 30;
    }
}
