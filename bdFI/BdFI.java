package bdFI;

import bdFI.exceptions.*;

import dataStructure.Iterator;

import java.io.Serializable;

/**
 * @author Guilherme Fernandes 60045 gc.fernandes@campus.fct.unl.pt
 * @author Martim Gamboa 61700 m.gamboa@campus.fct.unl.pt
 * <p>
 * BdFI interface
 */
public interface BdFI extends Serializable {

    /**
     * adds a person with id <code>idPerson</code> called <code>name</code>,
     * who was born in <code>year</code>, gender <code>gender</code> ,
     * telephone number <code>telephone</code>
     *
     * @param year      person's year of birth
     * @param email     person's email
     * @param telephone person's telephone number
     * @param gender    person's gender input language
     * @param gend      person's gender in system language
     * @param name      person's name
     * @throws InvalidYearException           if the year is invalid
     * @throws PersonIdAlreadyExistsException if the person's Id already exists
     */
    void addPerson(String idPerson, int year, String email, String telephone, String gender, Gender gend, String name)
            throws InvalidYearException, PersonIdAlreadyExistsException;

    /**
     * adds a show with id <code>idShow</code>, title <code>title</code> produced in <code>year</code>
     *
     * @param idShow show's id
     * @param year   show's production year
     * @param title  show's title
     * @throws ShowIdAlreadyExistsException if show's Id already exists
     * @throws InvalidYearException         if the year is invalid
     */
    void addShow(String idShow, int year, String title) throws ShowIdAlreadyExistsException, InvalidYearException;

    /**
     * adds a person participation whose id is <code>idPerson</code>
     * to the show with id <code>idShow</code>,
     * with description <code>description</code>
     *
     * @param idPerson    person's id
     * @param idShow      show's id
     * @param description person's description
     * @throws PersonDoesNotExistsException if person does not exist
     * @throws ShowDoesNotExistsException   if show does not exist
     */
    void addParticipant(String idPerson, String idShow, String description) throws PersonDoesNotExistsException,
            ShowDoesNotExistsException;

    /**
     * premiere the show with id <code>idShow</code>
     *
     * @param idShow show's id
     * @throws ShowAlreadyHasCompletedProductionException if show has already completed production
     * @throws ShowDoesNotExistsException                 if show does not exist
     */
    void premiere(String idShow) throws ShowDoesNotExistsException, ShowAlreadyHasCompletedProductionException;

    /**
     * remove show with id <code>idShow</code>
     *
     * @param idShow show's id
     * @throws ShowDoesNotExistsException                 if show does not exist
     * @throws ShowAlreadyHasCompletedProductionException if show has already completed production
     */
    void removeShow(String idShow) throws ShowDoesNotExistsException, ShowAlreadyHasCompletedProductionException;

    /**
     * add tag <code>tag</code> to show with id <code>idShow</code>
     *
     * @param idShow show's id
     * @param tag    show's tag
     * @throws ShowDoesNotExistsException if show does not exist
     */
    void tag(String idShow, String tag) throws ShowDoesNotExistsException;


    /**
     * return all shows registered
     *
     * @return all entries with shows registered
     */
    Iterator<Show> shows();


    /**
     * returns the show with id <code>idShow</code>
     *
     * @param idShow show's id
     * @return show
     * @throws ShowDoesNotExistsException if show does not exist
     */
    Show show(String idShow) throws ShowDoesNotExistsException;

    /**
     * returns the person with id <code>idPerson</code>
     *
     * @param idPerson person's id
     * @return person
     * @throws PersonDoesNotExistsException if person does not exist
     */
    Person person(String idPerson) throws PersonDoesNotExistsException;

    /**
     * returns an iterator with all show <code>idShow</code> participation's
     *
     * @param idShow show's id
     * @return iterator with all show participation's
     * @throws ShowDoesNotExistsException if show does not exist
     * @throws NoParticipationsException if show does not have participations
     */
    Iterator<Participation> showParticipations(String idShow) throws ShowDoesNotExistsException, NoParticipationsException;

    /**
     * rate show with id <code>idShow</code> with <code>stars</code> stars
     *
     * @param idShow show's id
     * @param starts number of stars to rate show
     * @throws ShowDoesNotExistsException       if show does not exist
     * @throws ShowISInProducingStatusException if show is in producing status
     * @throws InvalidRatingException           if rating is invalid
     */
    void rateShow(String idShow, int starts) throws ShowDoesNotExistsException, ShowISInProducingStatusException,
            InvalidRatingException;

    /**
     * return all participation's of person with id <code>idPerson</code>
     *
     * @param idPerson person's id
     * @return person participation's
     * @throws PersonDoesNotExistsException if person does not exist
     */
    Iterator<Show> personShows(String idPerson) throws PersonDoesNotExistsException, PersonHasNoShowsException;

    /**
     * return the shows with best rating
     *
     * @return a show iterator that contains the shows with best rating
     * @throws NoShowsException               if there are no shows
     * @throws NoFinishedProductionsException if there are no finished productions
     * @throws NoRatedProductionsException    if there are no rated productions
     */
    Iterator<Show> bestShows() throws NoShowsException, NoFinishedProductionsException, NoRatedProductionsException;

    /**
     * return shows with rating <code>rating</code>
     *
     * @param rating show's rating
     * @return a show iterator that contains the shows with <code>rating</code> average
     * @throws InvalidRatingException         if rating is invalid
     * @throws NoShowsException               if there are no shows
     * @throws NoRatedProductionsException    if there are no rated productions
     * @throws NoFinishedProductionsException if there are no finished productions
     */
    Iterator<Show> listShows(int rating) throws InvalidRatingException, NoShowsException, NoRatedProductionsException,
            NoFinishedProductionsException, NoProductionsWithRatingException;

    /**
     * returns all shows with <code>tag</code> tags
     *
     * @param tag show's tag
     * @return a show iterator that contains the shows with <code>tag</code>
     * @throws NoShowsException             if there are no shows
     * @throws NoTaggedProductionsException if there are no tagged shows
     * @throws NoShowsWithTagException      if there are no shows with tag
     */
    Iterator<Show> taggedShows(String tag) throws NoShowsException, NoTaggedProductionsException,
            NoShowsWithTagException;
}
