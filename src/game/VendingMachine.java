package game;

import edu.monash.fit2099.engine.*;
import game.actions.BuyItemAction;
import game.dinosaur.Allosaur;
import game.dinosaur.Brachiosaur;
import game.dinosaur.Stegosaur;
import game.items.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VendingMachine extends Ground {
    private static int EcoPoints = 20000;
    private ArrayList<Item> sellableItem;


    public VendingMachine() {
        super('V');
        sellableItem = new ArrayList<>();
        sellableItem.add(new Fruit());
        sellableItem.add(new LaserGun());
        sellableItem.add(new Egg(new Stegosaur()));
        sellableItem.add(new Egg(new Allosaur()));
        sellableItem.add(new Egg(new Brachiosaur()));
        sellableItem.add(new HerbivoreMealKit());
        sellableItem.add(new CarnivoreMealKit());
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * Override this to implement terrain that blocks thrown objects but not movement, or vice versa
     *
     * @return true
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    private Action returnBuyItemAction(Item item, int price) {
        Item freshItem;
        Action action = null;
        if (item instanceof Egg){
            freshItem = ((Egg) item).getNewCopy();
            action = new BuyItemAction(freshItem, price);
        } else {
            try {
                Class<?> cls = item.getClass();
                Constructor<?> constructor;
                constructor = cls.getConstructor();
                freshItem = (Item) constructor.newInstance();
                action = new BuyItemAction(freshItem, price);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return action;
    }

    /**
     * Returns an empty Action list.
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);
        for (Item item : sellableItem) {
            Purchasable itemPurchase = (Purchasable) (item);
            actions.add(returnBuyItemAction(item, itemPurchase.getPrice()));
        }

        return actions;
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
