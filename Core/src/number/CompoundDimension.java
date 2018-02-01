package number;


import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CompoundDimension extends Dimension {
    private TreeMap<BaseDimension, Integer> dimensions;

    public CompoundDimension(TreeMap<BaseDimension, Integer> dimensions) {
        this.dimensions = new TreeMap<>(dimensions);
        Set<BaseDimension> keySet=this.dimensions.keySet();
        for(BaseDimension dim:keySet)
            if(this.dimensions.get(dim)==0)
                this.dimensions.remove(dim);
    }

    //Makes a new dimension as a product of two other dimensions
    public CompoundDimension(Dimension d1, Dimension d2) {
        TreeMap<BaseDimension,Integer> dims1=d1.getTypes();
        TreeMap<BaseDimension,Integer> dims2=d2.getTypes();
        this.dimensions=new TreeMap<>(dims1);
        for(Map.Entry<BaseDimension,Integer> entry:dims2.entrySet())
            if(this.dimensions.containsKey(entry.getKey()))
                this.dimensions.put(entry.getKey(),entry.getValue()+this.dimensions.get(entry.getKey()));
            else
                this.dimensions.put(entry.getKey(),entry.getValue());
        Set<BaseDimension> keySet=this.dimensions.keySet();
        for(BaseDimension dim:keySet)
            if(this.dimensions.get(dim)==0)
                this.dimensions.remove(dim);
    }

    @Override
    public String getName() {
        TreeMap<String,Integer> stringMap=new TreeMap<>();
        for(Map.Entry<BaseDimension,Integer> entry:this.dimensions.entrySet())
            stringMap.put(entry.getKey().getName(),entry.getValue());
        return Dimensions.combine(stringMap);
    }

    @Override
    public TreeMap<BaseDimension, Integer> getTypes() {
        return new TreeMap<>(this.dimensions);
    }
}
