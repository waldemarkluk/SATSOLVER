package strategy.selection;

import logic.SAT_Solver;
import logic.reader.CNF;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Grzegorz Tokarz
 * @author Marcin Długosz
 * @author Michał Suwała
 *         Date: 21.03.14
 *         Time: 23:05
 */
public class SelectionRoulette extends SelectionStrategyAbstract {
    @Override
    public List<BitSet> select(LinkedList<BitSet> population) {
        selection.clear();
        for(int i=0;i<2;i++)
        {
            double popIndex=0.0;
            double xr=rnd.nextDouble();
            for(BitSet p:population)
            {
                popIndex+=((double)CNF.totalSatisfiedCount(p)/ SAT_Solver.totalFitness);
                if(popIndex>=xr)
                {
                    selection.add(p);
                    break;
                }
            }
        }
        return selection;
    }
}
