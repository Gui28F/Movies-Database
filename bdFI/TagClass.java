package bdFI;


import bdFI.exceptions.NoShowsWithTagException;
import dataStructure.*;

/**
 * @author Guilherme Fernandes 60045 gc.fernandes@campus.fct.unl.pt
 * @author Martim Gamboa 61700 m.gamboa@campus.fct.unl.pt
 * <p>
 * Tag class
 */
public class TagClass implements TagChange {
    private String tag; //tag name
    protected Dictionary<String, Show> shows; // shows with this tag

    public TagClass(String tag) {
        this.tag = tag;
        this.shows = new AVLTree<>();
    }

    @Override
    public String tag() {
        return this.tag;
    }


    @Override
    public Iterator<Show> shows() throws NoShowsWithTagException {
        if (shows.isEmpty())
            throw new NoShowsWithTagException();
        return new ValuesIterator<>(shows.iterator());
    }

    @Override
    public boolean hasShows() {
        return !shows.isEmpty();
    }

    @Override
    public void addShow(Show show) {
        this.shows.insert(show.title(), show);
    }

    @Override
    public void removeShow(Show show) {
        this.shows.remove(show.title());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TagClass other = (TagClass) obj;
        return this.tag.equals(other.tag());
    }
}
