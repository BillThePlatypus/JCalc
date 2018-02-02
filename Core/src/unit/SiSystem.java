package unit;


import java.util.TreeMap;

public class SiSystem extends UnitSystem
{
	public static final BaseUnit KILOGRAM=new BaseUnit(Dimensions.MASS,1,"kilogram","kg");
	public static final BaseUnit GRAM=new BaseUnit(Dimensions.MASS,.001,"gram","g");
	public static final BaseUnit METER=new BaseUnit(Dimensions.LENGTH,1,"meter","m");
	public static final BaseUnit SECOND=new BaseUnit(Dimensions.TIME,1,"second","s");
	public static final BaseUnit AMPERE=new BaseUnit(Dimensions.CURRENT,1,"Amp","A");
	public static final CompoundUnit COULOMB;
	public static final CompoundUnit JOULE;
	public static final CompoundUnit WATT;
	public static final CompoundUnit DIOPTER;
	public static final CompoundUnit HERTZ;
	public static final CompoundUnit NEWTON;

	public static final SiSystem INSTANCE=new SiSystem();

	static{
		TreeMap<Unit,Integer> tempMap=new TreeMap<>();
		tempMap.put(SECOND,-1);
		HERTZ=new CompoundUnit(tempMap,"HERTZ","hz");
		tempMap.remove(SECOND);
		tempMap.put(METER,-1);
		DIOPTER=new CompoundUnit(tempMap);
		tempMap.put(METER,1);
		tempMap.put(KILOGRAM,1);
		tempMap.put(SECOND,-2);
		NEWTON=new CompoundUnit(tempMap);
		tempMap.put(METER,2);
		JOULE=new CompoundUnit(tempMap);
		tempMap.put(SECOND,-3);
		WATT=new CompoundUnit(tempMap);
		tempMap.clear();
		tempMap.put(SECOND,1);
		tempMap.put(AMPERE,1);
		COULOMB =new CompoundUnit(tempMap);
	}

	private SiSystem(){}s
	@Override
	public String getName()
	{
		return "SI";
	}

	@Override
	public Unit getUnit(Dimension dim, double siValue)
	{
		return null;
	}

	@Override
	public Unit getUnit(Dimension dim)
	{
		return null;
	}
}
