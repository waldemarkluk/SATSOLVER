package strategy.selection;

import logic.SAT_Solver;
import logic.reader.CNF;
import satsolver.PopulationComparator;
import utils.LogicHelper;

import java.util.BitSet;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Grzegorz Tokarz
 * @author Marcin Długosz
 * @author Michał Suwała
 *         Date: 21.03.14
 *         Time: 23:05
 */
public class SelectionTournament extends SelectionStrategyAbstract {
    private int tournamentSize=5;
    private double p=0.8;
    @Override
    public List<BitSet> select(LinkedList<BitSet> population) {
        selection.clear();
        LinkedList<BitSet> populationBackup=new LinkedList<BitSet>(population);
        Collections.shuffle(populationBackup,rnd);
        if(tournamentSize*2>populationBackup.size())
        {
            tournamentSize=(int)populationBackup.size()/2;
        }
        populationBackup.subList((tournamentSize>0&&tournamentSize*2<populationBackup.size())?tournamentSize*2:populationBackup.size(),populationBackup.size()).clear();
        for(int j=0;j<2;j++)
        {
            LinkedList<BitSet> populationCopy=new LinkedList<BitSet>(populationBackup.subList(j*tournamentSize,(j+1)*tournamentSize));
            //Collections.shuffle(populationCopy,rnd);

            //populationCopy.subList((tournamentSize>0&&tournamentSize<populationCopy.size())?tournamentSize:populationCopy.size(),populationCopy.size()).clear();
            Collections.sort(populationCopy, new PopulationComparator());
            double q=1-p;
            double sum=(1-Math.pow(q,tournamentSize));
            int i=0;
            double selectionR=rnd.nextDouble();

            for(BitSet X:populationCopy)
            {
                if((((1-Math.pow(q,i+1))/sum))>=selectionR)
                {
                    selection.add(X);
                    //System.out.println("Choosen with ratio:"+(1-Math.pow(q,i+1)-(1-Math.pow(q,i))));
                    break;
                }

                i++;
            }
        }

        return selection;
    }
}
