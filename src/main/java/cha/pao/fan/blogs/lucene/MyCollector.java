package cha.pao.fan.blogs.lucene;

import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.search.Collector;
import org.apache.lucene.search.LeafCollector;

import java.io.IOException;

public class MyCollector implements Collector {

    @Override
    public LeafCollector getLeafCollector(LeafReaderContext context) throws IOException {
        return null;
    }

    @Override
    public boolean needsScores() {
        return false;
    }
}
