package unit;


import com.sun.istack.internal.localization.NullLocalizable;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class SiSystem extends UnitSystem
{
	public static final BaseUnit KILOGRAM=new BaseUnit(Dimensions.MASS,1,"kilogram","kg");
	public static final BaseUnit GRAM=new BaseUnit(Dimensions.MASS,.001,"gram","g");
	public static final BaseUnit METER=new BaseUnit(Dimensions.LENGTH,1,"meter","m");
	public static final BaseUnit SECOND=new BaseUnit(Dimensions.TIME,1,"second","s");
	public static final BaseUnit MINUTE=new BaseUnit(Dimensions.TIME,60,"minute","min");
	public static final BaseUnit HOUR=new BaseUnit(Dimensions.TIME,3600,"hour","hr");
	public static final BaseUnit AMPERE=new BaseUnit(Dimensions.CURRENT,1,"ampere","A");
	public static final BaseUnit RADIAN=new BaseUnit(NullDimension.INSTANCE,1,"radian","	rad");
	public static final BaseUnit DEGREE=new BaseUnit(NullDimension.INSTANCE,Math.PI/180,"degree","\u00B0");
	public static final CompoundUnit COULOMB;
	public static final CompoundUnit JOULE;
	public static final CompoundUnit WATT;
	public static final CompoundUnit DIOPTER;
	public static final CompoundUnit HERTZ;
	public static final CompoundUnit NEWTON;
	public static final CompoundUnit PASCAL;
	public static final CompoundUnit VOLT;
	public static final CompoundUnit OHM;
	public static final CompoundUnit SIEMENS;
	public static final CompoundUnit FARAD;
	public static final CompoundUnit WEBER;
	public static final CompoundUnit HENRY;
	public static final CompoundUnit TESLA;

	private static final TreeMap<Dimension,List<Unit>> preferredUnits=new TreeMap<>();

	public static final SiSystem INSTANCE=new SiSystem();

	static{
		TreeMap<Unit,Integer> tempMap=new TreeMap<>();

		tempMap.put(SECOND,-1);

		HERTZ=new CompoundUnit(tempMap,"hertz","hz");
		tempMap.remove(SECOND);
		tempMap.put(METER,-1);

		DIOPTER=new CompoundUnit(tempMap,"diopter","dpt");
		tempMap.put(METER,1);
		tempMap.put(KILOGRAM,1);
		tempMap.put(SECOND,-2);
		NEWTON=new CompoundUnit(tempMap);

		tempMap.put(METER,2);
		JOULE=new CompoundUnit(tempMap,"joule","J");

		tempMap.put(SECOND,-3);
		WATT=new CompoundUnit(tempMap,"watt","W");

		tempMap.put(SECOND,-2);
		tempMap.put(METER,-1);
		PASCAL=new CompoundUnit(tempMap,"pascal","Pa");

		tempMap.clear();
		tempMap.put(SECOND,1);
		tempMap.put(AMPERE,1);
		COULOMB =new CompoundUnit(tempMap,"coulomb","C");

		tempMap.clear();
		tempMap.put(KILOGRAM,1);
		tempMap.put(METER,2);
		tempMap.put(SECOND,-3);
		tempMap.put(AMPERE,-1);
		VOLT=new CompoundUnit(tempMap,"volt","V");

		tempMap.put(AMPERE,-2);
		OHM=new CompoundUnit(tempMap,"ohm","\u03A9");

		tempMap.put(SECOND,-2);
		HENRY=new CompoundUnit(tempMap,"henry","H");

		tempMap.put(AMPERE,-1);
		WEBER=new CompoundUnit(tempMap,"weber","Wb");

		tempMap.remove(METER);
		TESLA=new CompoundUnit(tempMap,"tesla","T");

		tempMap.clear();
		tempMap.put(KILOGRAM,-1);
		tempMap.put(METER,-2);
		tempMap.put(SECOND,4);
		tempMap.put(AMPERE,2);
		FARAD=new CompoundUnit(tempMap,"farad","F");

		tempMap.put(SECOND,3);
		SIEMENS=new CompoundUnit(tempMap,"siemens","S");







	}
	private static void addPreferredUnit(Unit u)
	{
		if(!SiSystem.preferredUnits.containsKey(u.getDimension()))
			SiSystem.preferredUnits.put(u.getDimension(),new LinkedList<>());
		SiSystem.preferredUnits.get(u.getDimension()).add(u);

	}

	private SiSystem(){}
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

	@Override
	public Unit rename(Unit u)
	{
		if(SiSystem.preferredUnits.containsKey(u.getDimension()))
			for(Unit possibility:SiSystem.preferredUnits.get(u.getDimension()))
				if(possibility.asSi()==u.asSi())
					return possibility;
		return u;
	}
}
