package number;


public class SiSystem extends UnitSystem {
    @Override
    public String getName() {
        return "SI";
    }

    @Override
    public Unit getUnit(Dimension dim, double siValue) {
        return null;
    }

    @Override
    public Unit getUnit(Dimension dim) {
        return null;
    }
}
