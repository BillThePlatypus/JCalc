package unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NullUnit extends Unit{

	public static final NullUnit INSTANCE=new NullUnit();

	private NullUnit(){}
	@Override
	public String getSymbol() {
		return "";
	}

	@Override
	public String getFullName() {
		return "";
	}

	@Override
	public Dimension getDimension() {
		return NullDimension.INSTANCE;
	}

	@Override
	public List<UnitSystem> getPreferredSystems() {
		return new ArrayList<>();
	}

	@Override
	public double asSi() {
		return 0;
	}

	@Override
	public void addSystem(UnitSystem system) {

	}

	@Override
	public Map<Unit, Integer> getSubUnits() {
		return new TreeMap<>();
	}
}
