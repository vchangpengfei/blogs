package jdk;

import java.util.BitSet;

public class BitSetTest {

    public static void main(String args[])
    {
        BitSet set=new BitSet();
        set.set(9);
        System.out.println(set.length());
        System.out.println(set.size());
        System.out.println(set.cardinality());

    }

}
