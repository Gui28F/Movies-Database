package bdFI.exceptions;

/**
 * @author Guilherme Fernandes 60045 gc.fernandes@campus.fct.unl.pt
 * @author Martim Gamboa 61700 m.gamboa@campus.fct.unl.pt
 */
// throws exception if show has already completed production
public class ShowAlreadyHasCompletedProductionException extends RuntimeException {
    static final long serialVersionUID = 0L;
    public ShowAlreadyHasCompletedProductionException() {
        super();
    }
}
