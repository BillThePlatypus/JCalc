package unit;

import java.util.Map;
import java.util.Set;

public class Units {
	public static Unit multiply(Unit u1, Unit u2)
	{

	}
	public static Unit divide(Unit u1, Unit u2)
	{

	}
	public static Unit invert(Unit u)
	{

	}
	private static UnitSystem getDefaultSystem()
	{
		return SiSystem.INSTANCE;
	}
	static Unit makeUnit(Map<Unit,Integer> units)
	{
		Set<Map.Entry<Unit,Integer>> entries=units.entrySet();
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
