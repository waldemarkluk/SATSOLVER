package strategy.selection;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author Grzegorz Tokarz
 * @author Marcin Długosz
 * @author Michał Suwała
 *         Date: 21.03.14
 *         Time: 23:06
 */
public abstract class SelectionStrategyAbstract implements SelectionStrategy {
    protected LinkedList<BitSet> selection=new LinkedList<BitSet>();
    protected Random rnd=new Random();

    @Override
    public abstract List<BitSet> select(LinkedList<BitSet> population);

    @Override
    public BitSet getXY() {
        if(selection!=null)
            return selection.poll();
        return null;
    }
}
