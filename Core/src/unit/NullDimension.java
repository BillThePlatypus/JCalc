package unit;


import java.util.TreeMap;

public class NullDimension extends Dimension
{
	public static final NullDimension INSTANCE = new NullDimension();

	private NullDimension(){}
	@Override
	public String getName()
	{
		return "";
	}

	@Override
	public TreeMap<BaseDimension, Integer> getTypes()
	{
		return new TreeMap<>();
	}
}
