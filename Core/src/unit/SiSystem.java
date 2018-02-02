package unit;


public class SiSystem extends UnitSystem
{
	public static BaseUnit KILOGRAM=new BaseUnit(Dimensions.MASS,1,"kilogram","kg");
	public static BaseUnit GRAM=new BaseUnit(Dimensions.MASS,.001,"gram","g");
	public static BaseUnit METER=new BaseUnit(Dimensions.LENGTH,1,"meter","m");
	public static BaseUnit SECOND=new BaseUnit(Dimensions.TIME,1,"second","s");
	public static BaseUnit AMPERE=new BaseUnit(Dimensions.CURRENT,1,"Amp","A");
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
