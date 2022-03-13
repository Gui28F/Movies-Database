import bdFI.*;
import bdFI.exceptions.*;
import dataStructure.Iterator;


import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * @author Guilherme Fernandes 60045 gc.fernandes@campus.fct.unl.pt
 * @author Martim Gamboa 61700 m.gamboa@campus.fct.unl.pt
 * Main class
 */
public class Main {

    private static final String DATA_FILE = "storeBdFI.dat"; //file to save bdFI object
    //constants that represent messages given by the program
    private static final String PERSON_ADDED = "Person added.";
    private static final String SHOW_ADDED = "Show added.";
    private static final String SHOW_REMOVED = "Show removed.";
    private static final String PARTICIPATION_ADDED = "Participation added.";
    private static final String SUCCESSFUL_PRODUCTION = "Successful production.";
    private static final String TAG_ADDED = "Tag added.";
    private static final String SHOW_INFO = "%s %s %d %d\n";
    private static final String RATING_APPLIED = "Rating applied.";
    private static final String PERSON_INFO = "%s %s %d %s %s %s\n";
    private static final String PERSON_HAS_NO_SHOWS = "idPerson has no shows.";
    private static final String SHOW_HAS_NO_PARTICIPATIONS = "idShow has no participations.";
    private static final String PARTICIPATION_INFO = "%s %s %d %s %s %s %s\n";
    private static final String QUIT = "Serializing and quitting...";
    //exception messages
    private static final String INVALID_YEAR = "Invalid year.";
    private static final String INVALID_GENDER = "Invalid gender information.";
    private static final String PERSON_ID_ALREADY_EXISTS = "idPerson exists.";
    private static final String SHOW_ID_ALREADY_EXISTS = "idShow exists.";
    private static final String PERSON_DOES_NOT_EXIST = "idPerson does not exist.";
    private static final String SHOW_DOES_NOT_EXIST = "idShow does not exist.";
    private static final String SHOW_ALREADY_HAS_COMPLETED_PRODUCTION = "idShow has already completed production.";
    private static final String IS_IN_PRODUCING = "idShow is in production.";
    private static final String INVALID_RATE = "Invalid Rating.";
    private static final String NO_FINISHED_PRODUCTIONS = "No finished productions.";
    private static final String NO_RATED_PRODUCTIONS = "No rated productions.";
    private static final String NO_SHOWS = "No shows.";
    private static final String NO_TAGGED_PRODUCTIONS = "No tagged productions.";
    private static final String NO_SHOWS_WITH_TAG = "No shows with tag.";
    private static final String NO_PRODUCTIONS_WITH_RATING = "No productions with rating.";
    //Commands
    private static final String ADD_PERSON = "ADDPERSON";
    private static final String ADD_SHOW = "ADDSHOW";
    private static final String ADD_PARTICIPATION = "ADDPARTICIPATION";
    private static final String PREMIERE = "PREMIERE";
    private static final String REMOVE_SHOW = "REMOVESHOW";
    private static final String TAG_SHOW = "TAGSHOW";
    private static final String INFO_SHOW = "INFOSHOW";
    private static final String RATE_SHOW = "RATESHOW";
    private static final String INFO_PERSON = "INFOPERSON";
    private static final String LIST_SHOWS_PERSON = "LISTSHOWSPERSON";
    private static final String LIST_PARTICIPATIONS = "LISTPARTICIPATIONS";
    private static final String LIST_BEST_SHOWS = "LISTBESTSHOWS";
    private static final String LIST_SHOWS = "LISTSHOWS";
    private static final String LIST_TAGGED_SHOWS = "LISTTAGGEDSHOWS";
    private static final String EXIT = "QUIT";
    //Valid genders
    private static final String MALE = "MALE";
    private static final String FEMALE = "FEMALE";
    private static final String NOT_PROVIDED = "NOT-PROVIDED";
    private static final String OTHER = "OTHER";

    /**
     * main method
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BdFI bdFI = load();
        String cmd = in.next().trim().toUpperCase();
        while (!cmd.equals(EXIT)) {
            executeCommand(in, bdFI, cmd);
            in.nextLine();
            cmd = in.next().trim().toUpperCase();
        }
        System.out.println(QUIT);
        save(bdFI);
        System.out.println();
    }

    /**
     * executes commands
     *
     * @param in   scanner
     * @param bdFI top object
     * @param cmd  command read
     */
    private static void executeCommand(Scanner in, BdFI bdFI, String cmd) {
        switch (cmd) {
            case ADD_PERSON:
                addPerson(in, bdFI);
                break;
            case ADD_SHOW:
                addShow(in, bdFI);
                break;
            case ADD_PARTICIPATION:
                addParticipant(in, bdFI);
                break;
            case PREMIERE:
                premiere(in, bdFI);
                break;
            case REMOVE_SHOW:
                removeShow(in, bdFI);
                break;
            case TAG_SHOW:
                tagShow(in, bdFI);
                break;
            case INFO_SHOW:
                infoShow(in, bdFI);
                break;
            case RATE_SHOW:
                rateShow(in, bdFI);
                break;
            case INFO_PERSON:
                infoPerson(in, bdFI);
                break;
            case LIST_SHOWS_PERSON:
                listShowsPersons(in, bdFI);
                break;
            case LIST_PARTICIPATIONS:
                listParticipations(in, bdFI);
                break;
            case LIST_BEST_SHOWS:
                listBestShows(bdFI);
                break;
            case LIST_SHOWS:
                listShows(in, bdFI);
                break;
            case LIST_TAGGED_SHOWS:
                listTaggedShows(in, bdFI);
                break;
        }
        System.out.println();
    }

    /**
     * verify if the gender given exists and return the respective gender enumerator
     *
     * @param gender gender
     * @return enumerator gender if gender is valid and null if it's not
     */
    private static Gender gender(String gender) {
        switch (gender.toUpperCase()) {
            case MALE:
                return Gender.MALE;
            case FEMALE:
                return Gender.FEMALE;
            case OTHER:
                return Gender.OTHER;
            case NOT_PROVIDED:
                return Gender.NOT_PROVIDED;
            default:
                return null;
        }
    }

    /**
     * adds person to database
     *
     * @param in   scanner
     * @param bdFI top object
     */
    private static void addPerson(Scanner in, BdFI bdFI) {
        String idPerson = in.next();    // person's id
        int year = in.nextInt();        // person's year of birth
        String email = in.next();        // person's email
        String telephone = in.next();    // person's telephone number
        String gender = in.next().trim();        // person's gender
        String name = in.nextLine().trim();    // person's name
        // if all parameters are valid adds person to database
        try {
            if (year < 0 || year > LocalDate.now().getYear())
                throw new InvalidYearException();
            Gender gend = gender(gender);
            if (gend == null)
                throw new InvalidGenderException();
            bdFI.addPerson(idPerson, year, email, telephone, gender, gend, name);
            System.out.println(PERSON_ADDED);
            // if invalid year, invalid gender or id already exists throws exception and person is not added
        } catch (InvalidYearException e) {
            System.out.println(INVALID_YEAR);
        } catch (InvalidGenderException e) {
            System.out.println(INVALID_GENDER);
        } catch (PersonIdAlreadyExistsException e) {
            System.out.println(PERSON_ID_ALREADY_EXISTS);
        }
    }


    /**
     * adds show to database
     *
     * @param in   scanner
     * @param bdFI top object
     */
    private static void addShow(Scanner in, BdFI bdFI) {
        String idShow = in.next();        // show's id
        int year = in.nextInt();        // show's production year
        String title = in.nextLine().trim();    // show's title

        // if all parameters are valid adds show to database
        try {
            if (year < 0 || year > LocalDate.now().getYear())
                throw new InvalidYearException();
            bdFI.addShow(idShow, year, title);
            System.out.println(SHOW_ADDED);

            // if show id already exists or invalid year throws exception and show is not added
        } catch (ShowIdAlreadyExistsException e) {
            System.out.println(SHOW_ID_ALREADY_EXISTS);
        } catch (InvalidYearException e) {
            System.out.println(INVALID_YEAR);
        }
    }

    /**
     * adds a participation to one show in database
     *
     * @param in   scanner
     * @param bdFI top object
     */
    private static void addParticipant(Scanner in, BdFI bdFI) {
        String idPerson = in.next();        // person's id
        String idShow = in.next();            // show's id
        String description = in.nextLine().trim();    // show's description

        // if all parameters are valid adds participant to database
        try {
            bdFI.addParticipant(idPerson, idShow, description);
            System.out.println(PARTICIPATION_ADDED);

            // if person does not exist or show does not exist throws exception and participant is not added
        } catch (PersonDoesNotExistsException e) {
            System.out.println(PERSON_DOES_NOT_EXIST);
        } catch (ShowDoesNotExistsException e) {
            System.out.println(SHOW_DOES_NOT_EXIST);
        }
    }

    /**
     * changes show status to premiered
     *
     * @param in   scanner
     * @param bdFI top object
     */
    private static void premiere(Scanner in, BdFI bdFI) {
        String idShow = in.next();    // show's id
        in.nextLine();

        // if all parameters are valid show is premiered
        try {
            bdFI.premiere(idShow);
            System.out.println(SUCCESSFUL_PRODUCTION);

            // if show does not exist or show is still producing throws exception and show is not premiered
        } catch (ShowDoesNotExistsException e) {
            System.out.println(SHOW_DOES_NOT_EXIST);
        } catch (ShowAlreadyHasCompletedProductionException e) {
            System.out.println(SHOW_ALREADY_HAS_COMPLETED_PRODUCTION);
        }
    }

    /**
     * removes show from database
     *
     * @param in   scanner
     * @param bdFI top object
     */
    private static void removeShow(Scanner in, BdFI bdFI) {
        String idShow = in.next(); // show's id
        in.nextLine();

        // if all parameters are valid show is removed
        try {
            bdFI.removeShow(idShow);
            System.out.println(SHOW_REMOVED);
            // if show does not exist or show has been premiered throws exception and show is not removed
        } catch (ShowDoesNotExistsException e) {
            System.out.println(SHOW_DOES_NOT_EXIST);
        } catch (ShowAlreadyHasCompletedProductionException e) {
            System.out.println(SHOW_ALREADY_HAS_COMPLETED_PRODUCTION);
        }
    }

    /**
     * adds tag to show
     *
     * @param in   scanner
     * @param bdFI top object
     */
    private static void tagShow(Scanner in, BdFI bdFI) {
        String idShow = in.next();    // show's id
        String tag = in.next();        // tag
        in.nextLine();

        // if all parameters are valid tag is added
        try {
            bdFI.tag(idShow, tag);
            System.out.println(TAG_ADDED);

            // if show does not exist throws exception and tag is not added
        } catch (ShowDoesNotExistsException e) {
            System.out.println(SHOW_DOES_NOT_EXIST);
        }
    }

    /**
     * displays show's info
     *
     * @param in   scanner
     * @param bdFI top object
     */
    private static void infoShow(Scanner in, BdFI bdFI) {
        String idShow = in.next();    // show's id
        in.nextLine();

        // if all parameters are valid displays show's info
        try {
            Show show = bdFI.show(idShow);
            System.out.printf(SHOW_INFO, show.idShow(), show.title(), show.year(), show.rating());
            Iterator<Tag> tags = show.tags();
            while (tags.hasNext()) {
                Tag tag = tags.next();
                System.out.println(tag.tag());
            }

            // if show does not exist throws exception and show's info is not displayed
        } catch (ShowDoesNotExistsException e) {
            System.out.println(SHOW_DOES_NOT_EXIST);
        }
    }

    /**
     * rates show
     *
     * @param in   scanner
     * @param bdFI top object
     */
    private static void rateShow(Scanner in, BdFI bdFI) {
        String idShow = in.next();    // show's id
        int stars = in.nextInt();    // rating
        in.nextLine();

        // if all parameters are valid show is rated
        try {
            bdFI.rateShow(idShow, stars);
            System.out.println(RATING_APPLIED);

            // if show does not exist, show is producing or invalid rating throws exception and show is not rated
        } catch (ShowDoesNotExistsException e) {
            System.out.println(SHOW_DOES_NOT_EXIST);
        } catch (ShowISInProducingStatusException e) {
            System.out.println(IS_IN_PRODUCING);
        } catch (InvalidRatingException e) {
            System.out.println(INVALID_RATE);
        }
    }

    /**
     * display's person's info
     *
     * @param in   scanner
     * @param bdFI top object
     */
    private static void infoPerson(Scanner in, BdFI bdFI) {
        String idPerson = in.next();    // person's id
        in.nextLine();

        // if all parameters are valid display's person's info
        try {
            Person person = bdFI.person(idPerson);
            System.out.printf(PERSON_INFO, person.idPerson(), person.name(), person.yearBirth(), person.email(),
                    person.phoneNumber(), person.gender());

            // if person does not exist throws exception and person's info is not displayed
        } catch (PersonDoesNotExistsException e) {
            System.out.println(PERSON_DOES_NOT_EXIST);
        }
    }

    /**
     * list shows associated with person
     *
     * @param in   scanner
     * @param bdFI top object
     */
    private static void listShowsPersons(Scanner in, BdFI bdFI) {
        String idPerson = in.next();
        in.nextLine();

        // if all parameters are valid lists shows
        try {
            Iterator<Show> it = bdFI.personShows(idPerson);
            while (it.hasNext()) {
                Show show = it.next();
                System.out.printf(SHOW_INFO, show.idShow(), show.title(), show.year(), show.rating());
            }
            // if person does not exist or person has no shows throws exception and no show is listed
        } catch (PersonDoesNotExistsException e) {
            System.out.println(PERSON_DOES_NOT_EXIST);
        } catch (PersonHasNoShowsException e) {
            System.out.println(PERSON_HAS_NO_SHOWS);
        }
    }

    /**
     * lists show's participants
     *
     * @param in   scanner
     * @param bdFI top object
     */
    private static void listParticipations(Scanner in, BdFI bdFI) {
        String idShow = in.next();    // show's id
        in.nextLine();

        // if all parameters are valid lists participants
        try {
            Iterator<Participation> participation = bdFI.showParticipations(idShow);
            //participants are listed
            while (participation.hasNext()) {
                Participation part = participation.next();
                Person person = part.person();
                System.out.printf(PARTICIPATION_INFO, person.idPerson(), person.name(), person.yearBirth(),
                        person.email(), person.phoneNumber(), person.gender(), part.description());
            }

            // if show does not exist throws exception and no participant is listed
        } catch (ShowDoesNotExistsException e) {
            System.out.println(SHOW_DOES_NOT_EXIST);
        } catch (NoParticipationsException e) { // if show has no participants no participant is listed
            System.out.println(SHOW_HAS_NO_PARTICIPATIONS);
        }

    }

    /**
     * list best shows
     *
     * @param bdFI top object
     */
    private static void listBestShows(BdFI bdFI) {

        // if all parameters are valid lists best shows
        try {
            Iterator<Show> it = bdFI.bestShows();
            while (it.hasNext()) {
                Show show = it.next();
                System.out.printf(SHOW_INFO, show.idShow(), show.title(), show.year(), show.rating());
            }

            // if there are no shows, no finished productions or no rated shows throws exception and no show is listed
        } catch (NoShowsException e) {
            System.out.println(NO_SHOWS);
        } catch (NoFinishedProductionsException e) {
            System.out.println(NO_FINISHED_PRODUCTIONS);
        } catch (NoRatedProductionsException e) {
            System.out.println(NO_RATED_PRODUCTIONS);
        }
    }

    /**
     * list shows with rating
     *
     * @param in   scanner
     * @param bdFI top object
     */
    private static void listShows(Scanner in, BdFI bdFI) {
        int rating = in.nextInt();    // rating
        in.nextLine();

        // if all parameters are valid lists shows with rating
        try {
            Iterator<Show> it = bdFI.listShows(rating);
            while (it.hasNext()) {
                Show show = it.next();
                System.out.printf(SHOW_INFO, show.idShow(), show.title(), show.year(), show.rating());
            }

            // if invalid rating, there are no shows, no rated shows or no finished productions throws exception and no show is listed
        } catch (InvalidRatingException e) {
            System.out.println(INVALID_RATE);
        } catch (NoShowsException e) {
            System.out.println(NO_SHOWS);
        } catch (NoRatedProductionsException e) {
            System.out.println(NO_RATED_PRODUCTIONS);
        } catch (NoFinishedProductionsException e) {
            System.out.println(NO_FINISHED_PRODUCTIONS);
        } catch (NoProductionsWithRatingException e) {
            System.out.println(NO_PRODUCTIONS_WITH_RATING);
        }

    }

    /**
     * lists tagged shows
     *
     * @param in   scanner
     * @param bdFI top object
     */
    private static void listTaggedShows(Scanner in, BdFI bdFI) {
        String tag = in.next();    // tag
        in.nextLine();

        // if all parameters are valid lists tagged shows
        try {
            Iterator<Show> it = bdFI.taggedShows(tag);
            while (it.hasNext()) {
                Show show = it.next();
                System.out.printf(SHOW_INFO, show.idShow(), show.title(), show.year(), show.rating());
            }
            // if there are no shows or no tagged shows throws exception and no show is listed
        } catch (NoShowsException e) {
            System.out.println(NO_SHOWS);
        } catch (NoTaggedProductionsException e) {
            System.out.println(NO_TAGGED_PRODUCTIONS);
        } catch (NoShowsWithTagException e) {
            System.out.println(NO_SHOWS_WITH_TAG);
        }
    }

    /**
     * save bdFI object in DATA_FILE
     *
     * @param bdFI top object
     */
    private static void save(BdFI bdFI) {
        try {
            ObjectOutputStream file = new ObjectOutputStream(
                    new FileOutputStream(DATA_FILE));
            file.writeObject(bdFI);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * load BdFI object from DATA_FILE and if DATA_FILE does not exist
     * returns a new object
     *
     * @return BdFI
     */
    @SuppressWarnings("unchecked")
    private static BdFI load() {
        BdFI loaded;
        try {
            ObjectInputStream file = new ObjectInputStream(
                    new FileInputStream(DATA_FILE));
            loaded = (BdFI) file.readObject();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            return new BdFIClass();
        }
        if (loaded == null)
            return new BdFIClass();
        return loaded;
    }
}
