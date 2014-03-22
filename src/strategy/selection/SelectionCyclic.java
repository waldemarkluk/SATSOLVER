package strategy.selection;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Grzegorz Tokarz
 * @author Marcin Długosz
 * @author Michał Suwała
 *         Date: 21.03.14
 *         Time: 23:34
 */
public class SelectionCyclic implements SelectionStrategy {
    //TODO: fixme
    private SelectionStrategy[] strategies={new SelectionSimpleElite(),new SelectionHAM(),new SelectionIndexedHAM(),new SelectionRandom()};
    private int cyclePosition=0;

    @Override
    public List<BitSet> select(LinkedList<BitSet> population) {
        cyclePosition++;
        cyclePosition%=strategies.length;
        return strategies[cyclePosition].select(population);
    }

    @Override
    public BitSet getXY() {
        return strategies[cyclePosition].getXY();
    }
}
