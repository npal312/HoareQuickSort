import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SortTest {

	@Test
	void testNone() {
		String msg = "Should have nothing in the array.";
		Integer[] test = {};
		Sort.sort(test);
		assertEquals("", Sort.toString(test), msg);
	}
	
	@Test
	void testOne() {
		String msg = "Should have one element in the array.";
		Integer[] test = {4};
		Sort.sort(test);
		assertEquals("4", Sort.toString(test), msg);
	}
	
	@Test
	void testTwo() {
		String msg = "Should have two elements, sorted correctly, in the array.";
		Integer[] test = {10, 3};
		Sort.sort(test);
		assertEquals("3 10", Sort.toString(test), msg);
	}
	
	@Test
	void testThree() {
		String msg = "The array should be sorted correctly.";
		Integer[] test = {40, 10, 37};
		Sort.sort(test);
		assertEquals("10 37 40", Sort.toString(test), msg);
	}
	
	@Test
	void testLargeAmount() {
		String msg = "The array should be sorted correctly.";
		Integer[] test = {10, 40, 37, 45, 2, 1, 6, 26, 55, 3, 11, 99, 20, 24, 93, 91, 4, 5, 70, 49, 82, 63, 33, 0, 89, 30, 943};
		Sort.sort(test);
		assertEquals("0 1 2 3 4 5 6 10 11 20 24 26 30 33 37 40 45 49 55 63 70 82 89 91 93 99 943", Sort.toString(test), msg);
	}
	
	@Test
	void testNegatives() {
		String msg = "The array should be sorted correctly.";
		Integer[] test = {37, 45, 2, 26, 55, -17, -14, -999};
		Sort.sort(test);
		assertEquals("-999 -17 -14 2 26 37 45 55", Sort.toString(test), msg);
	}
	
	@Test
	void testRepeating() {
		String msg = "The array should be sorted correctly.";
		Integer[] test = {2, 1, 6, 3, 4, 5, 1, 1, 4, 5, 5, 5, 5, 5};
		Sort.sort(test);
		assertEquals("1 1 1 2 3 4 4 5 5 5 5 5 5 6", Sort.toString(test), msg);
	}
	
	@Test
	void testString() {
		String msg = "The array should be sorted correctly.";
		String[] test = {"nick", "palladino", "homework", "java", "eclipse"};
		Sort.sort(test);
		assertEquals("eclipse homework java nick palladino", Sort.toString(test), msg);
	}
	
	@Test
	void testChar() {
		String msg = "The array should be sorted correctly.";
		Character[] test = {'b', 'z', 'n', 'e', 'p'};
		Sort.sort(test);
		assertEquals("b e n p z", Sort.toString(test), msg);
	}
	
}
