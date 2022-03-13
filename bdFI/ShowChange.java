package bdFI;

import bdFI.exceptions.ShowAlreadyHasCompletedProductionException;
import bdFI.exceptions.ShowISInProducingStatusException;

/**
 * @author Guilherme Fernandes 60045 gc.fernandes@campus.fct.unl.pt
 * @author Martim Gamboa 61700 m.gamboa@campus.fct.unl.pt
 * <p>
 * Non-public interface with methods that change Show object
 */
interface ShowChange extends Show {
    /**
     * Adds participation <code>participant</code>
     *
     * @param participation participant
     */
    void addParticipant(Participation participation);

    /**
     * Changes show's status to premiered
     *
     * @throws ShowAlreadyHasCompletedProductionException if show has already completed production
     */
    void premiere() throws ShowAlreadyHasCompletedProductionException;

    /**
     * Adds tag <code>tag</code>
     *
     * @param tag show's tag
     */
    void addTag(Tag tag);

    /**
     * @param stars rating
     * @throws ShowISInProducingStatusException if show is still producing
     */
    void rate(int stars) throws ShowISInProducingStatusException;

    /**
     * set the show status as PREMIERED
     */
    void setPremiered();
}
