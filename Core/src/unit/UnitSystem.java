package unit;


public abstract class UnitSystem
{
	public abstract String getName();

	public Unit getUnit(Dimension dim, double siValue)
	{
		return this.getUnit(dim);
	}

	public abstract Unit getUnit(Dimension dim);
	public abstract Unit rename(Unit u);
}
