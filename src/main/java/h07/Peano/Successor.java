package h07.Peano;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;

@DoNotTouch
public class Successor implements NaturalNumber {
    public final NaturalNumber predecessor;

    public Successor(NaturalNumber predecessor) {
        this.predecessor = predecessor;
    }
}
