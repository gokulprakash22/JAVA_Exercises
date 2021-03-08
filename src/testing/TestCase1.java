package testing;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCase1 {

	@Test
	public void test1() {
		Calculator calci = new Calculator();
		int result = calci.add(10, 10);
		assertEquals(20,result);
	}
	@Test
	public void test2() {
		Calculator calci = new Calculator();
		assertEquals(40,calci.add(20, 20));
	}
	@Test
	public void test3() {
		Calculator calci = new Calculator();
		assertEquals(60,calci.add(30, 30));
	}

}
