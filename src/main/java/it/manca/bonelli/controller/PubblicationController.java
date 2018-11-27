package it.manca.bonelli.controller;

import it.manca.bonelli.model.Pubblication;
import it.manca.bonelli.parsehtml.NextPubblications;
import it.manca.bonelli.service.PubblicationService;
import it.manca.bonelli.utils.ComparePubblications;
import java.security.Principal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author munky
 */
@Controller
@RequestMapping("/rest")
public class PubblicationController {
    @Autowired
    PubblicationService service;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Pubblication> getPubblicationList() {
        List<Pubblication> dbPubblicationList = service.findAllPubblications();
        return dbPubblicationList;
    }
    
    @RequestMapping(value = "/reload", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Pubblication> refreshPubblicationList() {
        List<Pubblication> dbPubblicationList = service.findAllPubblications();
        NextPubblications pubblications = new NextPubblications("http://www.sergiobonelli.it/sezioni/44/prossimamente");
        List<Pubblication> newPubblicationList = pubblications.getPubblicationList();
        
        //List<Pubblication> toSave = ComparePubblications.compare(newPubblicationList, dbPubblicationList);
        
        //for (Pubblication p : toSave) {
        for (Pubblication p : newPubblicationList) {
            //System.out.println(p.toString());
            //System.out.println("\n");
            try {
                service.savePubblication(p);
            } catch(DataIntegrityViolationException ex) {
                
            }
        }
        return newPubblicationList;
    }
}
