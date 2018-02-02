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
}
