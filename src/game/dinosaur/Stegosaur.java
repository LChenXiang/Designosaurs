package game.dinosaur;


import java.util.Random;

/**
 * A herbivorous dinosaur.
 * Represents a Stegosaur.
 *
 * @author NgYuKang
 * @see DinosaurStatus
 * @see Gender
 * @see HerbivoreDinosaur
 * @see Dinosaur
 * @since 25/04/2021
 */
public class Stegosaur extends HerbivoreDinosaur {
    // Will need to change this to a collection if Stegosaur gets additional Behaviours.

    /**
     * Constructor to initialise a Stegosaur with a specific gender.
     * All Stegosaurs are represented by a 'd' and have 100 hit points.
     * They should start with 50 hit points.
     *
     * @param name   the name of this Stegosaur
     * @param gender the gender this dinosaur should have.
     */
    public Stegosaur(String name, Enum<Gender> gender) {
        super(name, 's', 100, gender);
        addCapability(DinosaurStatus.ALLOSAUR_CAN_ATTACK);
        addCapability(DinosaurStatus.SHORT_NECK);
    }

    /**
     * Constructor to initialise a Stegosaur with a specific gender and age.
     *
     * @param name   the name of this Stegosaur
     * @param gender the gender this dinosaur should have.
     * @param newAge the age the dinosaur should have
     */
    public Stegosaur(String name, Enum<Gender> gender, int newAge) {
        super(name, 'd', 100, gender, newAge);
        addCapability(DinosaurStatus.ALLOSAUR_CAN_ATTACK);
        addCapability(DinosaurStatus.SHORT_NECK);
    }

    /**
     * @return How long a Stegosaur corpse should stay before disappearing.
     */
    @Override
    public int getCorpseRotTime() {
        return 20;
    }

    /**
     * @return The HP(Hunger) of the Stegosaur that is considered it being hungry
     */
    @Override
    public int getHungerThreshold() {
        return 90;
    }

    /**
     * @return The age that a Stegosaur starts being an adult.
     */
    @Override
    public int getAdultAge() {
        return 30;
    }

    @Override
    public int getIncubationPeriod() {
        return 15;
    }

    /**
     * @return The HP an adult Stegosaur should start with.
     */
    @Override
    public int getStartingHP() {
        return 50;
    }

    /**
     * @return HP that a baby Stegosaur should start with.
     */
    @Override
    public int getBabyStartingHP() {
        return 10;
    }

    /**
     * @return The HP(Hunger) where a Stegosaur is considered well fed.
     */
    @Override
    public int getWellFeedHunger() {
        return 50;
    }

    /**
     * @return How long can a Stegosaur be unconscious for.
     */
    @Override
    public int getUnConsciousThreshold() {
        return 20;
    }

    /**
     * @return Length of a Stegosaur pregnancy.
     */
    @Override
    public int getPregnancyLength() {
        return 10;
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
        return new Stegosaur("Stegosaur", gender, 0);
    }

    //	/**
//	 * Figure out what to do next.
//	 *
//	 * FIXME: Stegosaur wanders around at random, or if no suitable MoveActions are available, it
//	 * just stands there.  That's boring.
//	 *
//	 * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
//	 */
//	@Override
//	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
//		super.playTurn(actions, lastAction, map, display);
//	}

}
