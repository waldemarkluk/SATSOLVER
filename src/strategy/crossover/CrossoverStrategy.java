package strategy.crossover;

import java.util.ArrayList;
import java.util.BitSet;

/**
 * @author Grzegorz Tokarz
 * @author Marcin Długosz
 * @author Michał Suwała
 *         Date: 21.03.14
 *         Time: 11:14
 */
public interface CrossoverStrategy {
    public ArrayList<BitSet> crossover(BitSet X, BitSet Y);
}
