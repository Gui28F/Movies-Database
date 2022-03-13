package bdFI;

/**
 * @author Guilherme Fernandes 60045 gc.fernandes@campus.fct.unl.pt
 * @author Martim Gamboa 61700 m.gamboa@campus.fct.unl.pt
 *
 * Non-public interface with methods that change Person object
 */
interface PersonChange extends Person{
    /**
     * adds a participation <code>participation</code> to last position of list participations
     *
     * @param participation participation
     */
    void addParticipation(Participation participation);

    /**
     * removes a participation from the list of person participations
     * @param participation participation to remove
     */
    void removeParticipation(Participation participation);
}
