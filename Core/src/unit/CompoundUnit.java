package unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CompoundUnit extends Unit
{
	private final String name;
	private final String symbol;
	private final TreeMap<Unit, Integer> units;
	private final Dimension dimension;
	private final List<UnitSystem> preferredSystems = new ArrayList<>();
	private final double siValue;

	public CompoundUnit(Map<Unit, Integer> units, String name, String symbol)
	{
		this.name = name;
		this.symbol = symbol;
		this.units = new TreeMap<>(units);
		Dimension tempDim = NullDimension.INSTANCE;
		double tempSiValue = 1;
		for (Map.Entry<Unit, Integer> entry : units.entrySet())
		{
			if (entry.getValue() > 0)
				for (int i = 0; i < entry.getValue(); i++)
					tempDim = Dimension.multiply(tempDim, entry.getKey().getDimension());
			else
				for (int i = 0; i > entry.getValue(); i--)
					tempDim = Dimension.divide(tempDim, entry.getKey().getDimension());
			tempSiValue *= Math.pow(entry.getKey().asSi(), entry.getValue());
		}
		this.dimension = tempDim;
		this.siValue = tempSiValue;

	}

	public CompoundUnit(Map<Unit, Integer> units)
	{
		this(units, makeName(units), makeSymbol(units));
	}

	private static String makeName(Map<Unit, Integer> units)
	{
		TreeMap<String, Integer> stringMap = new TreeMap<>();
		for (Map.Entry<Unit, Integer> entry : units.entrySet())
			stringMap.put(entry.getKey().getFullName(), entry.getValue());
		return Dimensions.combine(stringMap);
	}

	private static String makeSymbol(Map<Unit, Integer> units)
	{
		TreeMap<String, Integer> stringMap = new TreeMap<>();
		for (Map.Entry<Unit, Integer> entry : units.entrySet())
			stringMap.put(entry.getKey().getSymbol(), entry.getValue());
		return Dimensions.combine(stringMap);
	}

	@Override
	public String getSymbol()
	{
		return this.symbol;
	}

	@Override
	public String getFullName()
	{
		return this.name;
	}

	@Override
	public Dimension getDimension()
	{
		return this.dimension;
	}

	@Override
	public List<UnitSystem> getPreferredSystems()
	{
		return this.preferredSystems;
	}

	@Override
	public double asSi()
	{
		return this.siValue;
	}

	@Override
	public void addSystem(UnitSystem system)
	{
		if (!this.preferredSystems.contains(system))
			this.preferredSystems.add(system);
	}

	@Override
	public Map<BaseUnit, Integer> asBaseUnits()
	{
		return null;
	}
}
