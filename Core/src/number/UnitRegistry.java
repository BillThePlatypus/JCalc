package number;


import java.util.TreeMap;

public class UnitRegistry
{
	private static UnitRegistry instance;
	private TreeMap<String, Unit> unitsBySymbol = new TreeMap<>();
	private TreeMap<String, Unit> unitsByName = new TreeMap<>();

	private UnitRegistry()
	{

	}

	public static UnitRegistry instance()
	{
		return instance;
	}

	public void registerUnit(Unit unit, String symbol, String name)
	{
		this.unitsByName.put(name, unit);
		this.unitsBySymbol.put(symbol, unit);
	}

	public Unit getUnitByName(String name)
	{
		return this.unitsByName.get(name);
	}

	public Unit getUnitBySymbol(String symbol)
	{
		return this.unitsBySymbol.get(symbol);
	}

	public Unit getUnit(String nameOrSymbol)
	{
		Unit ret = this.getUnitByName(nameOrSymbol);
		if (ret == null)
			ret = this.getUnitBySymbol(nameOrSymbol);
		return ret;
	}
}
