package game.action;

import edu.monash.fit2099.engine.*;
import game.dinosaur.Allosaur;
import game.dinosaur.Stegosaur;

/**
 * Special Action that attacks an Actor, and heals the attacker Actor
 *
 * @author Lin Chen Xiang
 * @see game.behaviours.PredatorBehaviour
// * @see carnihungerbehaviour
 * @see Actor
 * @see Allosaur
 * @see AttackAction
 * @see GameMap
 * @since 03/05/2021
 */

public class EatPreyAction extends AttackAction {

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public EatPreyAction(Actor target) {
        super(target);
    }

    /**
     * Attacks an actor and heals by its damage dealt
     * @param actor the Allosaur attacking
     * @param map the current world map
     * @return description of what happened during execute (missed, killed target etc.)
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        String[] output;
        String result;

        // check if actor and target are correct types
        if (!(target instanceof Stegosaur)) {
            return null;
        }

        // Allosaur tries attacking target
        result = super.execute(actor, map);
        output = result.split(" ");

        // missed, nothing else happens
        if (output[1].equals("misses")) {
            return result;
        }

        Weapon weapon = actor.getWeapon();
        int healPoints = weapon.damage();

        // Allosaur heals by same amount of damage dealt to target
        actor.heal(healPoints);
        result += System.lineSeparator() + actor + " heals for " + healPoints + " HitPoints.";

        // target Stegosaur survives, add to hashMap to ensure Allosaur can't attack it for 20 turns
        if (target.isConscious()) {
            ((Allosaur)actor).insertStegosaurAttacked((Stegosaur) target);
        }

        return result;
    }
}
