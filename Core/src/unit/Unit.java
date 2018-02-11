package unit;

import java.util.List;
import java.util.Map;

public abstract class Unit implements Comparable<Unit>
{
	public abstract String getSymbol();

	public String getSymbol(double quantity)
	{
		return this.getSymbol();
	}

	public abstract String getFullName();

	public String getFullName(double quantity)
	{
		return this.getFullName();
	}

	public abstract Dimension getDimension();

	public abstract List<UnitSystem> getPreferredSystems();

	public abstract double asSi();

	public abstract void addSystem(UnitSystem system);

	public abstract Map<Unit, Integer> getSubUnits();

	@Override
	public int compareTo(Unit u)
	{
		if(this.getFullName().equals(u.getFullName()))
			return Double.compare(this.asSi(),u.asSi());
		else
			return this.getFullName().compareTo(u.getFullName());
	}
	@Override
	public int hashCode()
	{
		int hash=this.getFullName().hashCode();
		hash|=this.getDimension().hashCode();
		hash|=Double.hashCode(this.asSi());
		hash|=this.getSymbol().hashCode();
		hash|=this.getClass().getName().hashCode();
		return hash;
	}
	//This should work without overriding for subclasses as well, unless they do something goofy/fancy
	@Override
	public boolean equals(Object o)
	{
		if(!o.getClass().equals(this.getClass()))
			return false;
		Unit u=(Unit)o;
		return this.asSi()==u.asSi()
				&& this.getFullName().equals(u.getFullName())
				&& this.getSymbol().equals(u.getSymbol())
				&& this.getDimension().equals(u.getDimension());
	}
	@Override
	public String toString()
	{
		return this.getFullName()+" ("+this.getClass().getName()+")";
	}
}
