package cha.pao.fan.blogs.lucene;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class LuceneTestBase extends Assert {

    public static final String index_path="lucene_index";

    public Directory newDirectory() throws IOException {
        Path path=new File(index_path).toPath();
        Directory directory = FSDirectory.open(path);
        return directory;
    }



}
