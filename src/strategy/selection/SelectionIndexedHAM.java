package strategy.selection;

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
public class SelectionIndexedHAM extends SelectionStrategyAbstract {
    private int hamIndex=0;
    @Override
    public List<BitSet> select(LinkedList<BitSet> population) {
        //readonly
        int max=0;
        BitSet X=population.get(hamIndex%=population.size());
        BitSet temp=(BitSet)X.clone();
        BitSet Y=new BitSet();
        for(BitSet Z:population)
        {
            int card;
            temp.xor(Z);
            if((card=temp.cardinality())>max)
            {
                max=card;
                Y=temp;
                temp=(BitSet)X.clone();
            }
        }
        //System.out.println(max);
        selection.clear();
        selection.add(X);selection.add(Y);
        hamIndex++;
        return selection;
    }
}
