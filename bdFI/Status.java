package bdFI;

/**
 * @author Guilherme Fernandes 60045 gc.fernandes@campus.fct.unl.pt
 * @author Martim Gamboa 61700 m.gamboa@campus.fct.unl.pt
 * 
 * Enumerator with show status
 */
public enum Status {
    PREMIERED("Premiered"), PRODUCING("Producing");
    private String description;

    Status(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
