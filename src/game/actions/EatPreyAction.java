package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.behaviours.Behaviour;

/**
 * Special Action that attacks an Actor, and heals the attacker Actor
 *
 * @author Lin Chen Xiang
 * @see game.behaviours.PredatorBehaviour
// * @see carnihungerbehaviour
 * @see Actor
 * @see AttackAction
 * @see GameMap
 * @since 03/05/2021
 */

public class EatPreyAction extends AttackAction {
    /**
     * Constructor.
     *
     * @param actor the Actor attacking
     * @param target the Actor to attack
     */
    public EatPreyAction(Actor actor, Actor target) {
        super(target);
    }
}
