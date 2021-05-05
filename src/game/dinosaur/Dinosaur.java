package game.dinosaur;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actions.DieFromHungerAction;
import game.actions.LayEggAction;
import game.behaviours.Behaviour;
import game.behaviours.BreedBehaviour;
import game.behaviours.WanderBehaviour;

import java.util.ArrayList;

/**
 * Represents a dinosaur. This class is abstract and should still be extended into Herbivore and Carnivore dinosaurs.
 * Contains all the codes to check hunger, pregnancy progression, baby growth.
 * Also contains code to know what the player can do to ALL dinosaurs (dinosaur specific will be in their own classes)
 * Override abstract methods here to give the dinosaur its stats.
 * Add needed behaviour in constructor in extended classes as needed to give it other behaviours.
 *
 * @author NgYuKang
 * @version 1.0
 * @see DinosaurStatus
 * @see Gender
 * @since 25/04/2021
 */
public abstract class Dinosaur extends Actor {

    /**
     * Used to keep track of the dinosaur's age. Used in transitioning a baby to an adult
     */
    private int age;

    /**
     * Used to keep track of pregnancy.
     */
    private int pregnantAge = 0;

    /**
     * Used to keep track how long a dinosaur has been unconscious.
     */
    private int unConsciousElapsed = 0;

    /**
     * List of behaviours the dinosaur can do.
     * Will be looped through from start to end, representing behaviours with more priority at the front
     */
    protected final ArrayList<Behaviour> behaviourList = new ArrayList<>();

    /**
     * Constructor to initialise a dinosaur to the adult age. Requires gender input
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param gender      Gender of the dinosaur.
     */
    public Dinosaur(String name, char displayChar, int hitPoints, Enum<Gender> gender) {
        // hitPoints here is used to initialise the character's max hp.
        // We will initialise the starting hp via another method.
        super(name, displayChar, hitPoints);
        addCapability(gender);
        // Insert all behaviour
        behaviourList.add(0, new WanderBehaviour());
        behaviourList.add(0, new BreedBehaviour());
        this.hitPoints = getStartingHP();
    }

    /**
     * Constructor to initialise a dinosaur to a specific age. Baby enum will be added if below adult age.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param gender      gender of the dinosaur
     * @param newAge      age of the dinosaur
     */
    public Dinosaur(String name, char displayChar, int hitPoints, Enum<Gender> gender, int newAge) {
        // hitPoints here is used to initialise the character's max hp.
        // We will initialise the starting hp via another method.
        super(name, displayChar, hitPoints);
        addCapability(gender);
        age = newAge;
        // Age HP check
        if (newAge > 0) {
            this.hitPoints = getStartingHP();
        } else {
            this.hitPoints = getBabyStartingHP();
            addCapability(DinosaurStatus.BABY);
        }
        // Insert all behaviour
        behaviourList.add(0, new WanderBehaviour());
    }

    /**
     * Adds actions that other Actor (only player at this point) can do.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions the other actor can do.
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        return new Actions(new AttackAction(this));
    }

    /**
     * @return How long it takes for this dinosaur's corpse to rot.
     */
    public abstract int getCorpseRotTime();

    /**
     * @return the HP(hunger) that the dinosaur is considered hungry.
     */
    public abstract int getHungerThreshold();

    /**
     * @return the age that the dinosaur is considered an adult
     */
    public abstract int getAdultAge();

    /**
     * @return The duration taken for the egg to hatch
     */
    public abstract int getIncubationPeriod();

    /**
     * @return how much the adult dinosaur's starting hp should be.
     */
    public abstract int getStartingHP();

    /**
     * @return how much hp a baby dinosaur of this type should have.
     */
    public abstract int getBabyStartingHP();

    /**
     * @return how long can this dinosaur be unconscious before it is considered dead.
     */
    public abstract int getUnConsciousThreshold();

    /**
     * @return how long does the pregnancy of this dinosaur last.
     */
    public abstract int getPregnancyLength();

    /**
     * @return The integer where this dinosaur is considered well fed.
     */
    public abstract int getWellFeedHunger();

    /**
     * @return Whether the dinosaur is currently considered well fed
     */
    public boolean isWellFed() {
        return (hitPoints > getWellFeedHunger());
    }

    /**
     * @return Whether the dinosaur is hungry
     */
    public boolean isHungry() {
        return (hitPoints < getHungerThreshold());
    }

    /**
     * Override this for each unique dinosaur so that
     * we can generate a new dinosaur from a dinosaur object
     * Will be useful when a dinosaur lays an egg, we create
     * a new dinosaur and put the dinosaur in there.
     * An egg will still be ticked every turn but since the dinosaur is
     * not referenced by the world yet, its turn will not be processed.
     * The dinosaur will then only be placed onto the map, into the game
     * when the egg successfully hatches. If the egg doesn't hatch and gets
     * destroyed (not referenced anymore), so does the dinosaur.
     *
     * @return A new instance of this dinosaur
     */
    public abstract Dinosaur getNewDinosaur();

    /**
     *
     * @return How much HP does the corpse of this dinosaur heals
     */
    public abstract int getCorpseHealAmount();


    /**
     * Allow the dinosaur to have its turn.
     * Will decrement its HP (hunger) and increment the age every turn.
     * Will check if it's hungry and print suitable message.
     * Will check if it has fainted from hunger, if yes, check if it has passed the game rule of 20 turns.
     * If so, kill it with DieFromHungerAction (Could have been just implemented here to be honest)
     * But letting death be handled by an action could be better.
     * Will also run pregnancy related code. Lays egg if it is past/equal to incubation period.
     * Will also check if the dinosaur is a baby, if yes, check if it has become an adult and adjust accordingly
     * Finally, we will find something for the dinosaur to do via behaviour.
     * This is done by looping through an ArrayList of behaviours, where the position
     * indicates the priority of the behaviour. Being at the front means highest priority.
     * If there's no good action to find from the behaviours, just do nothing
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action to take during this turn, defaults to DoNothingAction if can't find any.
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // Turn-related attribute change
        hitPoints--;
        age++;

        // Check hunger
        // Makes sure to print it only once when it becomes hungry
        // When it is no longer hungry, it is indicated via enum so the hunger message is printed again
        if (isHungry()) {
            if (!(hasCapability(DinosaurStatus.HUNGRY))) {
                Location here = map.locationOf(this);
                display.println(String.format("%s at (%s,%s) is getting hungry!", name, here.x(), here.y()));
                addCapability(DinosaurStatus.HUNGRY);
            }
        } else {
            if (hasCapability(DinosaurStatus.HUNGRY)) {
                removeCapability(DinosaurStatus.HUNGRY);
            }
        }

        // Check if starving to death
        if (!(isConscious())) {
            unConsciousElapsed++;
            if (unConsciousElapsed >= getUnConsciousThreshold()) {
                return new DieFromHungerAction(); // Placeholder
            } else {
                return new DoNothingAction();
            }
        } else {
            // Keep resetting it to 0
            unConsciousElapsed = 0;
        }

        // Pregnancy code
        if (hasCapability(DinosaurStatus.PREGNANT)) {
            pregnantAge++;
            if (pregnantAge >= getPregnancyLength()) {
                pregnantAge = 0;
                return new LayEggAction(); // Placeholder
            }
        }

        // Remove baby status if adult age
        if (hasCapability(DinosaurStatus.BABY) && (age >= getAdultAge())) {
            removeCapability(DinosaurStatus.BABY);
        }

        // Handle multi-turn actions
        if (lastAction.getNextAction() != null) {
            return lastAction.getNextAction();
        }

        // If passed everything, find something to do
        for (Behaviour behaviour : behaviourList) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }

        // Stuck? Too bad! Just do nothing. Probably starve to death though.
        return new DoNothingAction();
    }
}
