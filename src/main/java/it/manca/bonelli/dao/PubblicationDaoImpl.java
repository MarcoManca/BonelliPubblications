package it.manca.bonelli.dao;
 
import java.util.List;
 
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
 
import it.manca.bonelli.model.Pubblication;
import java.util.Calendar;
import java.util.Date;
 
@Repository("pubblicationDao")
public class PubblicationDaoImpl extends AbstractDao implements PubblicationDao{
 
    @Override
    public void savePubblication(Pubblication pub) {
        persist(pub);
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<Pubblication> findAllPubblications() {
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        Criteria criteria = getSession().createCriteria(Pubblication.class);
        criteria.add(Restrictions.ge("pubblicationDate", today));
        return (List<Pubblication>) criteria.list();
    }
 
    @Override
    public void deletePubblicationByNameAndNumber(String name, int number) {
        Query query = getSession().createSQLQuery("delete from Pubblication where pubblicationName = :name "
                + "and number = :number");
        query.setString("pubblicationName", name);
        query.setInteger("number", number);
        query.executeUpdate();
    }
  
    @Override
    public void updatePubblication(Pubblication pub){
        getSession().update(pub);
    }

    @Override
    public List<Pubblication> findAllPubblicationsToBuy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pubblication findPubblicationByNameAndNumber(String name, int number) {
        Criteria criteria = getSession().createCriteria(Pubblication.class);
        criteria.add(Restrictions.eq("pubblicationName",name));
        criteria.add(Restrictions.eq("number",number));
        return (Pubblication) criteria.uniqueResult();
    }
}