package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaur.Dinosaur;
import game.dinosaur.DinosaurStatus;
import game.growable.Growable;

/**
 * Action that allows a Dinosaur to eat from a Growable
 * @author Lin Chen Xiang
 * @see Actor
 * @see Action
 * @see Growable
 * @see Dinosaur
 * @see DinosaurStatus
 * @see game.behaviours.HerbHungerBehaviour
 * @see GameMap
 * @since 03/05/2021
 */

public class EatFromGrowableAction extends Action {

    /**
     * the growable to be fed on
     */
    private Growable growable;

    /**
     * Constructor
     * @param growable the growable to be fed on
     */
    public EatFromGrowableAction(Growable growable){
        this.growable = growable;
    }

    /**
     * Heals an actor based on amount of fruits eaten, and decrement fruit count of growable for total times actor ate from it
     * @param actor The actor feeding from the growable.
     * @param map The map the actor is on.
     * @return description of actor eating from the growable.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        int healPoints=0;
        String result = actor + " feeds from " + growable;

        // if actor is a big eater, let it keep eating until it is full or growable runs out of fruits
        if (actor.hasCapability(DinosaurStatus.BIG_EATER_TREE)) {
            while (growable.getNumberOfRipeFruit()>0) {
                if (!(((Dinosaur) actor).isHungry())) {
                    break;
                }
                // doesn't really make a difference right now, but will be useful if other types of BIG EATERS get introduced
                if (actor.hasCapability(DinosaurStatus.BAD_DIGESTION)) {
                    int healing = 5;
                    healPoints += healing;
                    actor.heal(healing);
                }
                growable.decrementNumberOfRipeFruit();
            }
        }
        // actor has a short neck, eat one fruit
        else if (actor.hasCapability(DinosaurStatus.SHORT_NECK)) {
            healPoints = 10;
            actor.heal(healPoints);
            growable.decrementNumberOfRipeFruit();
        }

        return result + " and heals for " + healPoints + " hitpoints.";
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return description of actor eating from the growable.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " feeds from " + growable;
    }
}
