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
public class OnePointCrossover implements CrossoverStrategy {
    private Random rnd;

    public OnePointCrossover(Random rnd) {
        this.rnd = rnd;
    }

    @Override
    public ArrayList<BitSet> crossover(BitSet X, BitSet Y) {
        //TODO: spiltratio
        double spiltratio=0.5;//przetnij chromosomy na polowie
        BitSet chromosomeXmask=new BitSet(X.length());
        chromosomeXmask.set(0,(int)((X.length()-1)*spiltratio));

        BitSet Z1=(BitSet)X.clone();
        Z1.and(chromosomeXmask);//wez kawalek chromosomu
        BitSet temp=(BitSet)Y.clone();
        temp.andNot(chromosomeXmask);//wez drugi kawalek chromosomu
        Z1.or(temp);//polacz w 1

        BitSet Z2=(BitSet)Y.clone();
        Z2.andNot(chromosomeXmask);//wez kawalek Y
        temp=(BitSet)X.clone();
        temp.and(chromosomeXmask);
        Z2.or(temp);

        double mutationRate=0.3;//1% :p
        int mutationLimiter=16;
        for(int i=0;i<mutationLimiter;i++)
        {
            if(rnd.nextDouble()<=mutationRate)
            {
                Z1.flip(rnd.nextInt(CNF.s2Index.size() - 1));
            }
        }
        for(int i=0;i<mutationLimiter;i++)
        {
            if(rnd.nextDouble()<=mutationRate)
            {
                Z2.flip(rnd.nextInt(CNF.s2Index.size()-1));
            }
        }
        /*System.out.println(LogicHelper.toStringPadded(X)+" SAT("+CNF.totalSatisfiedCount(X)+")");
        System.out.println(LogicHelper.toStringPadded(Y)+" SAT("+CNF.totalSatisfiedCount(Y)+")");
        System.out.println("---------------------------------");
        System.out.println(LogicHelper.toStringPadded(Z1)+" SAT("+CNF.totalSatisfiedCount(Z1)+")");
        System.out.println(LogicHelper.toStringPadded(Z2)+" SAT("+CNF.totalSatisfiedCount(Z2)+")");   */
        return new ArrayList(Arrays.asList(Z1,Z2));
        //X&M1+Y&M2
    }
}
