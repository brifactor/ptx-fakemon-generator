/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Type;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Singleton list of all available types
 * Also serves as a factory object to create Typings
 * @author Brittany
 */
public class TypeList {
    
    private static TypeList instance;
    private static ArrayList<String> all_types; //The actual list of types that we are generating from
    private final Random rand;
    private int single_type_weight; //chance of getting a single-type 'mon
    private int dual_type_weight; //chance of getting a dual-type 'mon
    
    //Private Constructor (necessary for singleton)
    private TypeList() {
        //TODO: read the list of types from an external config file or something
        //For now, here is a list of some placeholder types
        all_types = new ArrayList<>();
        all_types.add("Fire");
        all_types.add("Water");
        all_types.add("Grass");
        all_types.add("Normal");
        all_types.add("Bug");
        all_types.add("Flying");
        
        rand = new Random(); //For random number generation
        //Using Random() instead of ThreadLocalRandom for ease of testing later.
        
        single_type_weight = 1; //nonzero chance
        dual_type_weight = 1; //equal with single_type_weight (for now at least)
    }
    
    //If instance doesn't exist, create it. Either way, return it.
    public static TypeList getInstance() {
        if (instance == null) {
            instance = new TypeList();
        }
        return instance;
    }
    
    //Create a Typing object with random types
    public Typing genRandomTyping() {
        //Generate a primary type; equal chance of any type on the list
        int which_primary = rand.nextInt(all_types.size());
        
        //Does this typing have a secondary type?
        int single_or_dual = rand.nextInt(single_type_weight + dual_type_weight);
        if (single_or_dual < single_type_weight) { //single-type fakemon generated
            //Pass the generated primary type to the Typing constructor and return it
            return new Typing(all_types.get(which_primary));
        }
        else { //dual-type fakemon generated
            //Generate a second type; equal chance of any type on the list
            int which_secondary = rand.nextInt(all_types.size());
            //Pass the generated types to the Typing constructor and return it
            return new Typing(all_types.get(which_primary), all_types.get(which_secondary));
        }
    }//end function
    
}
