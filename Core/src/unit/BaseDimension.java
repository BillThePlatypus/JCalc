package unit;


import java.util.TreeMap;

public class BaseDimension extends Dimension
{
	private final String name;

	public BaseDimension(String name)
	{
		this.name = name;
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public TreeMap<BaseDimension, Integer> getTypes()
	{
		TreeMap<BaseDimension, Integer> ret = new TreeMap<>();
		ret.put(this, 1);
		return ret;
	}
}
