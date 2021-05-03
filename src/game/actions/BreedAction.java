package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.behaviours.BreedBehaviour;
import game.dinosaur.DinosaurStatus;
import game.dinosaur.Gender;

/**
 * Special Action breeds with an Actor
 *
 * @author Lin Chen Xiang
 * @see Actor
 * @see Action
 * @see game.behaviours.BreedBehaviour
 * @see DinosaurStatus
 * @see Gender
 * @see GameMap
 * @since 03/05/2021
 */

public class  BreedAction extends Action {

    /**
     * Empty Constructor
     */
    public BreedAction() {}


    /**
     * executes breeding process, checks if actor is female, if so, make it pregnant, otherwise do nothing
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return null if actor is MALE, or a description that indicates actor is now pregnant
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        if (actor.hasCapability(Gender.FEMALE)) {
            actor.addCapability(DinosaurStatus.PREGNANT);
            return actor + "is pregnant";
        }

        return null;
    }

    /**
     * indicates that actor is mating on log
     * @param actor The actor performing the action.
     * @return description that actor is mating
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + "mates";
    }
}
