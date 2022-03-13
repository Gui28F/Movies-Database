package bdFI;

import bdFI.exceptions.PersonHasNoShowsException;
import dataStructure.*;

/**
 * @author Guilherme Fernandes 60045 gc.fernandes@campus.fct.unl.pt
 * @author Martim Gamboa 61700 m.gamboa@campus.fct.unl.pt
 * <p>
 * Person class
 */
public class PersonClass implements PersonChange {
    private String idPerson;//person id
    private int yearBirth;//year of birth
    private String email;//person email
    private String phoneNumber;//person phone number
    private String genderS;//person gender
    private String name;//person name
    private Gender gender;//person gender in system language
    protected Dictionary<String, Show> shows; //person shows

    public PersonClass(String idPerson, int yearBirth, String email, String phoneNumber, String gender, Gender gend
            , String name) {
        this.idPerson = idPerson;
        this.yearBirth = yearBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.genderS = gender;
        this.name = name;
        this.gender = gend;
        this.shows = new AVLTree<>();
    }

    @Override
    public String idPerson() {
        return this.idPerson;
    }

    @Override
    public int yearBirth() {
        return this.yearBirth;
    }

    @Override
    public String email() {
        return this.email;
    }

    @Override
    public String phoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public String gender() {
        return this.genderS;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public Iterator<Show> shows() throws PersonHasNoShowsException {
        if (shows.isEmpty())
            throw new PersonHasNoShowsException();
        return new ValuesIterator<>(shows.iterator());
    }

    @Override
    public void addParticipation(Participation participation) {
        this.shows.insert(participation.program().idShow(), participation.program());
    }

    @Override
    public void removeParticipation(Participation participation) {
        this.shows.remove(participation.program().idShow());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PersonClass other = (PersonClass) obj;
        return this.idPerson.equals(other.idPerson());
    }
}
