package game.dinosaur;

import edu.monash.fit2099.engine.*;
import game.behaviours.PredatorBehaviour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Represents a Carnivore Allosaur.
 *
 * @author NgYuKang
 * @version 1.0
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
     * Constructor to initialise an adult Allosaur with a specific gender.
     * All Allosaurs are represented by a 'A' and have max 100 hit points.
     * They should start with 50 hit points.
     *
     * @param gender the gender this Allosaur should have.
     */
    public Allosaur(Enum<Gender> gender) {
        super("Allosaur", 'A', 100, gender);
        attackedStegosaur = new HashMap<>();
        behaviourList.add(new PredatorBehaviour());
    }

    /**
     * Constructor to initialise a baby Allosaur with a specific gender and age
     * All Allosaurs are represented by a 'A' and have max 100 hit points.
     * They should start with 10 hit points.
     */
    public Allosaur() {
        super("Allosaur", 'A', 100);
        attackedStegosaur = new HashMap<>();
        behaviourList.add(new PredatorBehaviour());
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
     * @return Whether this Allosaur can attack the target (Is it in the list of attacked stegosaur?)
     */
    public boolean canAttack(Stegosaur target) {
        boolean ret;
        if (attackedStegosaur.get(target) == null) {
            ret = true;
        } else {
            ret = false;
        }
        return ret;
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
        return new Allosaur();
    }

    /**
     * @return How much HP does the corpse of this dinosaur heals
     */
    @Override
    public int getCorpseHealAmount() {
        return 50;
    }

    @Override
    public int getEggPurchasePrice() {
        return 1000;
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
        // Temp Array: Can't remove while in this loop
        ArrayList<Stegosaur> dinosaurToRemove = new ArrayList<>();
        for (Map.Entry<Stegosaur, Integer> stegosaurIntegerEntry : attackedStegosaur.entrySet()) {
            Stegosaur stegosaur = stegosaurIntegerEntry.getKey();
            int timeElapsed = stegosaurIntegerEntry.getValue();
            timeElapsed++;
            // Remove if is 20 turns or more already
            // else update value
            if (timeElapsed >= 20) {
                dinosaurToRemove.add(stegosaur);
            } else {
                attackedStegosaur.put(stegosaur, timeElapsed);
                System.out.println("Updated " + timeElapsed);
            }
        }

        // Remove operation
        for (Stegosaur stegosaur : dinosaurToRemove) {
            attackedStegosaur.remove(stegosaur);
            System.out.println("Removed " + stegosaur);
        }

        return super.playTurn(actions, lastAction, map, display);
    }
}
