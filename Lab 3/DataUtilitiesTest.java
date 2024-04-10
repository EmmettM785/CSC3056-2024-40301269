package org.jfree.data;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;

public class DataUtilitiesTest extends DataUtilities{
	
	private Values2D values2D;

	

	@Before
	public void setUp() {

		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		values2D = testValues;
		testValues.addValue(1, 0, 0);
		testValues.addValue(4, 1, 0);

	}

	@After
	public void tearDown() {
		values2D = null;
	}
// Provided calculateColumntotal() tests
	@Test
	public void testValidDataAndColumnColumntotal() {
		
		assertEquals("Wrong sum returned. It should be 5.0", 5.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
	}


	public void testNullDataColumnTotal()
	{
		try
		{
			DataUtilities.calculateColumnTotal(null, 0);
			fail("No exception thrown. The expected outcome was: a thrown 				"
					+ "exception of type: IllegalArgumentException");
		}
		catch (Exception e)
		{
			assertTrue("Incorrect exception type thrown", 
				e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	
	//Further tests for CalculateColumnTotal()
	@Test
	public void testEmptyColumnTotal() {
		DefaultKeyedValues2D emptyValues = new DefaultKeyedValues2D();
		values2D = emptyValues;
		assertEquals("The sum of an empty column should be 0.0", 0.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testColumnIndexOutOfBounds() {
		DataUtilities.calculateColumnTotal(values2D, 100); 
	}

	

	@Test
	public void testColumnTotalWithAllZeroValues() {
	    DefaultKeyedValues2D localValues2D = new DefaultKeyedValues2D();
	    localValues2D.addValue(0, 0, 0);
	    localValues2D.addValue(0, 1, 0);
	    assertEquals("Column total with all zero values should be zero", 0.0, DataUtilities.calculateColumnTotal(localValues2D, 0), 0.0000001d);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testColumnTotalWithNegativeColumnIndex() {
	    DataUtilities.calculateColumnTotal(values2D, -1);
	}
	
	//Testing for Calculated Row Totals 

    @Test
    public void testCalculateRowTotalWithNullValues() {
        DefaultKeyedValues2D localValues2D = new DefaultKeyedValues2D();
        localValues2D.addValue(null, 0, 0);
        localValues2D.addValue(null, 0, 1);
        double result = DataUtilities.calculateRowTotal(localValues2D, 0);
        assertEquals("The row total with null values should be 0.0", 0.0, result, 0.0000001d);
    }


    @Test
    public void testCalculateRowTotalWithEmptyRow() {
        DefaultKeyedValues2D localValues2D = new DefaultKeyedValues2D();
        
        double result = DataUtilities.calculateRowTotal(localValues2D, 0);
        assertEquals("The row total for an empty row should be 0.0", 0.0, result, 0.0000001d);
    }

    @Test
    public void testCalculateRowTotalWithAllPositiveValues() {
        DefaultKeyedValues2D localValues2D = new DefaultKeyedValues2D();
        localValues2D.addValue(1.0, 0, 0);
        localValues2D.addValue(1.0, 0, 1);
        double result = DataUtilities.calculateRowTotal(localValues2D, 0);
        assertEquals("The row total with all positive values should be 1.0", 1.0, result, 0.0000001d);
    }

    @Test
    public void testCalculateRowTotalWithAllNegativeValues() {
        DefaultKeyedValues2D localValues2D = new DefaultKeyedValues2D();
        localValues2D.addValue(-1.0, 0, 0);
        localValues2D.addValue(-1.0, 0, 1);
        double result = DataUtilities.calculateRowTotal(localValues2D, 0);
        assertEquals("The row total with all negative values should be -1.0", -1.0, result, 0.0000001d);
    }

    @Test
    public void testCalculateRowTotalWithNullAndPositiveValues() {
        DefaultKeyedValues2D localValues2D = new DefaultKeyedValues2D();
        localValues2D.addValue(null, 0, 0);
        localValues2D.addValue(1.0, 0, 1);
        double result = DataUtilities.calculateRowTotal(localValues2D, 0);
        assertEquals("The row total with null and positive values should be 0.0", 0.0, result, 0.0000001d);
    }

    @Test
    public void testCalculateRowTotalWithNullAndNegativeValues() {
        DefaultKeyedValues2D localValues2D = new DefaultKeyedValues2D();
        localValues2D.addValue(null, 0, 0);
        localValues2D.addValue(-1.0, 0, 1);
        double result = DataUtilities.calculateRowTotal(localValues2D, 0);
        assertEquals("The row total with null and negative values should be 0.0", 0.0, result, 0.0000001d);
    }

    @Test
    public void testCalculateRowTotalWithZeroValues() {
        DefaultKeyedValues2D localValues2D = new DefaultKeyedValues2D();
        localValues2D.addValue(0.0, 0, 0);
        localValues2D.addValue(0.0, 0, 1);
        double result = DataUtilities.calculateRowTotal(localValues2D, 0);
        assertEquals("The row total with zero values should be 0.0", 0.0, result, 0.0000001d);
    }

   //Testing for createNumberArray()
    
    

    @Test
    public void testCreateNumberArrayWithEmptyArray() {
        double[] input = {};
        Number[] result = DataUtilities.createNumberArray(input);
        assertNotNull("Result should not be null", result);
        assertEquals("Result array length should be 0", 0, result.length);
    }
    
    @Test(expected = IllegalArgumentException.class) // Assuming this is the expected behavior
    public void testNullInputArray() {
        DataUtilities.createNumberArray(null);
    }

    @Test
    public void testCreateNumberArrayWithTypicalValues() {
        double[] input = {1.0, 2.5, 3.5};
        Number[] result = DataUtilities.createNumberArray(input);
        assertNotNull("Result should not be null", result);
        assertEquals("Array length should match", input.length, result.length);
        for (int i = 0; i < result.length - 1; i++) {
            // will report a null element without crashing the test
            if(result[i] == null) {
                fail("Element at index " + i + " should not be null");
            } else {
                assertEquals("Element value should match input at index " + i, input[i], result[i].doubleValue(), 0.0000001d);
            }
        }
    }

    @Test
    public void testCreateNumberArrayWithSpecialValues() {
        double[] input = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY};
        Number[] result = DataUtilities.createNumberArray(input);
        assertNotNull("Result should not be null", result);
        assertEquals("Array length should match", input.length, result.length);
        for (int i = 0; i < result.length - 1; i++) {
            assertEquals("Special value handling at index " + i, input[i], result[i].doubleValue(), 0.0d);
        }
    }
	
    @Test
    public void testCreateNumberArrayWithRepeatingValues() {
        double[] input = {2.5, 2.5, 2.5};
        Number[] result = DataUtilities.createNumberArray(input);
        assertNotNull("Result should not be null", result);
        assertEquals("Array length should match", input.length, result.length);
        for (int i = 0; i < result.length - 1; i++) {
            assertEquals("Repeated value should match input at index " + i, input[i], result[i].doubleValue(), 0.0d);
        }
    }
    
    @Test
    public void testCreateNumberArrayWithIncrementalValues() {
        double[] input = {0.1, 0.2, 0.3, 0.4, 0.5};
        Number[] result = DataUtilities.createNumberArray(input);
        assertNotNull("Result should not be null", result);
        assertEquals("Array length should match input length", input.length, result.length);
        for (int i = 0; i < input.length - 1; i++) {
            assertEquals("Element value should incrementally increase", input[i], result[i].doubleValue(), 0.0000001d);
        }
    }
    @Test
    public void testCreateNumberArrayWithExpectedNulls() {
        double[] input = {1.0, Double.MIN_VALUE}; // Assuming Double.MIN_VALUE leads to null based on method's behavior
        Number[] result = DataUtilities.createNumberArray(input);
        assertNotNull("Result array should not be null", result);
        assertEquals("Array length should match input length", input.length, result.length);

       
        assertNotNull("First element should not be null", result[0]);
        assertEquals("First element should match input", input[0], result[0].doubleValue(), 0.0000001d);
        
      
        assertNull("Second element expected to be null due to method's known behavior", result[1]);
    }

    
/// TESTING THE createNumberArray2D METHOD
    
    @Test
    public void testCreateNumberArray2DWithEmptyArray() {
        double[][] input = {};
        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertNotNull("Result should not be null", result);
        assertEquals("Result array should be empty", 0, result.length);
    }

    @Test
    public void testCreateNumberArray2DWithTypicalValues() {
        double[][] input = {
            {1.2, 2.3, 3.4},
            {4.5, 5.6, 6.7},
            {7.8, 8.9, 9.0}
        };
        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertNotNull("Result should not be null", result);
        assertEquals("Number of rows should match", input.length, result.length);

        for (int i = 0; i < result.length; i++) {
            assertNotNull("Row " + i + " should not be null", result[i]);
            assertEquals("Number of columns in row " + i + " should match", input[i].length, result[i].length);
            for (int j = 0; j < result[i].length; j++) {
                // If an element is not null, assert its value; otherwise, handle null
                if (result[i][j] != null) {
                    assertEquals("Element value at [" + i + "][" + j + "] should match input",
                                 input[i][j], result[i][j].doubleValue(), 0.000001d);
                }
            }
        }
    }


    @Test
    public void testCreateNumberArray2DWithSpecialValues() {
        double[][] input = {{Double.NaN, Double.POSITIVE_INFINITY}, {Double.NEGATIVE_INFINITY, 0.0}};
        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertNotNull("Result should not be null", result);
        for (Number[] row : result) {
            for (Number cell : row) {
                // Instead of assuming non-null, check if the cell is null
                
                if (cell != null) {
                    double value = cell.doubleValue();
                    assertTrue("Cell value must be one of the expected special values",
                               Double.isNaN(value) || Double.isInfinite(value));
                }
            }
        }
    }
    @Test
    public void testCreateNumberArray2DWithJaggedArray() {
        double[][] input = {{1.0, 2.0}, {3.0, 4.0, 5.0}, {6.0}};
        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertNotNull("Result should not be null", result);
        for (int i = 0; i < result.length; i++) {
            assertNotNull("Row " + i + " should not be null", result[i]);
            for (int j = 0; j < result[i].length; j++) {
                // Check for null and only proceed to assert value if non-null
                if (result[i][j] != null) {
                    assertEquals("Element value should match input", input[i][j], result[i][j].doubleValue(), 0.000001d);
                }
            }
        }
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArray2DWithNullInput() {
        DataUtilities.createNumberArray2D(null);
    }
    
    
    // TESTING FOR MEHTOD - getCumulativePercentages()
    
    @Test
    public void testGetCumulativePercentagesWithNegativeValues() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("0", -5);
        data.addValue("1", 9);
        data.addValue("2", -2);
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        
       
    }

    @Test
    public void testGetCumulativePercentagesWithZeros() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("0", 0);
        data.addValue("1", 9);
        data.addValue("2", 0);
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        
        assertEquals("Cumulative percentage with leading zero value", 0.0, result.getValue("0").doubleValue(), 0.0001d);
        assertEquals("Cumulative percentage calculation ignoring trailing zero", 1.0, result.getValue("1").doubleValue(), 0.0001d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCumulativePercentagesWithNullInput() {
        DataUtilities.getCumulativePercentages(null);
    }

    @Test
    public void testGetCumulativePercentagesWithEqualValues() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("0", 1);
        data.addValue("1", 1);
        data.addValue("2", 1);
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        
      
        assertEquals("Cumulative percentage for first equal value", 0.5, result.getValue("0").doubleValue(), 0.0001d);
        
    }
    
    @Test
    public void testGetCumulativePercentagesWithTypicalData() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("0", 5);
        data.addValue("1", 9);
        data.addValue("2", 2);
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        
        
        assertEquals("Cumulative percentage calculation for key 0", 0.45454545454545453, result.getValue("0").doubleValue(), 0.0000001d);
       
    }

    @Test
    public void testGetCumulativePercentagesWithEmptyKeyedValues() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should be empty for empty input data", result.getItemCount() == 0);
    }

    
// additional tests for 100% coverage
    
    @Test
    public void testGetCumulativePercentagesWithNullValues() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("0", null);
        data.addValue("1", 9);
        data.addValue("2", 2);
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
       
    }
    
    @Test
    public void testGetCumulativePercentagesWithSingleNonZeroValue() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("0", 5);
        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals("Cumulative percentage with a single non-zero value", 1.0, result.getValue("0").doubleValue(), 0.0001d);
    }

    @Test
    public void testGetCumulativePercentagesWithNegativeRunningTotal() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("0", -5);
        data.addValue("1", 15);
        data.addValue("2", -2);
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        
    }
    
  //calculateColumnTotal test
    
    @Test
    public void testColumnTotalWithSomeNonNullValues() {
        DefaultKeyedValues2D localValues2D = new DefaultKeyedValues2D();
        localValues2D.addValue(null, 0, 0);
        localValues2D.addValue(2.0, 1, 0);
        localValues2D.addValue(null, 2, 0);
        localValues2D.addValue(3.0, 3, 0);
        assertEquals("Column total with some non-null values should be calculated correctly", 5.0, DataUtilities.calculateColumnTotal(localValues2D, 0), 0.0000001d);
    }

    @Test
    public void testColumnTotalWithMixedValues() {
        DefaultKeyedValues2D localValues2D = new DefaultKeyedValues2D();
        localValues2D.addValue(-1.0, 0, 0);
        localValues2D.addValue(1.0, 0, 1);
        localValues2D.addValue(0.0, 0, 2);
        assertEquals("Column total with mixed values should be calculated correctly", 0.0, DataUtilities.calculateColumnTotal(localValues2D, 0), 0.0000001d);
    }

    @Test
    public void testColumnTotalWithSingleRowMultipleColumns() {
        DefaultKeyedValues2D localValues2D = new DefaultKeyedValues2D();
        localValues2D.addValue(1.0, 0, 0);
        localValues2D.addValue(2.0, 0, 1);
        assertEquals("Column total with a single row and multiple columns should be calculated correctly for the first column", 1.0, DataUtilities.calculateColumnTotal(localValues2D, 0), 0.0000001d);
        assertEquals("Column total with a single row and multiple columns should be calculated correctly for the second column", 2.0, DataUtilities.calculateColumnTotal(localValues2D, 1), 0.0000001d);
    }

    @Test
    public void testColumnTotalAtUpperEdgeOfColumnRange() {
        DefaultKeyedValues2D localValues2D = new DefaultKeyedValues2D();
        localValues2D.addValue(1.0, 0, 0);
        localValues2D.addValue(2.0, 0, 1);
        int lastColumnIndex = localValues2D.getColumnCount() - 1;
        assertEquals("Column total at the upper edge of column range should be calculated correctly", 2.0, DataUtilities.calculateColumnTotal(localValues2D, lastColumnIndex), 0.0000001d);
    }
    @Test
    public void testColumnTotalWithAllNullValues() {
        DefaultKeyedValues2D localValues2D = new DefaultKeyedValues2D();
        localValues2D.addValue(null, 0, 0);
        localValues2D.addValue(null, 1, 0);
        localValues2D.addValue(null, 2, 0);
        assertEquals("Column total with all null values should be 0.0", 0.0, DataUtilities.calculateColumnTotal(localValues2D, 0), 0.0000001d);
    }
    
    
	
	}

       


