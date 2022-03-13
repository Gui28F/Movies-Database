package bdFI;

import java.io.Serializable;

/**
 * @author Guilherme Fernandes 60045 gc.fernandes@campus.fct.unl.pt
 * @author Martim Gamboa 61700 m.gamboa@campus.fct.unl.pt
 *
 * Participation interface
 */
public interface Participation extends Serializable {

    /**
     * returns person associated with participation
     *
     * @return person
     */
    Person person();

    /**
     * returns show associated with participation
     *
     * @return show
     */
    Show program();

    /**
     * returns participation description
     *
     * @return description
     */
    String description();
}
