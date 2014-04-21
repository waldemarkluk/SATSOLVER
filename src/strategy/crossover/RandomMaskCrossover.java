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
public class RandomMaskCrossover extends MaskBasedCrossover {
    private BitSet mask=null;
    private boolean keepMask=true;

    public RandomMaskCrossover(Random rnd) {
        super(rnd);
    }

    @Override
    public BitSet genMask(BitSet X, BitSet Y) {
        if(mask==null||!keepMask)
            mask=LogicHelper.getRandomBitSet(CNF.s2Index.size());

        return (BitSet) mask.clone();
    }
}
