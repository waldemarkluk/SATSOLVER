package logic;

import logic.reader.CNF;

import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Grzegorz Tokarz
 * @author Marcin Długosz
 * @author Michał Suwała
 *         Date: 19.03.14
 *         Time: 22:21
 */
public class TruthAssigment {
    public HashSet<Clause> satisfiedClauses = new HashSet<Clause>();
    public HashSet<Clause> unSatisfiedClauses = new HashSet<Clause>();
    public HashMap<Clause,BitSet> satResults = new HashMap<Clause, BitSet>();

    public void satisfies(Clause c)
    {
        satisfiedClauses.add(c);
    }

    public void calculateUnSatisfed()
    {

        unSatisfiedClauses = (HashSet<Clause>)CNF.clauses.clone();
        unSatisfiedClauses.removeAll(satisfiedClauses);
    }
}
