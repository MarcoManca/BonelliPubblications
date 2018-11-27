package it.manca.bonelli.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.Type;

/**
 *
 * @author munky
 */
@Entity
@Table(name="PUBBLICATION")
public class Pubblication implements Serializable {
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date pubblicationDate;
    @Id
    private int number;
    @Column(nullable = false)
    private String periodicity;
    @Id
    private String pubblicationName;
    @Column(nullable = false)
    private String pubblicationTitle;
    @Column(nullable = false)
    private String coverURL;
    @Column(nullable = false)
    private String schedaURL;
    @Column(nullable = false)
    private boolean buyed;

    public Pubblication() {
        this.buyed = false;
    }
    
    public Pubblication(Date pubblicationDate, int number, 
            String periodicity, String pubblicationName, 
            String pubblicationTitle, String coverURL, String schedaURL) {
        this.pubblicationDate = pubblicationDate;
        this.number = number;
        this.periodicity = periodicity;
        this.pubblicationName = pubblicationName;
        this.pubblicationTitle = pubblicationTitle;
        this.coverURL = coverURL;
        this.schedaURL = schedaURL;
        this.buyed = false;
    }

    @Override
    public String toString() {
        return "Name:".concat(pubblicationName).concat(" num: ")
                .concat(Integer.toString(number)).concat(" title: ")
                .concat(pubblicationTitle).concat(" date: ")
                .concat(pubblicationDate.toString());
    }
    
    public Date getPubblicationDate() {
        return pubblicationDate;
    }

    public void setPubblicationDate(Date pubblicationDate) {
        this.pubblicationDate = pubblicationDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public String getPubblicationName() {
        return pubblicationName;
    }

    public void setPubblicationName(String pubblicationName) {
        this.pubblicationName = pubblicationName;
    }

    public String getPubblicationTitle() {
        return pubblicationTitle;
    }

    public void setPubblicationTitle(String pubblicationTitle) {
        this.pubblicationTitle = pubblicationTitle;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public String getSchedaURL() {
        return schedaURL;
    }

    public void setSchedaURL(String schedaURL) {
        this.schedaURL = schedaURL;
    }

    public boolean isBuyed() {
        return buyed;
    }

    public void setBuyed(boolean buyed) {
        this.buyed = buyed;
    }
    
    @Override
    public boolean equals(Object  o) {
        if(!o.getClass().equals(Pubblication.class))
            return false;
        Pubblication p = (Pubblication)o;
        return this.getPubblicationName().equals(p.getPubblicationName()) &&
                this.getNumber() == p.getNumber();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.number;
        hash = 89 * hash + Objects.hashCode(this.pubblicationName);
        return hash;
    }
}
