package bdFI;

import bdFI.exceptions.NoParticipationsException;
import dataStructure.Iterator;

import java.io.Serializable;

/**
 * @author Guilherme Fernandes 60045 gc.fernandes@campus.fct.unl.pt
 * @author Martim Gamboa 61700 m.gamboa@campus.fct.unl.pt
 *
 * Show interface
 */
public interface Show extends Serializable {

    /**
     * return show's id
     *
     * @return id
     */
    String idShow();

    /**
     * return show's year of production
     *
     * @return year
     */
    int year();

    /**
     * return show's title
     *
     * @return title
     */
    String title();

    /**
     * returns show's average rating
     *
     * @return rating
     */
    int rating();


    /**
     * returns true if show is producing
     *
     * @return show status equals producing
     */
    boolean isInProduce();

    /**
     * returns iterator to tags
     *
     * @return tags iterator
     */
    Iterator<Tag> tags();

    /**
     * returns iterator to participations
     * @throws NoParticipationsException if show has no participations
     * @return participations iterator
     */
    Iterator<Participation> participations()throws NoParticipationsException;

}

