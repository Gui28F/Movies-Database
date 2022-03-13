package bdFI.exceptions;

/**
 * @author Guilherme Fernandes 60045 gc.fernandes@campus.fct.unl.pt
 * @author Martim Gamboa 61700 m.gamboa@campus.fct.unl.pt
 */
// throws exception if it does not exist productions with given rating
public class NoProductionsWithRatingException extends RuntimeException {
    static final long serialVersionUID = 0L;
    public NoProductionsWithRatingException() {
        super();
    }
}
