import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class PlayerTest {
    @Test
    public void test_doLoop() throws Exception {

    }

    @Test
    public void test_getPosition() throws Exception {
        assertEquals(1, Player.getPosition(0x1000, 4));
        assertEquals(1, Player.getPosition(0x0010, 2));
        assertEquals(4, Player.getPosition(0x4000, 4));
    }

}