package satsolver;

import logic.reader.CNF;

import java.util.BitSet;
import java.util.Comparator;

/**
 * @author Grzegorz Tokarz
 * @author Marcin Długosz
 * @author Michał Suwała
 *         Date: 20.03.14
 *         Time: 20:06
 */
public class PopulationComparator implements Comparator<BitSet> {
    //o1<=o2 -1,o1=o2 0, o1>=o2 1

    @Override
    public int compare(BitSet o1, BitSet o2) {
        if(CNF.totalSatisfiedCount(o1)<CNF.totalSatisfiedCount(o2))
            return 1;
        else if(CNF.totalSatisfiedCount(o1)==CNF.totalSatisfiedCount(o2))
            return 0;
        else
            return -1;
    }
}
