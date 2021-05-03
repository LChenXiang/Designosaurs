package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

public class VendingMachine extends Ground {
    private int EcoPoints;

    public VendingMachine(){
        super('V');
    }
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
