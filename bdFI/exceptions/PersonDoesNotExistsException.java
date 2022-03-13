package bdFI.exceptions;

/**
 * @author Guilherme Fernandes 60045 gc.fernandes@campus.fct.unl.pt
 * @author Martim Gamboa 61700 m.gamboa@campus.fct.unl.pt
 */
// throws exception if person does not exist
public class PersonDoesNotExistsException extends RuntimeException {
    static final long serialVersionUID = 0L;

    public PersonDoesNotExistsException() {
        super();
    }
}
