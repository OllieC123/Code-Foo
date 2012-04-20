/**
 * rand.java
 *
 *
 * Generates random column numbers to be used in ConnectFourModel.
 *	Currently not in use.
 *
 */
import java.util.*;

public class rand {

	private int x;

    public rand() {
    	x = 0;

    }
    public int random()
    {
    	Random random = new Random();
    	return random.nextInt(7);
    }


}