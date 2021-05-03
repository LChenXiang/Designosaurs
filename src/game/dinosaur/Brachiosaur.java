package game.dinosaur;

import java.util.Random;

/**
 * Represents a Herbivore Brachiosaur.
 *
 * @author NgYuKang
 * @see DinosaurStatus
 * @see Gender
 * @see HerbivoreDinosaur
 * @see Dinosaur
 * @since 25/04/2021
 */
public class Brachiosaur extends HerbivoreDinosaur {


    /**
     * Constructor to initialise a Brachiosaur with a specific gender.
     * All Brachiosaur are represented by a 'b' and have 160 hit points.
     * They should start with 100 hit points.
     *
     * @param name   the name of this Brachiosaur
     * @param gender the gender this Brachiosaur should have.
     */
    public Brachiosaur(String name, Enum<Gender> gender) {
        super(name, 'b', 160, gender);
        addCapability(DinosaurStatus.LONG_NECK);
        addCapability(DinosaurStatus.BAD_DIGESTION);
        addCapability(DinosaurStatus.BIG_EATER_TREE);
        addCapability(DinosaurStatus.STOMP_BUSH);
    }

    /**
     * Constructor to initialise a Brachiosaur with a specific gender and age.
     * All Brachiosaur are represented by a 'b' and have 160 hit points.
     * They should start with 100 hit points.
     *
     * @param name   the name of this Brachiosaur
     * @param gender the gender this Brachiosaur should have.
     */
    public Brachiosaur(String name, Enum<Gender> gender, int newAge) {
        super(name, 'b', 160, gender, newAge);
        addCapability(DinosaurStatus.LONG_NECK);
        addCapability(DinosaurStatus.BAD_DIGESTION);
        addCapability(DinosaurStatus.BIG_EATER_TREE);
        addCapability(DinosaurStatus.STOMP_BUSH);
    }

    /**
     * @return How long a Brachiosaur corpse should stay before rotting.
     */
    @Override
    public int getCorpseRotTime() {
        return 40;
    }

    /**
     * @return The HP(Hunger) where a Brachiosaur is considered hungry.
     */
    @Override
    public int getHungerThreshold() {
        return 140;
    }

    /**
     * @return Age that a Brachiosaur starts being an adult.
     */
    @Override
    public int getAdultAge() {
        return 50;
    }

    /**
     * @return How long it takes for an Brachiosaur egg to hatch.
     */
    @Override
    public int getIncubationPeriod() {
        return 20;
    }

    /**
     * @return How much starting HP should a baby Brachiosaur have.
     */
    @Override
    public int getBabyStartingHP() {
        return 10;
    }

    /**
     * @return How much starting HP should an adult Brachiosaur have.
     */
    @Override
    public int getStartingHP() {
        return 100;
    }

    /**
     * @return At what hunger the Brachiosaur is considered well fed.
     */
    @Override
    public int getWellFeedHunger() {
        return 70;
    }

    /**
     * @return How long until an unconscious Brachiosaur is considered dead.
     */
    @Override
    public int getUnConsciousThreshold() {
        return 15;
    }

    /**
     * @return Length of a Brachiosaur pregnancy.
     */
    @Override
    public int getPregnancyLength() {
        return 30;
    }

    /**
     * @return A new instance of this dinosaur
     */
    @Override
    public Dinosaur getNewDinosaur() {
        Random random = new Random();
        boolean res = random.nextBoolean();
        Enum<Gender> gender;
        if (!res) {
            gender = Gender.MALE;
        } else {
            gender = Gender.FEMALE;
        }
        return new Brachiosaur("Brachiosaur", gender, 0);
    }
}
