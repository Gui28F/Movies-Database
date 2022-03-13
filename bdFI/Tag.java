package bdFI;

import bdFI.exceptions.NoShowsWithTagException;
import dataStructure.Iterator;

import java.io.Serializable;

/**
 * @author Guilherme Fernandes 60045 gc.fernandes@campus.fct.unl.pt
 * @author Martim Gamboa 61700 m.gamboa@campus.fct.unl.pt
 *
 * Tag interface
 */
public interface Tag extends Serializable {

    /**
     * return tag
     *
     * @return tag
     */
    String tag();

    /**
     * gives an iterator with all shows with this tag
     * @return Show iterator with all shows with tag
     * @throws NoShowsWithTagException if don't exist any show with tag
     */
    Iterator<Show> shows()throws NoShowsWithTagException;

    /**
     * verify if exists shows with this tag
     * @return true if exists shows with this tag and false if it´s not
     */
    boolean hasShows();
}
