package it.manca.bonelli.service;

import java.util.List;

import it.manca.bonelli.model.Pubblication;

public interface PubblicationService {

    void savePubblication(Pubblication pub);

    List<Pubblication> findAllPubblications();

    List<Pubblication> findAllPubblicationsToBuy();

    void deletePubblicationByNameAndNumber(String name, int number);

    Pubblication findPubblicationByNameAndNumber(String name, int number);

    void updatePubblication(Pubblication pub);
}
