package number;


import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public abstract class Dimension implements Comparable<Dimension> {
    @Override
    public String toString() {
        return this.getName() + " dimension";
    }

    public abstract String getName();

    public abstract TreeMap<BaseDimension, Integer> getTypes();

    @Override
    public int compareTo(Dimension other) {
        return this.getName().compareTo(other.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Dimension) {
            Dimension other = (Dimension) o;
            return this.getTypes().equals(other.getTypes());
        }
        return false;
    }

    private static Dimension makeDimension(TreeMap<BaseDimension, Integer> dims) {

        Set<Map.Entry<BaseDimension, Integer>> entries = dims.entrySet();
        if (entries.size() == 1)
            for (Map.Entry<BaseDimension, Integer> entry : entries)
                if (entry.getValue() == 1)
                    return entry.getKey();
                else if (entries.size() == 0)
                    return new NullDimension();
        return new CompoundDimension(dims);
    }

    public static Dimension multiply(Dimension d1, Dimension d2) {

        TreeMap<BaseDimension, Integer> dimensions = new TreeMap<>();
        for (Map.Entry<BaseDimension, Integer> pair : d1.getTypes().entrySet())
            dimensions.put(pair.getKey(), pair.getValue());
        for (Map.Entry<BaseDimension, Integer> pair : d2.getTypes().entrySet())
            if (dimensions.containsKey(pair.getKey()))
                dimensions.put(pair.getKey(), dimensions.get(pair.getKey()) + pair.getValue());
            else
                dimensions.put(pair.getKey(), pair.getValue());
        Set<BaseDimension> keys=new TreeSet<>(dimensions.keySet());
        for (BaseDimension dim : keys)
            if (dimensions.get(dim) == 0)
                dimensions.remove(dim);
        return makeDimension(dimensions);
    }

    public Dimension invert() {
        TreeMap<BaseDimension, Integer> dims = this.getTypes();
        for (BaseDimension dim : dims.keySet())
            dims.put(dim, -dims.get(dim));
        return makeDimension(dims);
    }

    public static Dimension divide(Dimension dim1, Dimension dim2)
    {
        System.out.println(dim1);
        System.out.println(dim2);
        System.out.println(dim2.invert());
        return Dimension.multiply(dim1,dim2.invert());
    }


}
