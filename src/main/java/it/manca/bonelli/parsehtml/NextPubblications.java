package it.manca.bonelli.parsehtml;

import it.manca.bonelli.model.Pubblication;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author munky
 */
public class NextPubblications {
    private final String pubblicationDateKey = "uscita:";
    private final String pubblicationNumberKey = "N° :";
    private final String pubblicationPeriodicityKey = "Periodicità:";
    
    private String siteUrl;
    private Document doc;
    private List<String> allowedPubblications;
    
    public NextPubblications(String url) {
        this.siteUrl = url;
        try {
            this.doc = Jsoup.connect(this.siteUrl).get();
        } catch (IOException ex) {
            Logger.getLogger(NextPubblications.class.getName()).log(Level.SEVERE, null, ex);
        }
        initAllowedPubblications();
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }
    
    public List<Pubblication> getPubblicationList() {
        List<Pubblication> pubblicationList = new LinkedList<>();
        Date pubblicationDate = null;
        int pubblicationNumber = 0;
        String pubblicationPeriodicity = null;
        String pubblicationName = null;
        String pubblicationTitle;
        String coverURL;
        String schedaURL;
        
        Elements schede = doc.select("div.scheda");
        for(Element scheda : schede) {
            Set<String> classNames = scheda.classNames();
            for(String className : classNames) {
                if(!className.equals("box") && !className.startsWith("pos") &&
                        !className.equals("scheda") ) {
                    pubblicationName = className;
                    break;
                }
            }
            if(!allowedPubblications.contains(pubblicationName))
                continue;
            
            Elements attributes = scheda.select("article > p");
            for(Element attribute : attributes) {
                Elements spans = attribute.select("span");
                for(int i = 0; i < spans.size(); i++) {
                    Element leaf = spans.get(i);
                    switch (leaf.text()) {
                        case pubblicationDateKey:
                            String[] dateSplit = spans.get(++i).text().split("/");
                            Calendar cal = Calendar.getInstance();
                            cal.set(Calendar.YEAR, Integer.parseInt(dateSplit[2]));
                            cal.set(Calendar.MONTH, Integer.parseInt(dateSplit[1])-1);
                            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateSplit[0]));
                            pubblicationDate = cal.getTime();
                            break;
                        case pubblicationNumberKey:
                            pubblicationNumber = Integer.valueOf(spans.get(++i).text());
                            break;
                        case pubblicationPeriodicityKey:
                            pubblicationPeriodicity = spans.get(++i).text();
                            break;
                    }                   
                }                
            }
            Element attributesScheda = scheda.select("article > div.cont_foto > a").first();
            schedaURL = attributesScheda.attr("href");
            Element attributesFoto = scheda.select("article > div.cont_foto > a > img").first();
            pubblicationTitle = attributesFoto.attr("alt");
            if(pubblicationTitle.contains("-")) {
                pubblicationTitle = pubblicationTitle.substring(0, 
                        pubblicationTitle.indexOf("-")-1);
            }
            coverURL = attributesFoto.attr("src");
            
            Pubblication p = new Pubblication(pubblicationDate, pubblicationNumber,
                    pubblicationPeriodicity, pubblicationName, pubblicationTitle, coverURL, schedaURL);
            pubblicationList.add(p);
        }
        return pubblicationList;
    }

    private void initAllowedPubblications() {
        this.allowedPubblications = new LinkedList<>();
        this.allowedPubblications.add("lukas");
        this.allowedPubblications.add("julia");
        this.allowedPubblications.add("dylandog");
        this.allowedPubblications.add("romanzieminiserie");
        this.allowedPubblications.add("morganlost");
        this.allowedPubblications.add("lestorie");
        this.allowedPubblications.add("adamwild");
        this.allowedPubblications.add("orfani");
    }
}
