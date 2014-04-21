package logic;

import logic.reader.CNF;
import satsolver.FitnessChart;
import satsolver.PopulationComparator;
import strategy.crossover.*;
import strategy.selection.*;
import utils.LogicHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Grzegorz Tokarz
 * @author Marcin Długosz
 * @author Michał Suwała
 *         Date: 16.03.14
 *         Time: 11:19
 */
public class SAT_Solver {
    private LinkedList<BitSet> population =
            new LinkedList<BitSet>();
    private int popSize=0;
    private Random rnd=new Random();
    private CrossoverStrategy crossoverStrategy=new OnePointCrossover(rnd);
    private SelectionStrategy selectionStrategy=new SelectionTournament();
    public static int totalFitness=0;

    public void initDebugPopulation(int populationSize,String file)
    {
        Scanner sc= null;
        try {
            sc = new Scanner(new File(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        int i=0;
        while(sc.hasNext())
        {
            if(i>=populationSize)
                break;
            population.add(LogicHelper.stringToBitSet(sc.next()));
            i++;
        }
        Collections.sort(population, new PopulationComparator());
        popSize=populationSize;
    }

    public void initPopulation(int populationSize)
    {
        Random rnd=new Random();
        for(int i=0;i<populationSize;i++)
        {
            byte[] randomBytes = new byte[(int)Math.ceil((double)CNF.s2Index.size()/8)];
            rnd.nextBytes(randomBytes);
            BitSet bs=BitSet.valueOf(randomBytes);
            //obetnij zbedne bity :)
            bs.set(CNF.s2Index.size(),bs.size(),false);
            population.add(bs);
            //System.out.println(LogicHelper.toStringPadded(bs));
        }
        //posortuj sobie
        Collections.sort(population, new PopulationComparator());
        popSize=populationSize;
    }

    public void naturalSelection()
    {
        Collections.sort(population, new PopulationComparator());
        population=new LinkedList<BitSet>(population.subList(0,popSize));
    }

    private void addChild(ArrayList<BitSet> Z)
    {
        population.addAll(Z);
    }
    private void computeStats(int tries)
    {
        totalFitness=0;
        int maxFitness=Integer.MIN_VALUE;
        int minFitness=Integer.MAX_VALUE;

        for(BitSet bs:population)
        {
            int fitness=CNF.totalSatisfiedCount(bs);
            if(fitness>maxFitness)
                maxFitness=fitness;
            if(fitness<minFitness)
                minFitness=fitness;
            totalFitness+=fitness;
            //System.out.println(LogicHelper.toStringPadded(bs)+"\t"+fitness+"/"+CNF.clauses.size());
        }
        FitnessChart.dataset.addValue((double)maxFitness/CNF.clauses.size(),"MAX",Integer.toString(tries));
        FitnessChart.dataset.addValue((double)minFitness/CNF.clauses.size(),"MIN",Integer.toString(tries));
        FitnessChart.dataset.addValue(((double)totalFitness/population.size())/CNF.clauses.size(),"AVG",Integer.toString(tries));
    }
    public void solve()
    {
        int maxTries=20000;
        int tries=0;

        while(CNF.totalSatisfiedCount(population.peekFirst())!=CNF.clauses.size()&&tries<maxTries)
        {
            //System.out.println("Pokolenie "+tries);
            computeStats(tries);
            //System.out.println(maxFitness+"\t"+minFitness+"\t"+((double)totalFitness/population.size()));
            //1) Wylosuj nasze osobniki do reprodukcji, i je rozmnoz
            selectionStrategy.select(population);
            crossover(selectionStrategy.getXY(), selectionStrategy.getXY());
            //2) Dokonaj selekcji naturalnej
            naturalSelection();
            tries++;
            //cache
            if(CNF.cacheTruthAssigmentStats.size()>popSize*2)
                CNF.cacheTruthAssigmentStats.clear();
        }
        System.out.println("Wynik("+CNF.totalSatisfiedCount(population.peekFirst())+"):"+LogicHelper.toStringPadded(population.peekFirst())+" Liczba iteracji:"+tries);
        computeStats(tries);
    }

    public ArrayList<BitSet> crossover(BitSet X, BitSet Y)
    {
        ArrayList<BitSet> children=crossoverStrategy.crossover(X,Y);
        addChild(children);
        return children;
    }
}
