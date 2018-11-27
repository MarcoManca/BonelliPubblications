package it.manca.bonelli.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import it.manca.bonelli.dao.PubblicationDao;
import it.manca.bonelli.model.Pubblication;
 
@Service("pubblicationService")
@Transactional
public class PubblicationServiceImpl implements PubblicationService{
 
    @Autowired
    private PubblicationDao dao;
     
    @Override
    public void savePubblication(Pubblication pub) {
        dao.savePubblication(pub);
    }
 
    @Override
    public List<Pubblication> findAllPubblications() {
        return dao.findAllPubblications();
    }

    @Override
    public List<Pubblication> findAllPubblicationsToBuy() {
        return dao.findAllPubblicationsToBuy();
    }

    @Override
    public void deletePubblicationByNameAndNumber(String name, int number) {
        dao.deletePubblicationByNameAndNumber(name, number);
    }

    @Override
    public Pubblication findPubblicationByNameAndNumber(String name, int number) {
        return dao.findPubblicationByNameAndNumber(name, number);
    }

    @Override
    public void updatePubblication(Pubblication pub) {
        dao.updatePubblication(pub);
    }
 
   
}