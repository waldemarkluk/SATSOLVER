package strategy.crossover;

import logic.reader.CNF;
import utils.LogicHelper;

import java.util.*;

/**
 * @author Grzegorz Tokarz
 * @author Marcin Długosz
 * @author Michał Suwała
 *         Date: 21.03.14
 *         Time: 11:40
 */
public class OnePointCrossover extends MaskBasedCrossover {
    public OnePointCrossover(Random rnd) {
        super(rnd);
    }

    @Override
    public BitSet genMask(BitSet X, BitSet Y) {
        //TODO: spiltratio
        double spiltratio=0.5;//przetnij chromosomy na polowie
        BitSet chromosomeXmask=new BitSet(CNF.s2Index.size());
        chromosomeXmask.set(0,(int)((CNF.s2Index.size()-1)*spiltratio));
        return chromosomeXmask;
    }
}
