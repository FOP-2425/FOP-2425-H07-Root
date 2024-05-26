package h07.Peano;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;

/**
 * Represents a successor of a natural number in Peano arithmetic.
 */
@DoNotTouch
public class Successor extends NaturalNumber {
    public final NaturalNumber predecessor;

    public Successor(NaturalNumber predecessor) {
        this.predecessor = predecessor;
    }

    @Override
    public String toString() {
        return "S(" + predecessor.toString() + ")";
    }
}
