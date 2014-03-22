package strategy.selection;

import java.util.*;

/**
 * @author Grzegorz Tokarz
 * @author Marcin Długosz
 * @author Michał Suwała
 *         Date: 21.03.14
 *         Time: 22:54
 */
public class SelectionSimpleElite extends SelectionStrategyAbstract {

    @Override
    public List<BitSet> select(LinkedList<BitSet> population) {
        //% populacji biorący udział w rozmnażaniu
        double theta=0.5;
        selection=new LinkedList<BitSet>(Arrays.asList(population.get(0), population.get(rnd.nextInt((int) Math.ceil(theta * (population.size() - 1))))));
        return selection;
    }
}
