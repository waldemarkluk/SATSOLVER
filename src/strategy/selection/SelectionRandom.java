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
 *         Time: 23:05
 */
public class SelectionRandom extends SelectionStrategyAbstract {
    @Override
    public List<BitSet> select(LinkedList<BitSet> population) {
        //readonly
        Random rnd=new Random();
        BitSet X=population.get(rnd.nextInt(population.size()-1));
        BitSet Y=null;
        int maxtries=10;
        int tries=0;
        do {
            Y=population.get(rnd.nextInt(population.size()-1));
            tries++;
        }
        while(!X.equals(Y)&&tries<maxtries);
        selection.add(X);selection.add(Y);
        return selection;
    }
}
