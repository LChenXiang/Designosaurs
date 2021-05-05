package game;

import edu.monash.fit2099.engine.*;
import game.actions.BuyItemAction;
import game.dinosaur.Stegosaur;
import game.items.Corpse;
import game.items.Egg;
import game.items.Fruit;
import game.items.LaserGun;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class VendingMachine extends Ground {
    private static int EcoPoints = 500;
    private Map<Item, Integer> itemList;


    public VendingMachine() {
        super('V');
        itemList = new HashMap<>();
        itemList.put(new Fruit(), 30);
        itemList.put(new LaserGun(), 500);
    }
    private Action returnBuyItemAction(Item item, int price){
        Item freshItem;
        Action action = null;
        try {
            Class<?> cls = item.getClass();
            Constructor<?> constructor;
            constructor = cls.getConstructor();
            freshItem = (Item) constructor.newInstance();
            action = new BuyItemAction(freshItem, price);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return action;
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);
        for (Map.Entry<Item, Integer> itemSet : itemList.entrySet()) {
            Item item = itemSet.getKey();
            int price = itemSet.getValue();
            if (getEcoPoint() >= price){
                actions.add(returnBuyItemAction(item, price));
            }
        }

        return actions;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }
    public static void increaseEcoPoint(int num) {
        EcoPoints += num;
    }

    public static int getEcoPoint() {
        return EcoPoints;
    }

    public static void decreaseEcoPoint(int num) {
        EcoPoints -= num;
    }
}
