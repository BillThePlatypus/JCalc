package number;

import java.util.Map;
import java.util.TreeMap;

public class Dimensions {

    public static final BaseDimension LENGTH=new BaseDimension("Length");
    public static final BaseDimension MASS=new BaseDimension("Mass");
    public static final BaseDimension TIME=new BaseDimension("Time");
    public static final BaseDimension CURRENT=new BaseDimension("Current");
    public static final BaseDimension TEMPERATURE=new BaseDimension("Temperature");
    public static final BaseDimension AMOUNT=new BaseDimension("Amount");
    public static final BaseDimension LUMINOUS_INTENSITY=new BaseDimension("Luminous Intensity");

    public static final CompoundDimension SPEED;
    public static final CompoundDimension ACCELERATION;
    public static final CompoundDimension JERK;
    public static final CompoundDimension FORCE;
    public static final CompoundDimension ENERGY;
    public static final CompoundDimension MOMENTUM;
    public static final CompoundDimension CHARGE;

    static{
        TreeMap<BaseDimension,Integer> dims=new TreeMap<>();
        dims.put(LENGTH,1);
        dims.put(TIME,-1);
        SPEED=new CompoundDimension(dims);

        dims.put(TIME,-2);
        ACCELERATION=new CompoundDimension(dims);

        dims.put(TIME,-3);
        JERK=new CompoundDimension(dims);

        dims.put(TIME,-2);
        dims.put(MASS,1);
        FORCE=new CompoundDimension(dims);

        dims.put(LENGTH,2);
        ENERGY=new CompoundDimension(dims);

        dims.put(LENGTH,1);
        dims.put(TIME,-1);
        MOMENTUM=new CompoundDimension(dims);

        dims=new TreeMap<>();
        dims.put(Dimensions.CURRENT,1);
        dims.put(Dimensions.TIME,1);
        CHARGE=new CompoundDimension(dims);

    }
    protected static String combine(Map<String,Integer> strings)
    {
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, Integer> pair : strings.entrySet())
            if (pair.getValue() > 0) {
                if (first)
                    first = false;
                else
                    builder.append('*');
                builder.append(pair.getKey());
                if(pair.getValue()>1) {
                    builder.append('^');
                    builder.append(pair.getValue());
                }
            }
        if(first)
            builder.append('1');
        first=true;
        for(Map.Entry<String,Integer> pair:strings.entrySet())
            if(pair.getValue()<0){
                if(first)
                    builder.append('/');
                else
                    builder.append('*');
                builder.append(pair.getKey());
                if(pair.getValue()<-1){
                    builder.append('^');
                    builder.append(-pair.getValue());
                }
            }
        return builder.toString();
    }
}
