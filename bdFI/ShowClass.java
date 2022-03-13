package bdFI;

import bdFI.exceptions.NoParticipationsException;
import bdFI.exceptions.ShowAlreadyHasCompletedProductionException;
import bdFI.exceptions.ShowISInProducingStatusException;
import dataStructure.DoubleList;
import dataStructure.Iterator;
import dataStructure.List;

/**
 * @author Guilherme Fernandes 60045 gc.fernandes@campus.fct.unl.pt
 * @author Martim Gamboa 61700 m.gamboa@campus.fct.unl.pt
 * <p>
 * Show class
 */
public class ShowClass implements ShowChange, Comparable<Show> {
    public static final int NUMBER_OF_RATES = 11;//Number of possible rates
    public static final int MIN_RATE = 0;  //min rate that is possible to give to the show
    public static final int MAX_RATE = 10; //max rate that is possible to give to the show
    protected List<Participation> participants; //show participations
    protected List<Tag> tags; //show tags
    private String idShow;// show id
    private int year;// year of production
    private String title;//show title
    protected int average;//rate average
    protected int nRates;// number of rates
    protected Status status; // show status

    public ShowClass(String idShow, int year, String title) {
        this.participants = new DoubleList<>();
        this.tags = new DoubleList<>();
        this.idShow = idShow;
        this.year = year;
        this.title = title;
        this.average = 0;
        this.nRates = 0;
        this.status = Status.PRODUCING;
    }


    @Override
    public String idShow() {
        return this.idShow;
    }

    @Override
    public int year() {
        return this.year;
    }

    @Override
    public String title() {
        return this.title;
    }

    @Override
    public int rating() {
        return this.average;
    }

    @Override
    public boolean isInProduce() {
        return status.equals(Status.PRODUCING);
    }

    @Override
    public Iterator<Tag> tags() {
        return tags.iterator();
    }

    @Override
    public Iterator<Participation> participations()throws NoParticipationsException {
        if(participants.isEmpty())
            throw new NoParticipationsException();
        return participants.iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ShowClass other = (ShowClass) obj;
        return this.idShow.equalsIgnoreCase(other.idShow());
    }

    @Override
    public int compareTo(Show o) {
        return this.title().compareTo(o.title());
    }

    @Override
    public void addParticipant(Participation participant) {
        this.participants.addLast(participant);
    }

    @Override
    public void premiere() throws ShowAlreadyHasCompletedProductionException {
        if (this.status.equals(Status.PREMIERED))
            throw new ShowAlreadyHasCompletedProductionException();
        setPremiered();
    }

    @Override
    public void addTag(Tag tag) {
        this.tags.addLast(tag);
    }

    @Override
    public void rate(int stars) throws ShowISInProducingStatusException {
        if (isInProduce())
            throw new ShowISInProducingStatusException();
        this.average = Math.round((float) ((stars + this.nRates * this.average) / ((float) (this.nRates + 1))));
        this.nRates++;
    }

    @Override
    public void setPremiered() {
        this.status = Status.PREMIERED;
    }
}
