package it.manca.bonelli.utils;

import it.manca.bonelli.model.Pubblication;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author munky
 */
public class ComparePubblications {
    public static List<Pubblication> compare(List<Pubblication> newPub, List<Pubblication> dbPub) {
        List<Pubblication> newPubblication = new LinkedList<>();
        
        newLoop: for(Pubblication newP : newPub) {
            dbLoop: for(Pubblication dbP : dbPub) {
                if(dbP.equals(newP))
                    continue newLoop;
            }
            newPubblication.add(newP);
        }
 
        return newPubblication;
    }
}
