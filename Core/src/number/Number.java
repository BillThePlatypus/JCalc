package number;

import unit.Unit;

public class Number
{
	private final Unit unit;
	private final double value;

	public Number(double value, Unit unit)
	{
		this.unit = unit;
		this.value = value;
	}

	public String toString()
	{
		return this.value + " " + this.unit.getSymbol(this.value);
	}

	public Number convertTo(Unit unit)
	{
		if (this.unit.equals(unit))
			return this;
		if (unit.getDimension().equals(this.unit.getDimension()))
		{
			double newValue = this.value / this.unit.asSi() * unit.asSi();
			return new Number(newValue, unit);
		} else
			throw new IllegalArgumentException(String.format("Can't convert from %s to %s because they are of different dimension", this.unit.getFullName(), unit.getFullName()));
	}
	public Unit getUnit()
	{
		return this.unit;
	}
	public static Number multiply(Number n1, Number n2)
	{
		return new Number(n1.value*n2.value, n1.getUnit().multiply(n2.getUnit()));
	}
	public Number invert()
	{
		return new Number(1/this.value,this.getUnit()).invert();
	}
	public static Number divide(Number n1, Number n2)
	{
		return multiply(n1,n2.invert());
	}
}
