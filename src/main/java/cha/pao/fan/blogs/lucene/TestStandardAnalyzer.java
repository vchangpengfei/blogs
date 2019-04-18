package cha.pao.fan.blogs.lucene;

import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.*;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

public class TestStandardAnalyzer {

    @Test
    public void test0() throws IOException {

        StringBuilder sb = new StringBuilder();
        sb.append("我是中国人。 １２３４ Ｔｅｓｔｓ ");
        String input = sb.toString();
        StandardTokenizer ts = new StandardTokenizer();
        ts.setReader(new StringReader(input));

        CharTermAttribute termAtt = ts.getAttribute(CharTermAttribute.class);
        OffsetAttribute offsetAtt = ts.getAttribute(OffsetAttribute.class);
        TypeAttribute typeAtt = ts.getAttribute(TypeAttribute.class);
        PositionIncrementAttribute posIncrAtt = ts.getAttribute(PositionIncrementAttribute.class);
        PositionLengthAttribute posLengthAtt = ts.getAttribute(PositionLengthAttribute.class);
        KeywordAttribute keywordAtt = ts.getAttribute(KeywordAttribute.class);
        PayloadAttribute payloadAtt = ts.getAttribute(PayloadAttribute.class);
        ts.reset();
        while(ts.incrementToken())
        {
            System.out.println(termAtt);
            System.out.println(offsetAtt);
            System.out.println(typeAtt);
            System.out.println(posIncrAtt);
            System.out.println(posLengthAtt);
            System.out.println(keywordAtt);
            System.out.println(payloadAtt);
            System.out.println();
        }
        ts.end();
        ts.close();
    }


}
