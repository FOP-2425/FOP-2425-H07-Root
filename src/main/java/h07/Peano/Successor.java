package h07.Peano;

public class Successor implements NaturalNumber {
    public final NaturalNumber predecessor;

    public Successor(NaturalNumber predecessor) {
        this.predecessor = predecessor;
    }
}
