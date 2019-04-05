package cha.pao.fan.blogs.lucene;

import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.index.MultiDocValues;
import org.apache.lucene.index.NumericDocValues;
import org.apache.lucene.queries.function.FunctionValues;
import org.apache.lucene.queries.function.ValueSource;
import org.apache.lucene.queries.function.docvalues.IntDocValues;

import java.io.IOException;
import java.util.Map;

public class TestFunction extends ValueSource {

    public TestFunction(String field) {
        this.field = field;
    }

    private String field;

    @Override
    public FunctionValues getValues(Map map, LeafReaderContext leafReaderContext) throws IOException {
        int off=leafReaderContext.docBase;
        Object o=map.get("searcher");

        final NumericDocValues docValues=MultiDocValues.getNumericValues(leafReaderContext.reader(),field);


        return new IntDocValues(this) {
            private int lastDocID;

            /**
             * 计算分数score
             * @param i
             * @return
             * @throws IOException
             */
            @Override
            public int intVal(int i) throws IOException {
                if(i>docValues.docID()){
                    docValues.advance(i);
                }

                if(i==docValues.docID())
                {
                    return (int) docValues.longValue();
                }else{
                    return -1;
                }

            }




        };
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || (o.getClass() !=  TestFunction.class)) return false;
        TestFunction other = (TestFunction)o;
        return this.field.equals(other.field);
    }

    private static final int hcode = TestFunction.class.hashCode();
    @Override
    public int hashCode() {
        return hcode + field.hashCode();
    }

    @Override
    public String description() {
        return "test("+field+')';
    }
}
