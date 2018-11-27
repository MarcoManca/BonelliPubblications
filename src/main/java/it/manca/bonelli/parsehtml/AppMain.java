package it.manca.bonelli.parsehtml;

import it.manca.bonelli.model.Pubblication;
import it.manca.bonelli.service.PubblicationService;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author munky
 */
public class AppMain {

    public static void main(String[] args) {
        ApplicationContext context = 
    	  new ClassPathXmlApplicationContext("beans.xml");
        
        PubblicationService service = (PubblicationService) context.getBean("pubblicationService");
        List<Pubblication> dbPubblicationList = service.findAllPubblications();
        
        NextPubblications pubblications = new NextPubblications("http://www.sergiobonelli.it/sezioni/44/prossimamente");
        List<Pubblication> newPubblicationList = pubblications.getPubblicationList();
        
        //List<Pubblication> toSave = ComparePubblications.compare(newPubblicationList, dbPubblicationList);
        
//        for (Pubblication p : toSave) {
        for (Pubblication p : newPubblicationList) {    
            System.out.println(p.toString());
            System.out.println("\n");
            service.savePubblication(p);
        }
        
    }
}
