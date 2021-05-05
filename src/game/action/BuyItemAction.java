package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.VendingMachine;

public class BuyItemAction extends Action {

    Item itemToSell;
    int sellingPrice;

    public BuyItemAction(Item item, int price){
        itemToSell = item;
        sellingPrice = price;
    }


    // init item
    // init price
    // construct(item, price)

    @Override
    public String execute(Actor actor, GameMap map) {
        // add to inv (item)
        // minus EcoPoints (price)
        // returns message that player bought something
        actor.addItemToInventory(itemToSell);
        VendingMachine.decreaseEcoPoint(sellingPrice);
        return String.format("%s bought %s for %s EcoPoints", actor.toString(), itemToSell.toString(), sellingPrice);
    }

    @Override
    public String menuDescription(Actor actor) {
        // sysmenu player buys smth
        return String.format("%s buys %s for %s EcoPoints", actor.toString(), itemToSell.toString(), sellingPrice);
    }
}