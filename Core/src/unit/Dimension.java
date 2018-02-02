package unit;


import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public abstract class Dimension implements Comparable<Dimension>
{


	@Override
	public String toString()
	{
		return this.getName() + " dimension";
	}

	public abstract String getName();

	public abstract TreeMap<BaseDimension, Integer> getTypes();

	@Override
	public int compareTo(Dimension other)
	{
		return this.getName().compareTo(other.getName());
	}

	@Override
	public boolean equals(Object o)
	{
		if (this.getClass().equals(o.getClass()))
		{
			Dimension other = (Dimension) o;
			return this.getName().equals(other.getName()) && this.getTypes().equals(other.getTypes());
		}
		return false;
	}

	public Dimension invert()
	{
		TreeMap<BaseDimension, Integer> dims = this.getTypes();
		for (BaseDimension dim : dims.keySet())
			dims.put(dim, -dims.get(dim));
		return Dimensions.makeDimension(dims);
	}

	//Is this perhaps too slow?
	//Not if getTypes is O(1)
	@Override
	public int hashCode()
	{
		int hash=this.getName().hashCode();
		hash|=this.getTypes().hashCode();
		return hash;
	}


}
