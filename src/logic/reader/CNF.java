package logic.reader;

import logic.Clause;
import logic.Term;
import logic.TruthAssigment;
import utils.LogicHelper;

import java.io.File;
import java.util.*;

/**
 * @author Grzegorz Tokarz
 * @author Marcin Długosz
 * @author Michał Suwała
 *         Date: 16.03.14
 *         Time: 10:47
 */
public class CNF implements Reader {
    public static ArrayList<String> s2Index = new ArrayList<String>();
    public static HashSet<Clause> clauses = new HashSet<Clause>();

    /*cache*/
    public static HashMap<BitSet,TruthAssigment> cacheTruthAssigmentStats = new HashMap<BitSet, TruthAssigment>();

    private int clauseCount = 0;
    public static String index2Term(int index)
    {
        return s2Index.get(index);
    }
    public static int Imp(BitSet X, int i)
    {
        HashSet<Clause> S1=(HashSet<Clause>)cacheTruthAssigmentStats.get(X).unSatisfiedClauses.clone();
        HashSet<Clause> S2=(HashSet<Clause>)cacheTruthAssigmentStats.get(X).satisfiedClauses.clone();
        //System.out.println("S1:"+S1);
        //System.out.println("S2:"+S2);
        BitSet XX=(BitSet)X.clone();
        XX.flip(i);
        return totalSatisfiedCount(XX,S1)-(S2.size()-totalSatisfiedCount(XX,S2));
    }
    public static int Delta(BitSet X,BitSet Y, int i)
    {
        //System.out.println(i+":"+LogicHelper.toStringPadded(X,5));
        //System.out.println(i+":"+LogicHelper.toStringPadded(Y,5));
        return Imp(X,i)+Imp(Y,i);
    }
    public static void cacheSatisfity(BitSet X,Clause c, BitSet R)
    {
        TruthAssigment ta;
        if((ta=cacheTruthAssigmentStats.get(X))==null)
        {
            ta=new TruthAssigment();
            cacheTruthAssigmentStats.put(X,ta);
        }
        ta.satResults.put(c,R);
        ta.satisfies(c);
    }
    @Override
    public String toString()
    {
        return clauses.toString();
    }
    public static int totalSatisfiedCount(BitSet X)
    {
        return totalSatisfiedCount( X,CNF.clauses);
    }
    public static int totalSatisfiedCount(BitSet X,Set<Clause> clauseSet)
    {
        int counter=0;
        for(Clause clause:clauseSet)
        {
            //System.out.println(clause);
            counter+=(clause.satisfiedBy(X).cardinality()>0)?1:0;
        }
        return counter;
    }
    @Override
    public void read(String file) throws Exception {
        //TODO: przetwarzanie wyjatkow
        Scanner sc;
        if(file.equals(""))
            sc = new Scanner(System.in);
        else
            sc=new Scanner(new File(file));
        endofread:
        while(sc.hasNext())
        {
            String line=sc.next();
            String buff="";
            boolean negation=false;
            boolean declarationMode=false;
            Clause clause = new Clause();
            for(char c: line.toCharArray())
            {
                if(!declarationMode)
                {
                    switch(c)
                    {
                        case '[':
                            declarationMode=true;
                            break;
                        case ';':
                            if(buff.equals(""))
                                throw new Exception("Logic error: Buffer term buffer empty");
                            clause.addTerm(buff,negation);
                            negation=false;
                            buff="";
                            break;
                        case '~':
                            //negation
                            negation=true;
                            break;
                        case '.':
                            //end of clause
                            if(buff.equals(""))
                                break endofread;
                            clause.addTerm(buff,negation);
                            negation=false;
                            buff="";
                            clauses.add(clause);
                            clause=new Clause();
                            break;
                         default:
                             if(!Character.isLetterOrDigit(c))
                                 throw new Exception("Logic error 1");
                             buff+=c;
                    }
                }
                else
                {
                    switch(c)
                    {
                        case ',':
                            if(buff.equals(""))
                                throw new Exception("Logic error: Buffer term buffer empty");
                            s2Index.add(buff);
                            buff="";
                            break;
                        case ']':
                            declarationMode=false;
                            buff="";
                            break;
                        default:
                            if(!Character.isLetterOrDigit(c))
                                throw new Exception("Logic error 1");
                            buff+=c;
                    }
                }
            }
        }

    }

    public static void calculateUnSatisfed(BitSet x) {
        TruthAssigment ta=cacheTruthAssigmentStats.get(x);
        if(ta!=null)
            ta.calculateUnSatisfed();
    }

    public static void cacheUnSatisfity(BitSet X, Clause c, BitSet R) {
        TruthAssigment ta;
        if((ta=cacheTruthAssigmentStats.get(X))==null)
        {
            ta=new TruthAssigment();
            cacheTruthAssigmentStats.put(X,ta);
        }
        ta.unSatisfiedClauses.add(c);

    }
}
