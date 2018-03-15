package unit;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

	public Unit multiply(Unit u)
	{
		Map<Unit,Integer> units=this.getSubUnits();
		for(Map.Entry<Unit,Integer> entry:u.getSubUnits().entrySet())
			if(units.containsKey(entry.getKey()))
				units.put(entry.getKey(),units.get(entry.getKey())+entry.getValue());
			else
				units.put(entry.getKey(),entry.getValue());
		return Unit.makeUnit(units);
	}

	public Unit invert()
	{
		Map<Unit,Integer> units=this.getSubUnits();
		Set<Map.Entry<Unit,Integer>> entries=units.entrySet();
		for(Map.Entry<Unit,Integer> entry:entries)
			units.put(entry.getKey(),-entry.getValue());
		return Unit.makeUnit(units);
	}

	public Unit divide(Unit u)
	{
		return this.multiply(u.invert());
	}

	static Unit makeUnit(Map<Unit,Integer> units)
	{
		Set<Map.Entry<Unit,Integer>> entries=units.entrySet();
		for(Map.Entry<Unit,Integer> entry:entries)
			if(entry.getValue()==0)
				units.remove(entry.getKey());
		if(entries.size()==1)
			for(Map.Entry<Unit,Integer> entry:entries) {
				if (entry.getValue() == 1)
					return entry.getKey();
				if(entry.getValue() == 0)
					return NullUnit.INSTANCE;
			}
		return new CompoundUnit(units);
	}
}
