package unit;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Dimensions
{

	public static final BaseDimension LENGTH = new BaseDimension("Length");
	public static final BaseDimension MASS = new BaseDimension("Mass");
	public static final BaseDimension TIME = new BaseDimension("Time");
	public static final BaseDimension CURRENT = new BaseDimension("Current");
	public static final BaseDimension TEMPERATURE = new BaseDimension("Temperature");
	public static final BaseDimension AMOUNT = new BaseDimension("Amount");
	public static final BaseDimension LUMINOUS_INTENSITY = new BaseDimension("Luminous Intensity");

	public static final CompoundDimension SPEED;
	public static final CompoundDimension ACCELERATION;
	public static final CompoundDimension JERK;
	public static final CompoundDimension FORCE;
	public static final CompoundDimension ENERGY;
	public static final CompoundDimension MOMENTUM;
	public static final CompoundDimension CHARGE;

	static
	{
		TreeMap<BaseDimension, Integer> dims = new TreeMap<>();
		dims.put(LENGTH, 1);
		dims.put(TIME, -1);
		SPEED = new CompoundDimension(dims);

		dims.put(TIME, -2);
		ACCELERATION = new CompoundDimension(dims);

		dims.put(TIME, -3);
		JERK = new CompoundDimension(dims);

		dims.put(TIME, -2);
		dims.put(MASS, 1);
		FORCE = new CompoundDimension(dims);

		dims.put(LENGTH, 2);
		ENERGY = new CompoundDimension(dims);

		dims.put(LENGTH, 1);
		dims.put(TIME, -1);
		MOMENTUM = new CompoundDimension(dims);

		dims = new TreeMap<>();
		dims.put(Dimensions.CURRENT, 1);
		dims.put(Dimensions.TIME, 1);
		CHARGE = new CompoundDimension(dims);

	}

	protected static String combine(Map<String, Integer> strings)
	{
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		for (Map.Entry<String, Integer> pair : strings.entrySet())
			if (pair.getValue() > 0)
			{
				if (first)
					first = false;
				else
					builder.append('*');
				builder.append(pair.getKey());
				if (pair.getValue() > 1)
				{
					builder.append('^');
					builder.append(pair.getValue());
				}
			}
		if (first)
			builder.append('1');
		first = true;
		for (Map.Entry<String, Integer> pair : strings.entrySet())
			if (pair.getValue() < 0)
			{
				if (first)
					builder.append('/');
				else
					builder.append('*');
				builder.append(pair.getKey());
				if (pair.getValue() < -1)
				{
					builder.append('^');
					builder.append(-pair.getValue());
				}
			}
		return builder.toString();
	}
	protected static Dimension makeDimension(TreeMap<BaseDimension, Integer> dims)
	{

		Set<Map.Entry<BaseDimension, Integer>> entries = dims.entrySet();
		if (entries.size() == 1)
			for (Map.Entry<BaseDimension, Integer> entry : entries)
				if (entry.getValue() == 1)
					return entry.getKey();
		if (entries.size() == 0)
			return new NullDimension();
		return new CompoundDimension(dims);
	}

	public static Dimension multiply(Dimension d1, Dimension d2)
	{

		TreeMap<BaseDimension, Integer> dimensions = new TreeMap<>();
		for (Map.Entry<BaseDimension, Integer> pair : d1.getTypes().entrySet())
			dimensions.put(pair.getKey(), pair.getValue());
		for (Map.Entry<BaseDimension, Integer> pair : d2.getTypes().entrySet())
			if (dimensions.containsKey(pair.getKey()))
				dimensions.put(pair.getKey(), dimensions.get(pair.getKey()) + pair.getValue());
			else
				dimensions.put(pair.getKey(), pair.getValue());
		Set<BaseDimension> keys = new TreeSet<>(dimensions.keySet());
		for (BaseDimension dim : keys)
			if (dimensions.get(dim) == 0)
				dimensions.remove(dim);
		return makeDimension(dimensions);
	}

	public static Dimension divide(Dimension dim1, Dimension dim2)
	{
		System.out.println(dim1);
		System.out.println(dim2);
		System.out.println(dim2.invert());
		return Dimensions.multiply(dim1, dim2.invert());
	}
}
