package utils;

import logic.TruthAssigment;
import logic.reader.CNF;

import java.nio.LongBuffer;
import java.util.BitSet;
import java.util.LinkedList;
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
    public static BitSet getRandomBitSet(int length)
    {
        Random rnd=new Random();
        byte[] randomBytes = new byte[(int)Math.ceil((double)length/8)];
        rnd.nextBytes(randomBytes);
        BitSet bs=BitSet.valueOf(randomBytes);
        //obetnij zbedne bity :)
        bs.set(length,bs.size(),false);
        return bs;
    }
    public static void generateProblem()
    {
        Random rnd=new Random();
        String str;
        String buff="";
        int liczbaKlauzul=1024;
        int liczbaTermow=6;
        int pulaTermow=128;
        buff+="[";
        for(int i=0;i<liczbaKlauzul;i++)
        {
            buff+="x"+i+",";
        }
        buff=buff.substring(0,buff.length()-1);
        buff+="]\n";
        for(int j=0;j<liczbaKlauzul;j++)
        {
            for(int ct=0;ct<liczbaTermow;ct++)
            {

                buff+=((rnd.nextBoolean()==true)?"~":"")+"x"+rnd.nextInt(pulaTermow)+";";
            }
            buff=buff.substring(0,buff.length()-1)+".\n";
        }
        System.out.println(buff);
    }
}
