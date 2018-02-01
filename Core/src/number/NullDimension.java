package number;


import java.util.TreeMap;

public class NullDimension extends Dimension
{
	public static final NullDimension INSTANCE = new NullDimension();

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
