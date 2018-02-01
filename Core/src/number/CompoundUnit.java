package number;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CompoundUnit extends Unit {
    private final String name;
    private final String symbol;
    private final TreeMap<Unit,Integer> units;
    public CompoundUnit(Map<Unit,Integer> units,String name, String symbol)
    {
        this.name=name;
        this.symbol=symbol;
        this.units=new TreeMap<>(units);
    }
    public CompoundUnit(Map<Unit,Integer> units)
    {
        this(units,makeName(units),makeSymbol(units));
    }
    private static String makeName(Map<Unit,Integer> units)
    {
        TreeMap<String,Integer> stringMap=new TreeMap<>();
        for(Map.Entry<Unit,Integer> entry:units.entrySet())
            stringMap.put(entry.getKey().getFullName(),entry.getValue());
        return Dimensions.combine(stringMap);
    }
    private static String makeSymbol(Map<Unit,Integer> units)
    {
        TreeMap<String,Integer> stringMap=new TreeMap<>();
        for(Map.Entry<Unit,Integer> entry:units.entrySet())
            stringMap.put(entry.getKey().getSymbol(),entry.getValue());
        return Dimensions.combine(stringMap);
    }intelli
    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public String getFullName() {
        return this.namel
    }

    @Override
    public Dimension getDimension() {
        return null;
    }

    @Override
    public List<UnitSystem> getPreferredSystems() {
        return null;
    }

    @Override
    public double asSi() {
        return 0;
    }

    @Override
    public void addSystem(UnitSystem system) {

    }

    @Override
    public Map<BaseUnit, Integer> asBaseUnits() {
        return null;
    }
}
