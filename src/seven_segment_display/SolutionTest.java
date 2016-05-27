import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class SolutionTest {

    @Test
    public void testPrintSegments() throws Exception {

    }

    @Test
    public void testPad() throws Exception {
        assertEquals(" ### ", Solution.pad(" ", "#", 3, " "));
        assertEquals("#   #", Solution.pad("#", " ", 3, "#"));

    }

    @Test
    public void segmentsOn() throws Exception {
        assertEquals("#", Solution.Numbers.ZERO.segmentsOn(1, "#", " "));
        assertEquals("#", Solution.Numbers.ZERO.segmentsOn(2, "#", " "));
        assertEquals("#", Solution.Numbers.ZERO.segmentsOn(3, "#", " "));
        assertEquals("#", Solution.Numbers.ZERO.segmentsOn(4, "#", " "));
        assertEquals("#", Solution.Numbers.ZERO.segmentsOn(5, "#", " "));
        assertEquals("#", Solution.Numbers.ZERO.segmentsOn(6, "#", " "));
        assertEquals(" ", Solution.Numbers.ZERO.segmentsOn(7, "#", " "));
        assertEquals(" ", Solution.Numbers.ONE.segmentsOn(1, "#", " "));
        assertEquals("#", Solution.Numbers.ONE.segmentsOn(2, "#", " "));
        assertEquals("#", Solution.Numbers.ONE.segmentsOn(3, "#", " "));
        assertEquals(" ", Solution.Numbers.ONE.segmentsOn(4, "#", " "));
        assertEquals(" ", Solution.Numbers.ONE.segmentsOn(5, "#", " "));
        assertEquals(" ", Solution.Numbers.ONE.segmentsOn(6, "#", " "));
        assertEquals(" ", Solution.Numbers.ONE.segmentsOn(7, "#", " "));
    }

    @Test
    public void buildSegments() throws Exception {

        assertEquals(" # ", Solution.buildSegments(Solution.Numbers.ZERO, 0, "#", 1));
        assertEquals("# #", Solution.buildSegments(Solution.Numbers.ZERO, 1, "#", 1));
        assertEquals("   ", Solution.buildSegments(Solution.Numbers.ZERO, 2, "#", 1));
        assertEquals("# #", Solution.buildSegments(Solution.Numbers.ZERO, 3, "#", 1));
        assertEquals(" # ", Solution.buildSegments(Solution.Numbers.ZERO, 4, "#", 1));
    }

    @Test
    public void getNumbers() throws Exception {
        List<Solution.Numbers> list = Solution.getNumbers(2016);
        assertEquals(4, list.size());
        assertEquals(2, list.get(3).value);
        assertEquals(0, list.get(2).value);
        assertEquals(1, list.get(1).value);
        assertEquals(6, list.get(0).value);
    }
}