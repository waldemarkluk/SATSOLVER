package logic;

import java.util.BitSet;

/**
 * @author Grzegorz Tokarz
 * @author Marcin Długosz
 * @author Michał Suwała
 *         Date: 16.03.14
 *         Time: 10:54
 */
public class Term {
    private String name;
    private boolean noNegation=true;


    public Term(String s)
    {
        name=s;
    }
    public void setNegated(boolean state)
    {
        noNegation=state;
    }
}
