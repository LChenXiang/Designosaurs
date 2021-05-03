package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.BreedAction;
import game.dinosaur.Dinosaur;
import game.dinosaur.DinosaurStatus;
import game.dinosaur.Gender;

/**
 * Class that decides whether an actor is ready to breed and find a breed partner
 *
 * @author Lin Chen Xiang
 * @see Actor
 * @see Location
 * @see Dinosaur
 * @see DinosaurStatus
 * @see Gender
 * @see FollowBehaviour
 * @see game.actions.BreedAction
 * @see GameMap
 * @since 03/05/2021
 */

public class BreedBehaviour implements Behaviour{

    /**
     * Empty Constructor
     */
    public BreedBehaviour() {}

    /**
     * Goes through each radius to check for suitable breeding partner, then do action if found
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return Action to be done this turn (MoveActorAction, BreedAction)
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        // cast to Dinosaur
        Dinosaur dinosaur = (Dinosaur) actor;

        // Dinosaur is underage or already pregnant, can't breed
        if (dinosaur.hasCapability(DinosaurStatus.BABY) || dinosaur.hasCapability(DinosaurStatus.PREGNANT))
            return null;

        Location here = map.locationOf(dinosaur);
        Action action;

        // check if Dinosaur has enough hitpoints to breed
        if (dinosaur.isWellFed()) {

            // innermost radius -- if partner found immediately breed
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();

                // if exit has an actor and is the same type of Dinosaur
                if (destination.containsAnActor() && destination.getActor().getClass() == dinosaur.getClass()) {

                    // if they are opposite gender
                    if (dinosaur.hasCapability(Gender.MALE) && destination.getActor().hasCapability(Gender.FEMALE))
                        // if target is not underage nor pregnant
                        if (!(destination.getActor().hasCapability(DinosaurStatus.BABY)
                                || destination.getActor().hasCapability(DinosaurStatus.PREGNANT)))
                            return new BreedAction();

                            // swapped opposite gender
                        else if (dinosaur.hasCapability(Gender.FEMALE) && destination.getActor().hasCapability(Gender.MALE))
                            // if target is not underage nor pregnant
                            if (!(destination.getActor().hasCapability(DinosaurStatus.BABY)
                                    || destination.getActor().hasCapability(DinosaurStatus.PREGNANT)))
                                return new BreedAction();
                }
            }

            // innermost radius don't have breed partner, check locations of 2-tile radius
            action = findPartnerInRadius(here, 2, dinosaur, map);
            if (action!=null) // findPartnerInRadius returns moveactoraction
                return action;

            // 2nd layer also doesn't have breed partner, check locations of 3-tile radius
            action = findPartnerInRadius(here, 3, dinosaur, map);
            if (action!=null) // action is moveactoraction
                return action;
        }

        // no partner found
        return null;
    }

    /**
     * Find a suitable partner in set radius, return action if found
     *
     * @param here Location of actor looking for breeding partner
     * @param radius the radius of the actor to determine bound of actor surroundings
     * @param dinosaur the dinosaur searching for partner
     * @param map World map
     * @return null if no partner found in radius, follow if found partner (moveactoraction)
     */
    public Action findPartnerInRadius(Location here, int radius, Dinosaur dinosaur, GameMap map) {

        int counterX;
        int counterY = radius;
        Location there;
        Action follow;

        // diameter
        int length = radius*2;

        // go through each row if radius starting from here.y()+radius
        for (int i=0; i<=length; i++) {

            // start x at here.x()-radius
            counterX = radius*-1;

            // if at first or last row of radius, go through all tiles
            if (counterY == radius || counterY == (radius*-1))
                // go through all tiles of that row starting at (radius*-1, counterY)
                for (int j=0; j<=length; j++) {
                    // see if current tile is accessible
                    try {
                        there = map.at(here.x() + counterX, here.y() + counterY);
                    }
                    catch(ArrayIndexOutOfBoundsException e) {
                        continue;
                    }
                    follow = hasPartner(there, dinosaur, map); // check if has partner
                    if (follow!=null) // not null, return the action
                        return follow;

                    counterX++; // no partner at this tile, go to next
                }
                // in between first and last row of radius
            else
                // go through first and last column of this row
                for (int j=0; j<=length; j+=length) {
                    counterX += j; // 1st counterX = radius*-1, 2nd counterX = radius
                    // see if current tile is accessible
                    try {
                        there = map.at(here.x() + counterX, here.y() + counterY);
                    }
                    catch(ArrayIndexOutOfBoundsException e) {
                        continue;
                    }
                    follow = hasPartner(there, dinosaur, map); // check if has partner
                    if (follow!=null) // not null, return the action
                        return follow;
                }

            counterY--; // no partner at this row, decrement
        }

        return null; // no partner found in radius
    }

    /**
     * Check if this tile has a suitable partner
     *
     * @param there Location of this tile
     * @param dinosaur the Dinosaur finding a partner
     * @param map World map
     * @return null if no suitable partner, follow.getAction if found partner
     */
    public Action hasPartner(Location there, Dinosaur dinosaur, GameMap map) {

        Behaviour follow;

        // if tile has an Actor and is the same type of Dinosaur
        if (there.containsAnActor() && there.getActor().getClass() == dinosaur.getClass()) {

            // if they are opposite gender
            if (dinosaur.hasCapability(Gender.MALE) && there.getActor().hasCapability(Gender.FEMALE)) {
                // if target is not underage nor pregnant
                if (!(there.getActor().hasCapability(DinosaurStatus.BABY)
                        || there.getActor().hasCapability(DinosaurStatus.PREGNANT))) {
                    follow = new FollowBehaviour(there.getActor(), new BreedAction());
                    return follow.getAction(there.getActor(), map); // start following
                }
            }

            // swapped opposite gender
            else if (dinosaur.hasCapability(Gender.FEMALE) && there.getActor().hasCapability(Gender.MALE)) {
                // if target is not underage nor pregnant
                if (!(there.getActor().hasCapability(DinosaurStatus.BABY)
                        || there.getActor().hasCapability(DinosaurStatus.PREGNANT))) {
                    follow = new FollowBehaviour(there.getActor(), new BreedAction());
                    return follow.getAction(there.getActor(), map); // start following
                }
            }
        }
        return null; // no partner found
    }
}
