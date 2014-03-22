package strategy.selection;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Grzegorz Tokarz
 * @author Marcin Długosz
 * @author Michał Suwała
 *         Date: 21.03.14
 *         Time: 22:53
 */
public interface SelectionStrategy {
    public List<BitSet> select(LinkedList<BitSet> population);
    public BitSet getXY();
}
