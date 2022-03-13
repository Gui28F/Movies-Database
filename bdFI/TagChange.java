package bdFI;

/**
 * @author Guilherme Fernandes 60045 gc.fernandes@campus.fct.unl.pt
 * @author Martim Gamboa 61700 m.gamboa@campus.fct.unl.pt
 *
 * Non-public interface with methods that change Tag object
 */
interface TagChange extends Tag{
    /**
     * Adds show <code>show</code>
     *
     * @param show show
     */
    void addShow(Show show);

    /**
     * remove <code>show</code> from the list of shows with this tag
     * @param show show to remove
     */
    void removeShow(Show show);
}
