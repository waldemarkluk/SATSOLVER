package logic;

import logic.reader.CNF;
import utils.LogicHelper;

import java.util.BitSet;

/**
 * @author Grzegorz Tokarz
 * @author Marcin Długosz
 * @author Michał Suwała
 *         Date: 16.03.14
 *         Time: 11:15
 */
public class Clause {
    public BitSet mask=new BitSet();
    private BitSet value=new BitSet();

    public Clause()
    {

    }

    public void addTerm(String term)
    {
        addTerm(term, false);
    }

    public void addTerm(String term, boolean negated)
    {
        if(!CNF.s2Index.contains(term))
            CNF.s2Index.add(term);
        mask.set(CNF.s2Index.indexOf(term));
        if(negated)
            value.clear(CNF.s2Index.indexOf(term));
        else
            value.set(CNF.s2Index.indexOf(term));
    }
    public BitSet satisfiedBy(BitSet X)
    {
       /* System.out.println(this);
        for (int i = 0; i <CNF.s2Index.size(); i++) {
            System.out.print(CNF.s2Index.get(i)+"="+X.get(i)+" ");
        }
        System.out.println("");*/
        BitSet A=(BitSet)X.clone();
        A.xor(value);
        A.flip(0,CNF.s2Index.size());
        A.and(mask);
        //System.out.println("Ilosc spelnionych termow:"+A.cardinality());
        if(A.cardinality()>0)
        {
            CNF.cacheSatisfity(X,this,A);
        }
        else
            CNF.cacheUnSatisfity(X,this,A);
        return A;
    }

    @Override
    public String toString()
    {
        String s="";
        for (int i = mask.nextSetBit(0); i >= 0; i = mask.nextSetBit(i+1)) {
            s+=((!value.get(i))?"~":"")+CNF.index2Term(i)+"+";
        }
        return s.substring(0, s.length()-1);
    }
}
