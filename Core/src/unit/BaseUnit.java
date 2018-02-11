package unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BaseUnit extends Unit
{
	private final Dimension dimension;
	private final String name;
	private final String symbol;
	private final double si;
	private final List<UnitSystem> prefferedSystems = new ArrayList<>();

	public BaseUnit(Dimension dimension, double si, String name, String symbol)
	{
		this.dimension = dimension;
		this.name = name;
		this.symbol = symbol;
		this.si = si;
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
		return new ArrayList<>(this.prefferedSystems);
	}

	@Override
	public double asSi()
	{
		return this.si;
	}

	@Override
	public void addSystem(UnitSystem system)
	{
		if (!this.prefferedSystems.contains(system))
			this.prefferedSystems.add(system);
	}

	@Override
	public Map<Unit, Integer> getSubUnits()
	{
		Map<Unit, Integer> ret = new TreeMap<>();
		ret.put(this, 1);
		return ret;
	}
}
