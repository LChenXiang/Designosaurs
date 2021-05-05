package game.dinosaur;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.actions.FeedAction;
import game.behaviours.BreedBehaviour;
import game.behaviours.CarniHungerBehaviour;
import game.items.ItemStats;

/**
 * Represents a Carnivore dinosaur.
 *
 * @author NgYuKang
 * @version 1.0
 * @see DinosaurStatus
 * @see Gender
 * @see Dinosaur
 * @since 25/04/2021
 */
public abstract class CarnivoreDinosaur extends Dinosaur {

    /**
     * Constructor for a Carnivore Dinosaur.
     *
     * @param name        Name of the dinosaur.
     * @param displayChar Character used to represent the dinosaur on the map.
     * @param hitPoints   Max HP of the dinosaur.
     * @param gender      Gender of the dinosaur.
     */
    public CarnivoreDinosaur(String name, char displayChar, int hitPoints, Enum<Gender> gender) {
        super(name, displayChar, hitPoints, gender);
        addCapability(DinosaurStatus.TEAM_CARNIVORE);
        //TODO: Add needed behaviours here
        behaviourList.add(1, new CarniHungerBehaviour());
    }

    /**
     * Constructor for a Carnivore Dinosaur, initialises it to a specific age.
     *
     * @param name        Name of the dinosaur.
     * @param displayChar Character used to represent the dinosaur on the map.
     * @param hitPoints   Max HP of the dinosaur.
     * @param gender      Gender of the dinosaur.
     * @param newAge      Age of the dinosaur to be initialised with
     */
    public CarnivoreDinosaur(String name, char displayChar, int hitPoints, Enum<Gender> gender, int newAge) {
        super(name, displayChar, hitPoints, gender, newAge);
        addCapability(DinosaurStatus.TEAM_CARNIVORE);
        //TODO: Add needed behaviours here
        behaviourList.add(1, new CarniHungerBehaviour());

    }

    /**
     * Generates action that other actors can do to this carnivore dinosaur
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return actions that the other actor can do to a carnivore dinosaur.
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = super.getAllowableActions(otherActor, direction, map);
        // TODO: Replace placeholder once other parts are done
        for (Item item : otherActor.getInventory()) {
            if (item.hasCapability(ItemStats.CARNIVORE_CAN_EAT)) { // Placeholder
                actions.add(new FeedAction(this, item)); // Placeholder, replace with feed.
            }
        }
        return actions;
    }
}
