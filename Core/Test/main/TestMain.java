package main;

import unit.SiSystem;
import number.Number;

public class TestMain
{
	public static void main(String[] args)
	{
		Number n1=new Number(3, SiSystem.JOULE);
		Number n2=new Number(2, SiSystem.SECOND);
		System.out.println(Number.multiply(n1,n2));
		System.out.println(Number.multiply(n1,n2).getUnit().getSubUnits());
		System.out.println(Number.divide(n1,n2));
		System.out.println(Number.divide(n1,n2).getUnit().getSubUnits());
	}
}
