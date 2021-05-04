package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class BuyItem extends Action {

    // init item
    // init price

    // construct(item, price)

    @Override
    public String execute(Actor actor, GameMap map) {
        // add to inv (item)
        // minus ecopoints (price)
        // sysmsg player bought smth
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        // sysmenu player buys smth
        return null;
    }
}
