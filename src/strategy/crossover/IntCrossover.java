package strategy.crossover;

import logic.Clause;
import logic.reader.CNF;

import java.util.*;

/**
 * @author Grzegorz Tokarz
 * @author Marcin Długosz
 * @author Michał Suwała
 *         Date: 21.03.14
 *         Time: 11:17
 */
public class IntCrossover implements CrossoverStrategy {
    private Random rnd=null;
    public IntCrossover(Random rnd)
    {
        this.rnd=rnd;
    }
    @Override
    public ArrayList<BitSet> crossover(BitSet X, BitSet Y) {
        CNF.totalSatisfiedCount(X);
        CNF.totalSatisfiedCount(Y);

        CNF.calculateUnSatisfed(X);
        CNF.calculateUnSatisfed(Y);

        HashSet<Clause> unSatByXY=(HashSet<Clause>)CNF.cacheTruthAssigmentStats.get(X).unSatisfiedClauses.clone();
        HashSet<Clause> satByXY=(HashSet<Clause>)CNF.cacheTruthAssigmentStats.get(X).satisfiedClauses.clone();

        /*czesc wspolna*/
        unSatByXY.retainAll(CNF.cacheTruthAssigmentStats.get(Y).unSatisfiedClauses);
        satByXY.retainAll(CNF.cacheTruthAssigmentStats.get(Y).satisfiedClauses);

        //System.out.println(satByXY);
        //System.out.println(unSatByXY);

        BitSet reserved=new BitSet();
        BitSet Z = new BitSet();

        for(Clause c:unSatByXY)
        {
            int lastDelta=-9999;
            int newDelta;
            int flipIndex=-1;
            BitSet mask=(BitSet)c.mask.clone();
            mask.andNot(reserved);

            for(int i=mask.nextSetBit(0);i>=0;i=mask.nextSetBit(i+1))
            {
                if(lastDelta<(newDelta=CNF.Delta(X,Y,i)))
                {
                    lastDelta=newDelta;
                    flipIndex=i;
                    //System.out.println(lastDelta);
                }
            }
            if(flipIndex>-1)
            {
                reserved.set(flipIndex);
                Z.set(flipIndex,!X.get(flipIndex));
            }
        }
        for(Clause c:satByXY)
        {
            //temp maska pomocnicza
            BitSet temp=new BitSet();
            //ktore z X i Y są takie same :) tj maska - satisfied by zwraca maskę bitów ktore spelniaja klauzule
            temp.or(c.satisfiedBy(X));
            temp.and(c.satisfiedBy(Y));
            //nie są zarezerwowane w poprzednim kroku
            temp.andNot(reserved);
            //uaktualnij rezerwacje
            reserved.or(temp);
            //zaladuj wartosci docelowe do tmp
            temp.and(X);
            //zaladuj do Z'ta
            Z.or(temp);
        }

        BitSet temp=(BitSet)reserved.clone();
        temp.flip(0,CNF.s2Index.size());
        for(int i=temp.nextSetBit(0);i>-1;i=temp.nextSetBit(i+1))
        {
            Z.set(i,rnd.nextBoolean());
        }
        BitSet Z2=(BitSet)Z.clone();
        for(int i=temp.nextSetBit(0);i>-1;i=temp.nextSetBit(i+1))
        {
            Z2.set(i,rnd.nextBoolean());
        }
        double mutationRate=0.01;//1% :p
        int mutationLimiter=16;
        for(int i=0;i<mutationLimiter;i++)
        {
            if(rnd.nextDouble()<=mutationRate)
            {
                //Z1.flip(rnd.nextInt(CNF.s2Index.size() - 1));
                Z2.flip(rnd.nextInt(CNF.s2Index.size() - 1));
            }
        }
        for(int i=0;i<mutationLimiter;i++)
        {
            if(rnd.nextDouble()<=mutationRate)
            {
                Z.flip(rnd.nextInt(CNF.s2Index.size() - 1));
                //Z2.flip(rnd.nextInt(CNF.s2Index.size() - 1));
            }
        }
        return new ArrayList<BitSet>(Arrays.asList(Z,Z2));
    }
}
