package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.EatPreyAction;
import game.dinosaur.Allosaur;
import game.dinosaur.DinosaurStatus;
import game.dinosaur.Stegosaur;

/**
 * A class that decides whether the Actor can attack a nearby prey
 *
 * @author Lin Chen Xiang
 * @see Behaviour
 * @see Actor
 * @see EatPreyAction
 * @see Allosaur
 * @see game.dinosaur.Stegosaur
 * @see GameMap
 * @see Location
 * @since 03/05/2021
 */

public class PredatorBehaviour implements Behaviour {

    /**
     * Empty Constructor
     */
    public PredatorBehaviour() {
    }

    /**
     * Checks all exits of this actor to see if there are prey that can be attacked
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return null if no prey to attack, new EatPreyAction if there are prey that can be attacked
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        // not Allosaur, shouldn't have this behaviour
        if (!(actor instanceof Allosaur))
            return null;

        Location here = map.locationOf(actor);

        // find surrounding prey
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();

            // if this exit contains an Actor that can be attacked by Allosaur
            if (destination.containsAnActor() && destination.getActor().hasCapability(DinosaurStatus.ALLOSAUR_CAN_ATTACK)) {
//                try {int hasAttacked = ((Allosaur)actor).getTimeElapsedSinceAttack((Stegosaur) destination.getActor());}
                Integer hasAttacked = ((Allosaur)actor).getTimeElapsedSinceAttack((Stegosaur) destination.getActor());
//                 if hasAttacked is null means not attacked before
                if (hasAttacked==null)
                    return new EatPreyAction(destination.getActor());

            }

        }

        // no prey found
        return null;

    }
}
