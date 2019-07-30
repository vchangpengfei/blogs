// Key-size mismatch exception supporting KDTree class

package cha.pao.fan.blogs.arithmetic.kdtree;

public class KeyMissingException extends KDException { /* made public by MSL */

	public KeyMissingException() {
		super("Key not found");
	}

	// arbitrary; every serializable class has to have one of these
	public static final long serialVersionUID = 3L;

}
