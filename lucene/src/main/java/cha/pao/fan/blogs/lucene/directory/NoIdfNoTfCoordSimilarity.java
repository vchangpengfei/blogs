package cha.pao.fan.blogs.lucene.directory;

import org.apache.lucene.search.similarities.ClassicSimilarity;

/**
 * Created by pfchang
 * on 2021/1/14.
 */
public class NoIdfNoTfCoordSimilarity extends ClassicSimilarity {

	@Override
	public float idf(long docFreq, long numDocs) {
		return 1.0f;
	}

	@Override
	public float tf(float freq) {
		return 1.0f;
	}
}