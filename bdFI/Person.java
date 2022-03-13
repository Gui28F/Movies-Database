package bdFI;

import bdFI.exceptions.PersonHasNoShowsException;
import dataStructure.Iterator;

import java.io.Serializable;

/**
 * @author Guilherme Fernandes 60045 gc.fernandes@campus.fct.unl.pt
 * @author Martim Gamboa 61700 m.gamboa@campus.fct.unl.pt
 *
 * Person interface
 */
public interface Person extends Serializable {

    /**
     * return person's id
     *
     * @return id
     */
    String idPerson();

    /**
     * return person's year of birth
     *
     * @return year of birth
     */
    int yearBirth();

    /**
     * return person's email
     *
     * @return email
     */
    String email();

    /**
     * return person's phone number
     *
     * @return phone number
     */
    String phoneNumber();

    /**
     * return person's gender
     *
     * @return gender
     */
    String gender();

    /**
     * return person's name
     *
     * @return name
     */
    String name();

    /**
     * returns all person shows
     * @throws PersonHasNoShowsException    if person has no shows
     *
     * @return show
     */
    Iterator<Show> shows() throws PersonHasNoShowsException;
}
