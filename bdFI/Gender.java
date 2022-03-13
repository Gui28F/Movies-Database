package bdFI;
/**
 * @author Guilherme Fernandes 60045 gc.fernandes@campus.fct.unl.pt
 * @author Martim Gamboa 61700 m.gamboa@campus.fct.unl.pt
 * <p>
 * enumerator with all available genders
 */
public enum Gender {
    MALE("MALE"), FEMALE("FEMALE"), NOT_PROVIDED("NOT-PROVIDED"),
    OTHER("OTHER");
    String description;

    Gender(String description) {
        this.description = description;
    }

    public String toString() {
        return description;
    }
}
