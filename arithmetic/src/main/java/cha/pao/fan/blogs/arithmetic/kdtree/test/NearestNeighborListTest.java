package cha.pao.fan.blogs.arithmetic.kdtree.test;

import cha.pao.fan.blogs.arithmetic.kdtree.NearestNeighborList;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class NearestNeighborListTest {

	@Test
	public void testNearestNeighborList() {
		NearestNeighborList<String> nnl = new NearestNeighborList<String>(3);
		nnl.insert("A", 3.0);
		nnl.insert("B", 2.0);
		nnl.insert("D", 0.0);
		nnl.insert("C", 1.0);

		assertEquals(2.0, nnl.getMaxPriority(), 0.1);
		assertEquals("B", nnl.getHighest());
		assertEquals("B", nnl.removeHighest());
		assertEquals("C", nnl.removeHighest());
		assertEquals("D", nnl.removeHighest());
	}

}
