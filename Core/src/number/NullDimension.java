package number;


import java.util.TreeMap;

public class NullDimension extends Dimension {

    @Override
    public String getName() {
        return "";
    }

    @Override
    public TreeMap<BaseDimension, Integer> getTypes() {
        return new TreeMap<>();
    }
}
