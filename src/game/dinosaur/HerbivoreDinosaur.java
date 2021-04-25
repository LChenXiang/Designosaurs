package game.dinosaur;

import edu.monash.fit2099.engine.*;
import game.Player;


/**
 * Represents a Herbivore dinosaur.
 *
 * @author NgYuKang
 * @version 1.0
 * @see DinosaurStatus
 * @see Gender
 * @see Dinosaur
 * @since 25/04/2021
 */
public abstract class HerbivoreDinosaur extends Dinosaur {

    /**
     * Constructor for a Herbivore Dinosaur.
     *
     * @param name        Name of the dinosaur.
     * @param displayChar Character used to represent the dinosaur on the map.
     * @param hitPoints   Max HP of the dinosaur.
     * @param gender      Gender of the dinosaur.
     */
    public HerbivoreDinosaur(String name, char displayChar, int hitPoints, Enum<Gender> gender) {
        super(name, displayChar, hitPoints, gender);
        // Add other behaviour here
    }

    /**
     * Constructor for a Herbivore Dinosaur.
     *
     * @param name        Name of the dinosaur.
     * @param displayChar Character used to represent the dinosaur on the map.
     * @param hitPoints   Max HP of the dinosaur.
     * @param gender      Gender of the dinosaur.
     * @param newAge      Age of the dinosaur to be initialised with
     */
    public HerbivoreDinosaur(String name, char displayChar, int hitPoints, Enum<Gender> gender, int newAge) {
        super(name, displayChar, hitPoints, gender, newAge);
        // Add other behaviour here
    }


    /**
     * Generates action that other actors can do to this herbivore dinosaur
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return actions that the other actor can do to a carnivore dinosaur.
     */
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = super.getAllowableActions(otherActor, direction, map);
        if (otherActor instanceof Player) {
            for (Item item : otherActor.getInventory()) {
                if (item instanceof Item) { // Placeholder
                    actions.add(new DoNothingAction()); // Placeholder, replace with feed.
                }
            }
        }
        return actions;
    }

}
