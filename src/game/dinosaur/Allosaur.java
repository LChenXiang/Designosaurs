package game.dinosaur;

import edu.monash.fit2099.engine.*;
import game.behaviours.PredatorBehaviour;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Represents a Carnivore Allosaur.
 *
 * @author NgYuKang
 * @see DinosaurStatus
 * @see Gender
 * @see CarnivoreDinosaur
 * @see Dinosaur
 * @since 25/04/2021
 */
public class Allosaur extends CarnivoreDinosaur {

    /**
     * Used to store all the Stegosaur that this Allosaur has attacked.
     */
    private final Map<Stegosaur, Integer> attackedStegosaur;

    /**
     * Constructor to initialise an Allosaur with a specific gender.
     * All Allosaurs are represented by a 'A' and have 100 hit points.
     * They should start with 50 hit points.
     *
     * @param name   the name of this Allosaur
     * @param gender the gender this Allosaur should have.
     */
    public Allosaur(String name, Enum<Gender> gender) {
        super(name, 'A', 100, gender);
        attackedStegosaur = new HashMap<>();
    }

    /**
     * Constructor to initialise an Allsaur with a specific gender and age
     * All Allosaurs are represented by a 'A' and have 100 hit points.
     * They should start with 50 hit points.
     *
     * @param name   the name of this Allosaur
     * @param gender the gender this Allosaur should have.
     * @param newAge the age this Allosaur should have.
     */
    public Allosaur(String name, Enum<Gender> gender, int newAge) {
        super(name, 'A', 100, gender, newAge);
        attackedStegosaur = new HashMap<>();
    }

    /**
     * @return Corpse rot time of the Allosaur.
     */
    @Override
    public int getCorpseRotTime() {
        return 20;
    }

    /**
     * @return The HP (hunger) to which the Allosaur is considered hungry.
     */
    @Override
    public int getHungerThreshold() {
        return 50;
    }

    /**
     * @return The age that the Allosaur is considered an adult.
     */
    @Override
    public int getAdultAge() {
        return 50;
    }

    /**
     * @return How long it takes for an Allosaur egg to hatch.
     */
    @Override
    public int getIncubationPeriod() {
        return 50;
    }

    /**
     * @return the HP the ALlosaur will start with.
     */
    @Override
    public int getStartingHP() {
        return 50;
    }

    /**
     * @return Starting HP of a baby Allosaur.
     */
    @Override
    public int getBabyStartingHP() {
        return 20;
    }

    /**
     * @return HP (hunger) that the ALlosaur is considered well fed.
     */
    @Override
    public int getWellFeedHunger() {
        return 50;
    }

    /**
     * @return How long can the Allosaur be unconscious for.
     */
    @Override
    public int getUnConsciousThreshold() {
        return 20;
    }

    /**
     * @return Length of an Allosaur pregnancy
     */
    @Override
    public int getPregnancyLength() {
        return 20;
    }

    /**
     * @param target the Stegosaur to check
     * @return the time elapsed since the Stegosaur was attacked, null if not found
     */
    public int getTimeElapsedSinceAttack(Stegosaur target) {
        return attackedStegosaur.get(target);
    }

    /**
     * Adds the target Stegosaur to the hashmap
     *
     * @param target the Stegosaur that was attacked
     */
    public void insertStegosaurAttacked(Stegosaur target) {
        attackedStegosaur.put(target, 0);
    }

    /**
     * @return The damage an Allosaur would do.
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(20, "bites");
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
        return new Allosaur("Allosaur", gender, 0);
    }

    /**
     * Lets an Allosaur have its turn.
     * Will iterate through the entire hashmap to make update how long since
     * last attack on Stegosaur it has attacked.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action to take during this turn, defaults to DoNothingAction if can't find any.
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // Loop through all attacked stegosaur
        for (Map.Entry<Stegosaur, Integer> stegosaurIntegerEntry : attackedStegosaur.entrySet()) {
            Stegosaur stegosaur = stegosaurIntegerEntry.getKey();
            int timeElapsed = stegosaurIntegerEntry.getValue();
            timeElapsed++;
            // Remove if is 20 turns or more already
            // else update value
            if (timeElapsed >= 20) {
                attackedStegosaur.remove(stegosaur);
            } else {
                attackedStegosaur.put(stegosaur, timeElapsed);
            }
        }
        // Regardless of whatever is happening, check if we can attack
        // any Stegosaur nearby (Or anything that is attackable
        Action action = new PredatorBehaviour().getAction(this,map);
        if (action != null){
            return action;
        }
        return super.playTurn(actions, lastAction, map, display);
    }
}
