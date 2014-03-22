package utils;

import logic.TruthAssigment;
import logic.reader.CNF;

import java.nio.LongBuffer;
import java.util.BitSet;
import java.util.Random;

/**
 * @author Grzegorz Tokarz
 * @author Marcin Długosz
 * @author Michał Suwała
 *         Date: 19.03.14
 *         Time: 19:03
 */
public class LogicHelper {
    public static BitSet stringToBitSet(String in)
    {
        BitSet bs=new BitSet();
        int i=0;
        int l=in.length()-1;
        for(char c:in.toCharArray())
        {
            if(c=='1')
                bs.set(l-i);
            i++;
        }
        return bs;
    }

    public static String toString(BitSet bs) {
        if(bs.cardinality()>0)
        {
            String str="";
            for(Long l:bs.toLongArray())
            {
                str=Long.toBinaryString(l)+str;
            }
            return str;
        }
        else
            return "";
    }
    public static String toStringPadded(BitSet bs,int pad) {
        String bsStr=toString(bs);
        int m=pad-bsStr.length();
        if(bsStr.length()<pad)
        {
            for(int i=0;i<m;i++)
            {
                bsStr="0"+bsStr;
            }
        }
        bsStr=bsStr.substring(0,pad);
        return bsStr;
    }
    public static String toStringPadded(BitSet bs) {
        return toStringPadded(bs, CNF.s2Index.size());
    }

    public static void generateProblem()
    {
        Random rnd=new Random();
        int i=0;
        String str;
        String buff="";
        for(int j=0;j<2;j++)
        {
            str=Long.toBinaryString(rnd.nextLong());
            for(char c:str.toCharArray())
            {
                if(c=='0')
                {
                    System.out.print("~x"+i+".");
                    buff='1'+buff;
                }
                else
                {
                    System.out.print("x"+i+".");
                    buff='0'+buff;
                }
                i++;
            }
            System.out.print("\n");
        }
        System.out.println(buff);
    }
}
