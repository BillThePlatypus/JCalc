package number;

import java.util.List;
import java.util.Map;

public abstract class Unit {
    public abstract String getSymbol();
    public String getSymbol(double quantity)
    {
        return this.getSymbol();
    }
    public abstract String getFullName();
    public String getFullName(double quantity)
    {
        return this.getFullName();
    }
    public abstract Dimension getDimension();
    public abstract List<UnitSystem> getPreferredSystems();
    public abstract double asSi();
    public abstract void addSystem(UnitSystem system);
    public abstract Map<BaseUnit,Integer> asBaseUnits();
}
