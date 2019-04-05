package cha.pao.fan.blogs.lucene;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexReaderContext;
import org.apache.lucene.search.IndexSearcher;

import java.util.concurrent.ExecutorService;

public class MyIndexSearch extends IndexSearcher {
    public MyIndexSearch(IndexReader r) {
        super(r);
    }

    public MyIndexSearch(IndexReader r, ExecutorService executor) {
        super(r, executor);
    }

    public MyIndexSearch(IndexReaderContext context, ExecutorService executor) {
        super(context, executor);
    }

    public MyIndexSearch(IndexReaderContext context) {
        super(context);
    }




}
