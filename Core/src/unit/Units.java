package unit;

import java.util.Map;
import java.util.Set;

public class Units {
	public static Unit multiply(Unit u1, Unit u2)
	{
		Map<Unit,Integer> units=u1.getSubUnits();
		for(Map.Entry<Unit,Integer> entry:u2.getSubUnits().entrySet())
			if(units.containsKey(entry.getKey()))
				units.put(entry.getKey(),units.get(entry.getKey())+entry.getValue());
			else
				units.put(entry.getKey(),entry.getValue());
		return makeUnit(units);
	}
	public static Unit divide(Unit u1, Unit u2)
	{
		return multiply(u1,invert(u2));
	}
	public static Unit invert(Unit u)
	{
		Map<Unit,Integer> units=u.getSubUnits();
		Set<Map.Entry<Unit,Integer>> entries=units.entrySet();
		for(Map.Entry<Unit,Integer> entry:entries)
			units.put(entry.getKey(),-entry.getValue());
		return Units.makeUnit(units);
	}
	private static UnitSystem getDefaultSystem()
	{
		return SiSystem.INSTANCE;
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
