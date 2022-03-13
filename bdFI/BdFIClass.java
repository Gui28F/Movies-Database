package bdFI;

import bdFI.exceptions.*;

import dataStructure.*;

import java.time.LocalDate;

/**
 * @author Guilherme Fernandes 60045 gc.fernandes@campus.fct.unl.pt
 * @author Martim Gamboa 61700 m.gamboa@campus.fct.unl.pt
 * BdFI class
 */
public class BdFIClass implements BdFI {
    private static final int DEFAULT_CAPACITY = 200;//Default capacity of data structures
    private Dictionary<String, Show> shows;//all shows
    private Dictionary<String, Person> persons; //all persons
    private Dictionary<String, Tag> tags;//all tags
    private Dictionary<Integer, Dictionary<String, Show>> showsByRating;//all showsByRating
    private int maxRate;
    private int finishedProductions;

    public BdFIClass() {
        this.shows = new SepChainHashTable<>(DEFAULT_CAPACITY);
        this.persons = new SepChainHashTable<>(DEFAULT_CAPACITY);
        this.tags = new SepChainHashTable<>(DEFAULT_CAPACITY);
        this.showsByRating = new SepChainHashTable<>(ShowClass.NUMBER_OF_RATES);
        this.maxRate = -1;
        this.finishedProductions = 0;
    }


    @Override
    public void addPerson(String idPerson, int year, String email, String telephone, String gender, Gender gend,
                          String name) throws InvalidYearException, PersonIdAlreadyExistsException {

        if (year < 0 || year > LocalDate.now().getYear())
            throw new InvalidYearException();
        if (personIdAlreadyExists(idPerson))
            throw new PersonIdAlreadyExistsException();
        PersonChange person = new PersonClass(idPerson, year, email, telephone, gender, gend, name);
        this.persons.insert(idPerson.toLowerCase(), person);
    }

    /**
     * verifies if already exists a person with id given
     *
     * @param personID person id
     * @return true if id already exists and false if it´s not
     */
    private boolean personIdAlreadyExists(String personID) {
        return this.persons.find(personID.toLowerCase()) != null;
    }

    @Override
    public void addShow(String idShow, int year, String title) throws ShowIdAlreadyExistsException, InvalidYearException {
        if (year < 0 || year > LocalDate.now().getYear())
            throw new InvalidYearException();
        if (showIdAlreadyExists(idShow))
            throw new ShowIdAlreadyExistsException();
        ShowChange show = new ShowClass(idShow, year, title);
        if (year < LocalDate.now().getYear()) {
            show.setPremiered();
            finishedProductions++;
        }
        this.shows.insert(idShow.toLowerCase(), show);
    }

    /**
     * verifies if already exists a show with id given
     *
     * @param showId show id
     * @return true if id already exists and false if it´s not
     */
    private boolean showIdAlreadyExists(String showId) {
        return this.shows.find(showId.toLowerCase()) != null;
    }

    @Override
    public void addParticipant(String idPerson, String idShow, String description)
            throws PersonDoesNotExistsException, ShowDoesNotExistsException {
        PersonChange person = getPerson(idPerson);
        if (person == null)
            throw new PersonDoesNotExistsException();
        ShowChange show = getShow(idShow);
        if (show == null)
            throw new ShowDoesNotExistsException();
        Participation participant = new ParticipationClass(person, show, description);
        show.addParticipant(participant);
        person.addParticipation(participant);
    }

    @Override
    public void premiere(String idShow) throws ShowDoesNotExistsException, ShowAlreadyHasCompletedProductionException {
        ShowChange show = getShow(idShow);
        if (show == null)
            throw new ShowDoesNotExistsException();
        show.premiere();
        this.finishedProductions++;
    }

    @Override
    public void removeShow(String idShow) throws ShowDoesNotExistsException, ShowAlreadyHasCompletedProductionException {
        Show show = this.shows.find(idShow.toLowerCase());
        if (show == null)
            throw new ShowDoesNotExistsException();
        if (!show.isInProduce())
            throw new ShowAlreadyHasCompletedProductionException();
        show = this.shows.remove(idShow.toLowerCase());
        Dictionary<String, Show> showsByRating = this.showsByRating.find(show.rating());
        if (showsByRating != null) {
            showsByRating.remove(show.title().toLowerCase());
            this.showsByRating.insert(show.rating(), showsByRating);
        }
        removeParticipations(show);
        removeShowFromTags(show);

    }

    /**
     * Pre-condition: show != null
     * will remove all participations from the show
     */
    private void removeParticipations(Show show) {
        try {
            Iterator<Participation> it = show.participations();
            while (it.hasNext()) {
                Participation participation = it.next();
                ((PersonChange) participation.person()).removeParticipation(participation);
            }
        } catch (NoParticipationsException e) {//if show has no participations don't do anything
        }
    }

    /**
     * remove all tags with no shows and remove the show from tag shows
     *
     * @param show show that have been removed from system
     */
    private void removeShowFromTags(Show show) {
        Iterator<Tag> it = show.tags();
        while (it.hasNext()) {
            Tag tag = it.next();
            tag = tags.find(tag.tag().toLowerCase());
            ((TagChange) tag).removeShow(show);
            if (!tag.hasShows())
                tags.remove(tag.tag().toLowerCase());
        }
    }

    @Override
    public void tag(String idShow, String tag) throws ShowDoesNotExistsException {
        ShowChange show = getShow(idShow);
        if (show == null)
            throw new ShowDoesNotExistsException();
        Tag tag1 = getTag(tag);
        if (tag1 == null) {
            tag1 = new TagClass(tag.toLowerCase());
            tags.insert(tag.toLowerCase(), tag1);
        }
        ((TagChange) tag1).addShow(show);
        show.addTag(new TagClass(tag));
    }

    /**
     * @param idPerson person id
     * @return the person with id given
     */
    private PersonChange getPerson(String idPerson) {
        return (PersonChange) persons.find(idPerson.toLowerCase());
    }

    /**
     * @param idShow show id
     * @return the show with id given
     */
    private ShowChange getShow(String idShow) {
        Show showChange = this.shows.find(idShow.toLowerCase());
        if (showChange == null)
            return null;
        return (ShowChange) showChange;
    }

    @Override
    public Iterator<Show> shows() {
        return new ValuesIterator<>(this.shows.iterator());
    }


    @Override
    public Show show(String idShow) throws ShowDoesNotExistsException {
        Show show = getShow(idShow);
        if (show == null)
            throw new ShowDoesNotExistsException();
        return show;
    }

    @Override
    public Person person(String idPerson) throws PersonDoesNotExistsException {
        Person person = getPerson(idPerson);
        if (person == null)
            throw new PersonDoesNotExistsException();
        return person;
    }

    @Override
    public Iterator<Participation> showParticipations(String idShow) throws ShowDoesNotExistsException, NoParticipationsException {
        Show show = getShow(idShow);
        if (show == null)
            throw new ShowDoesNotExistsException();
        return show.participations();
    }

    @Override
    public void rateShow(String idShow, int stars) throws ShowDoesNotExistsException,
            ShowISInProducingStatusException, InvalidRatingException {
        if (stars < ShowClass.MIN_RATE || stars > ShowClass.MAX_RATE)
            throw new InvalidRatingException();
        ShowChange show = getShow(idShow);
        if (show == null)
            throw new ShowDoesNotExistsException();
        int oldRate = show.rating();
        show.rate(stars);
        int newRate = show.rating();
        updateRatingInformation(show, oldRate, newRate);


    }

    /**
     * updates de max rate and the list of shows with <code>oldRate</code> rate
     * and the shows with <code>newRate</code> rate
     *
     * @param show    show that have been rated
     * @param oldRate old rate of the show
     * @param newRate new rate of the show
     */
    private void updateRatingInformation(Show show, int oldRate, int newRate) {
        Dictionary<String, Show> shows;
        if (newRate >= oldRate) {
            shows = showsByRating.find(oldRate);
            if (shows != null)
                shows.remove(show.title().toLowerCase());
            if (newRate >= maxRate)
                maxRate = newRate;
       }
        shows = showsByRating.find(newRate);
        if (shows == null)
            shows = new AVLTree<>();
        shows.insert(show.title().toLowerCase(), show);
        showsByRating.insert(newRate, shows);
    }

    @Override
    public Iterator<Show> personShows(String idPerson) throws PersonDoesNotExistsException, PersonHasNoShowsException {
        Person person = getPerson(idPerson);
        if (person == null)
            throw new PersonDoesNotExistsException();
        return person.shows();
    }

    /**
     * verify if system has rated productions
     *
     * @return true if  it has rated productions and false if not
     */
    private boolean hasRatedProductions() {
        return maxRate != -1;
    }

    @Override
    public Iterator<Show> bestShows() throws NoShowsException, NoFinishedProductionsException, NoRatedProductionsException {
        if (shows.isEmpty())
            throw new NoShowsException();
        if (!hasFinishedProductions())
            throw new NoFinishedProductionsException();
        if (!hasRatedProductions())
            throw new NoRatedProductionsException();
        return new ValuesIterator<>(showsByRating.find(maxRate).iterator());
    }

    /**
     * verify if system has finished productions
     *
     * @return true if it has finished productions and false if not
     */
    private boolean hasFinishedProductions() {
        return finishedProductions != 0;
    }

    @Override
    public Iterator<Show> listShows(int rating) throws InvalidRatingException, NoShowsException, NoRatedProductionsException,
            NoFinishedProductionsException, NoProductionsWithRatingException {
        if (rating < ShowClass.MIN_RATE || rating > ShowClass.MAX_RATE)
            throw new InvalidRatingException();
        if (shows.isEmpty())
            throw new NoShowsException();
        if (!hasFinishedProductions())
            throw new NoFinishedProductionsException();
        if (showsByRating.isEmpty())
            throw new NoRatedProductionsException();
        Dictionary<String, Show> byRating = showsByRating.find(rating);
        if (byRating == null || byRating.isEmpty())
            throw new NoProductionsWithRatingException();
        return new ValuesIterator<>(byRating.iterator());
    }

    /**
     * @param tag tag name
     * @return the tag with name given
     */
    private Tag getTag(String tag) {
        return tags.find(tag.toLowerCase());

    }

    @Override
    public Iterator<Show> taggedShows(String tag) throws NoShowsException, NoTaggedProductionsException,
            NoShowsWithTagException {
        if (shows.isEmpty())
            throw new NoShowsException();
        if (tags.isEmpty())
            throw new NoTaggedProductionsException();
        Tag tag1 = getTag(tag);
        if (tag1 == null)
            throw new NoShowsWithTagException();
        return tag1.shows();
    }


}
