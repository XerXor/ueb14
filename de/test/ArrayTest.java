package de.test;

import junit.framework.TestCase;
import base.Array;
import exceptions.ArrayIsEmptyException;
import exceptions.QueueIsEmptyException;
import exceptions.QueueIsFullException;

public class ArrayTest extends TestCase {

	private Array array = null;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		try {
			array = new Array();
		} catch (QueueIsFullException e) {
			e.printStackTrace();
			fail("Exception geworfen." + e.getMessage());
		}
	
	}
	
	public void testArrayBase()
	{
		
		assertTrue(array.isEmpty());
		assertEquals(array.getSize(),0);
		for (int i = 0;i<100;i++)
		{
			try {
				array.add(i);
			} catch (IllegalArgumentException e) {
				fail("Error geworfen:"+e.getMessage());
			} catch (QueueIsEmptyException e) {
				fail("Error geworfen bei Eleement("+i+"):"+e.getMessage());
			}
		}
		assertEquals(array.getSize(),100);
		for(int i=0;i<array.getSize();i++)
		{
			int value = (Integer)(array.getElement(i));
			assertTrue(value+".Element ist ungleich "+i,value == i);
		}
		assertTrue(array.isFull());
	}

	
	public void testArrayKonsistenz2()
	{
		System.out.println("Konsistenzpruefung jedes 2. Element loeschen und fuellen.");
		assertTrue(array.isEmpty());
		assertEquals(array.getSize(),0);
		for (int i = 0;i<100;i++)//Fuelle elemente auf
		{
			try {
				array.add(i);
			} catch (IllegalArgumentException e) {
				fail("Error geworfen:"+e.getMessage());
			} catch (QueueIsEmptyException e) {
				fail("Error geworfen bei Element("+i+"):"+e.getMessage());
			}
		}
		System.out.println(array.toString());
		for (int i = 0;i<100;i++) //jedes zweite element loeschen
		{
			if ((i % 2 )==0)
			{
				
				try {
					array.remove(i);
				} catch (IllegalArgumentException e) {
					fail("Error geworfen bei Element("+i+"):"+e.getMessage());
				} catch (ArrayIsEmptyException e) {
					fail("Error geworfen bei Element("+i+"):"+e.getMessage());
				} catch (QueueIsFullException e) {
					fail("Error geworfen bei Element("+i+"):"+e.getMessage());
				}
			}
		}
		System.out.println(array.toString());
		assertEquals(50,array.getSize());
		for (int i = 0;i<50;i++) //50 elemente einfuegen
		{
				try {
					array.add(i);
				} catch (IllegalArgumentException e) {
					fail("Error geworfen bei Element("+i+"):"+e.getMessage());
				} catch (QueueIsEmptyException e) {
					fail("Error geworfen bei Element("+i+"):"+e.getMessage());
				}			
		}
		System.out.println(array.toString());
	}

	public void testArrayKonsistenzPartialloeschung()
	{
		System.out.println("50 Elemente loeschen und auffuellen.");
		assertTrue(array.isEmpty());
		assertEquals(array.getSize(),0);
		for (int i = 0;i<100;i++)//Fuelle elemente auf
		{
			try {
				array.add(i);
			} catch (IllegalArgumentException e) {
				fail("Error geworfen:"+e.getMessage());
			} catch (QueueIsEmptyException e) {
				fail("Error geworfen bei Element("+i+"):"+e.getMessage());
			}
		}
		System.out.println(array.toString());
		for (int i = 0;i<50;i++) //alles loeschen.
		{
				
				try {
					array.remove(i);
				} catch (IllegalArgumentException e) {
					fail("Error geworfen bei Element("+i+"):"+e.getMessage());
				} catch (ArrayIsEmptyException e) {
					fail("Error geworfen bei Element("+i+"):"+e.getMessage());
				} catch (QueueIsFullException e) {
					fail("Error geworfen bei Element("+i+"):"+e.getMessage());
				}
			
		}
		System.out.println(array.toString());
		assertEquals(50,array.getSize());
		for (int i = 0;i<50;i++) //50 elemente einfuegen
		{
				try {
					array.add(i);
				} catch (IllegalArgumentException e) {
					fail("Error geworfen bei Element("+i+"):"+e.getMessage());
				} catch (QueueIsEmptyException e) {
					fail("Error geworfen bei Element("+i+"):"+e.getMessage());
				}			
		}
		System.out.println(array.toString());
	}
	
	public void testArrayKonsistenzKomplettloeschung()
	{
		System.out.println("100 Elemente loeschen und mit 50 Elementen auffuellen.");	
		assertTrue(array.isEmpty());
		assertEquals(array.getSize(),0);
		for (int i = 0;i<100;i++)//Fuelle elemente auf
		{
			try {
				array.add(i);
			} catch (IllegalArgumentException e) {
				fail("Error geworfen:"+e.getMessage());
			} catch (QueueIsEmptyException e) {
				fail("Error geworfen bei Element("+i+"):"+e.getMessage());
			}
		}
		System.out.println(array.toString());
		for (int i = 0;i<100;i++) //alles loeschen.
		{
				
				try {
					array.remove(i);
				} catch (IllegalArgumentException e) {
					fail("Error geworfen bei Element("+i+"):"+e.getMessage());
				} catch (ArrayIsEmptyException e) {
					fail("Error geworfen bei Element("+i+"):"+e.getMessage());
				} catch (QueueIsFullException e) {
					fail("Error geworfen bei Element("+i+"):"+e.getMessage());
				}
			
		}
		System.out.println(array.toString());
		assertEquals(0,array.getSize());
		for (int i = 0;i<50;i++) //50 elemente einfuegen
		{
				try {
					array.add(i);
				} catch (IllegalArgumentException e) {
					fail("Error geworfen bei Element("+i+"):"+e.getMessage());
				} catch (QueueIsEmptyException e) {
					fail("Error geworfen bei Element("+i+"):"+e.getMessage());
				}			
		}
		assertEquals(50,array.getSize());
		System.out.println(array.toString());
	}
}
