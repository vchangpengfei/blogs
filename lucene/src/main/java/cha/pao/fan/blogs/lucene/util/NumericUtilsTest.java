package cha.pao.fan.blogs.lucene.util;

import org.apache.lucene.util.NumericUtils;

public class NumericUtilsTest {

    public static void main(String args[])
    {
        int docID=1024;
        byte[] scratch = new byte[4];
        NumericUtils.intToSortableBytes(docID, scratch, 0);
        System.out.println(docID);
        System.out.println(scratch);
        System.out.println(NumericUtils.sortableBytesToInt(scratch,0));
    }

}
