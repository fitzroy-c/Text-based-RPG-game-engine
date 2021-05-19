package Bag;

import Player.Bag;
import Player.Item;
import navigation.Coordinate;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
// parameterized testing
public class BagTest{

    @Test
    public void testPut() {
        Bag testBag = new Bag(10);

        HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
        hashmap.put("weight",3);
        hashmap.put("damage",30);
        Item i1 = new Item("1","consumable", "testItem", "testItemInto", hashmap);

        assertEquals(true, testBag.put(i1));
        assertEquals(3 , testBag.getCurrentWeight());
    }

    @Test
    public void testPutMore() {
        Bag testBag = new Bag(10);

        HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
        hashmap.put("weight",5);
        hashmap.put("damage",30);
        Item i1 = new Item("1","consumable", "testItem", "testItemInto", hashmap);

        assertEquals(true, testBag.put(i1));
        assertEquals(true, testBag.put(i1));
        assertEquals(10 , testBag.getCurrentWeight());
    }

    @Test
    public void testOverWeight() {
        Bag testBag = new Bag(10);

        HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
        hashmap.put("weight",5);
        hashmap.put("damage",30);
        Item i1 = new Item("1","consumable", "testItem", "testItemInto", hashmap);

        assertEquals(true, testBag.put(i1));
        assertEquals(true, testBag.put(i1));
        assertEquals(false, testBag.put(i1));
        assertEquals(10 , testBag.getCurrentWeight());
    }

    @Test
    public void testDropItem() {
        Bag testBag = new Bag(10);

        HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
        hashmap.put("weight",5);
        hashmap.put("damage",30);
        Item i1 = new Item("1","consumable", "testItem", "testItemInto", hashmap);

        assertEquals(true, testBag.put(i1));
        assertEquals(i1, testBag.drop(i1));
        assertEquals(true, testBag.put(i1));
        assertEquals(5 , testBag.getCurrentWeight());
    }

    @Test
    public void getItemByName() {
        Bag testBag = new Bag(10);

        HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
        hashmap.put("weight",5);
        hashmap.put("damage",30);
        Item i1 = new Item("1","damage", "testItem", "testItemInto", hashmap);

        HashMap<String, Integer> hashmap2 = new HashMap<String, Integer>();
        hashmap2.put("weight",3);
        hashmap2.put("damage",10);
        Item i2 = new Item("2","damage", "testItem2", "testItemInto", hashmap2);

        assertEquals(true, testBag.put(i1));
        assertEquals(true, testBag.put(i2));
        assertEquals(8 , testBag.getCurrentWeight());

        assertEquals(i2, testBag.getItemByName("testItem2"));
    }

    @Test
    public void searchItemByName() {
        Bag testBag = new Bag(10);

        HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
        hashmap.put("weight",5);
        hashmap.put("damage",30);
        Item i1 = new Item("1","damage", "testItem", "testItemInto", hashmap);

        HashMap<String, Integer> hashmap2 = new HashMap<String, Integer>();
        hashmap2.put("weight",3);
        hashmap2.put("damage",10);
        Item i2 = new Item("2","damage", "testItem2", "testItemInto", hashmap2);

        assertEquals(true, testBag.put(i1));
        assertEquals(true, testBag.put(i2));
        assertEquals(8 , testBag.getCurrentWeight());

        assertEquals(true, testBag.searchInBagByName("testItem2"));
        assertFalse(testBag.searchInBagByName("testItem3"));
    }
}
