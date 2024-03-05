package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RangeTest {
	
	private Range rangeObjectUnderTest;

	@Before
	public void setUp() throws Exception {
		rangeObjectUnderTest = new Range(-1, 1);
	}


	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCentralValueShouldBeZero() {
		assertEquals("The central value of -1 and 1 should be 0",
					0, rangeObjectUnderTest.getCentralValue(), 0.000000001d);
	}
	
	// Tests for expandToInclude
    @Test
    public void testExpandToIncludeWithNullRange() {
        Range result = Range.expandToInclude(null, 5.0);
        assertNotNull("The result should not be null when expanding a null range", result);
        assertEquals("The range should be from 5.0 to 5.0 when expanding a null range",
                    new Range(5.0, 5.0), result);
    }

    @Test
    public void testExpandToIncludeWithValueInsideRange() {
        Range result = Range.expandToInclude(rangeObjectUnderTest, 0.0);
        assertEquals("Expanding to include a value inside the range should not change the range",
                    rangeObjectUnderTest, result);
    }

    @Test
    public void testExpandToIncludeWithValueOutsideRange() {
        Range result = Range.expandToInclude(rangeObjectUnderTest, 2.0);
        assertEquals("The upper bound should be expanded to include value 2.0",
                    new Range(-1, 2.0), result);
    }

    @Test
    public void testExpandToIncludeWithHigherValueOutsideRange() {
        Range result = Range.expandToInclude(rangeObjectUnderTest, 2.0);
        assertEquals("The upper bound should be expanded to include the new higher value outside the range",
                    new Range(-1, 2.0), result);
    }

    @Test
    public void testExpandToIncludeWithExactUpperBound() {
        Range result = Range.expandToInclude(rangeObjectUnderTest, 1.0);
        assertEquals("Expanding with a value equal to the upper bound should not change the range",
                    rangeObjectUnderTest, result);
    }

    //Test for Shift
    
    @Test
    public void testShiftWithPositiveDelta() {
        Range result = Range.shift(rangeObjectUnderTest, 1.0, true);
        assertEquals("The range should be shifted to the right by 1.0",
                     new Range(0, 2), result);
    }

    @Test
    public void testShiftWithNegativeDelta() {
        Range result = Range.shift(rangeObjectUnderTest, -0.5, true);
        assertEquals("The range should be shifted to the left by 0.5",
                     new Range(-1.5, 0.5), result);
    }


    @Test
    public void testShiftAllowingZeroCrossing() {
        Range result = Range.shift(new Range(-2.0, -1.0), 1.5, true);
        assertEquals("The range should shift across zero when zero crossing is allowed",
                     new Range(-0.5, 0.5), result);
    }


    @Test
    public void testShiftWithPositiveDeltaAndZeroCrossing() {
        Range result = Range.shift(new Range(-2.0, 2.0), 3.0, true);
        assertEquals("The range should be shifted to the right by 3.0, allowing crossing zero",
                     new Range(1.0, 5.0), result);
    }

    @Test
    public void testShiftWithNegativeDeltaAndZeroCrossing() {
        Range result = Range.shift(new Range(-2.0, 2.0), -3.0, true);
        assertEquals("The range should be shifted to the left by 3.0, allowing crossing zero",
                     new Range(-5.0, -1.0), result);
    }

    @Test
    public void testShiftToCrossZeroWithZeroCrossingDisallowed() {
        Range result = Range.shift(new Range(0.5, 2.0), -1.0, false);
        assertEquals("The lower bound should adjust to zero, and the upper bound should shift left by 1.0 without crossing zero",
                     new Range(0.0, 1.0), result);
    }
    @Test
    public void testShiftEntireRangeToNegative() {
        Range result = Range.shift(new Range(1.0, 3.0), -5.0, true);
        assertEquals("The entire range should be shifted into the negative domain",
                     new Range(-4.0, -2.0), result);
    }

    @Test
    public void testShiftEntireRangeToPositive() {
        Range result = Range.shift(new Range(-3.0, -1.0), 5.0, true);
        assertEquals("The entire range should be shifted into the positive domain",
                     new Range(2.0, 4.0), result);
    }
    
    // Testing for GetLength()
    @Test
    public void testPositiveRangeLength() {
        Range range = new Range(1.0, 5.0);
        assertEquals("Length of positive range incorrect", 4.0, range.getLength(), 0.0000001d);
    }

    @Test
    public void testNegativeRangeLength() {
        Range range = new Range(-5.0, -1.0);
        assertEquals("Length of negative range incorrect", 4.0, range.getLength(), 0.0000001d);
    }

    @Test
    public void testZeroLengthRange() {
        Range range = new Range(3.0, 3.0);
        assertEquals("Length of zero-length range should be zero", 0.0, range.getLength(), 0.0000001d);
    }

    @Test
    public void testCrossZeroRangeLength() {
        Range range = new Range(-2.0, 2.0);
        assertEquals("Length of range crossing zero incorrect", 4.0, range.getLength(), 0.0000001d);
    }

    @Test
    public void testLargeRangeLength() {
        Range range = new Range(-1000.0, 1000.0);
        assertEquals("Length of large range incorrect", 2000.0, range.getLength(), 0.0000001d);
    }
    
    
    // testing for Constrain()
    @Test
    public void testValueInsideRange() {
        Range range = new Range(1.0, 10.0);
        assertEquals("Value inside the range should be returned as is", 5.0, range.constrain(5.0), 0.0000001d);
    }

    @Test
    public void testValueEqualToLowerBound() {
        Range range = new Range(1.0, 10.0);
        assertEquals("Value equal to lower bound should be returned as is", 1.0, range.constrain(1.0), 0.0000001d);
    }

    @Test
    public void testValueEqualToUpperBound() {
        Range range = new Range(1.0, 10.0);
        assertEquals("Value equal to upper bound should be returned as is", 10.0, range.constrain(10.0), 0.0000001d);
    }

    @Test
    public void testValueAtMidpointOfRange() {
        Range range = new Range(1.0, 10.0);
        assertEquals("Value at the midpoint should be returned as is", 5.5, range.constrain(5.5), 0.0000001d);
    }

    @Test
    public void testValueAboveUpperBound() {
        Range range = new Range(1.0, 10.0);
        assertEquals("Value above upper bound should return upper bound", 10.0, range.constrain(15.0), 0.0000001d);
    }
    
    // Testing for Intersects()

    @Test
    public void testNoIntersection() {
        Range range = new Range(5.0, 10.0);
        assertFalse("Range above should not intersect", range.intersects(11.0, 15.0));
    }

    @Test
    public void testTouchingRangesLowerBound() {
        Range range = new Range(5.0, 10.0);
        assertTrue("Ranges touching at lower bound should intersect", range.intersects(1.0, 5.0));
    }
    
    @Test
    public void testExactMatchOfRanges() {
        Range range = new Range(2.0, 8.0);
        assertTrue("Exact match of ranges should intersect", range.intersects(2.0, 8.0));
    }
    @Test
    public void testFullEnclosure() {
        Range range = new Range(5.0, 10.0);
        assertTrue("Range fully enclosing should intersect", range.intersects(1.0, 15.0));
    }

    @Test
    public void testExactMatch() {
        Range range = new Range(5.0, 10.0);
        assertTrue("Exactly matching range should intersect", range.intersects(5.0, 10.0));
    }

    @Test
    public void testWithin() {
        Range range = new Range(5.0, 10.0);
        assertTrue("Range within should intersect", range.intersects(6.0, 9.0));
    }

}
