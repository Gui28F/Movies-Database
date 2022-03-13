package bdFI.exceptions;
/**
 * @author Guilherme Fernandes 60045 gc.fernandes@campus.fct.unl.pt
 * @author Martim Gamboa 61700 m.gamboa@campus.fct.unl.pt
 */
// throws exception if person has no shows
public class PersonHasNoShowsException extends RuntimeException{
    static final long serialVersionUID = 0L;
    public PersonHasNoShowsException(){
        super();
    }
}
