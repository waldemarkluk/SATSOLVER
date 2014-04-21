package strategy.crossover;

import logic.reader.CNF;
import utils.LogicHelper;

import java.util.BitSet;
import java.util.Random;

/**
 * @author Grzegorz Tokarz
 * @author Marcin Długosz
 * @author Michał Suwała
 *         Date: 21.03.14
 *         Time: 11:40
 */
public class DifferentialCrossover extends MaskBasedCrossover {
    public DifferentialCrossover(Random rnd) {
        super(rnd);
    }

    @Override
    public BitSet genMask(BitSet X, BitSet Y) {
        BitSet mask= (BitSet) X.clone();
        mask.xor(Y);
        //add some noise
        mask.and(LogicHelper.getRandomBitSet(X.length()));
        return mask;
    }
}
