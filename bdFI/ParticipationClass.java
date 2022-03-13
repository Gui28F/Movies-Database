package bdFI;


/**
 * @author Guilherme Fernandes 60045 gc.fernandes@campus.fct.unl.pt
 * @author Martim Gamboa 61700 m.gamboa@campus.fct.unl.pt
 * <p>
 * Participation class
 */
public class ParticipationClass implements Participation {
    private Person person; // person that participates
    private Show program;   //program of participation
    private String description; //participation description


    public ParticipationClass(Person person, Show program, String description) {
        this.person = person;
        this.program = program;
        this.description = description;
    }

    @Override
    public Person person() {
        return this.person;
    }

    @Override
    public Show program() {
        return this.program;
    }

    @Override
    public String description() {
        return this.description;
    }
}
