package number;

import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class DimensionTest
{

	@Test
	void testEquals()
	{
		//Testing true for base dimensions
		assertTrue(Dimensions.LENGTH.equals(Dimensions.LENGTH));
		assertTrue(Dimensions.TIME.equals(Dimensions.TIME));
		assertTrue(Dimensions.CURRENT.equals(Dimensions.CURRENT));
		assertTrue(Dimensions.LUMINOUS_INTENSITY.equals(Dimensions.LUMINOUS_INTENSITY));

		//Testing false for base dimensions
		assertFalse(Dimensions.LENGTH.equals(Dimensions.TIME));
		assertFalse(Dimensions.TIME.equals(Dimensions.LENGTH));
		assertFalse(Dimensions.CURRENT.equals(Dimensions.MASS));
		assertFalse(Dimensions.MASS.equals(Dimensions.CURRENT));
		assertFalse(Dimensions.AMOUNT.equals(Dimensions.LENGTH));
		assertFalse(Dimensions.LENGTH.equals(Dimensions.AMOUNT));

		//Testing true for compound dimensions
		assertTrue(Dimensions.ACCELERATION.equals(Dimensions.ACCELERATION));
		assertTrue(Dimensions.ENERGY.equals(Dimensions.ENERGY));
		assertTrue(Dimensions.FORCE.equals(Dimensions.FORCE));

		//Testing false for compound dimensions
		assertFalse(Dimensions.ACCELERATION.equals(Dimensions.ENERGY));
		assertFalse(Dimensions.MOMENTUM.equals(Dimensions.FORCE));
		assertFalse(Dimensions.SPEED.equals(Dimensions.CHARGE));

		//Testing false between compound and base dimensions
		assertFalse(Dimensions.SPEED.equals(Dimensions.LENGTH));
		assertFalse(Dimensions.LENGTH.equals(Dimensions.SPEED));
		assertFalse(Dimensions.CHARGE.equals(Dimensions.CURRENT));
		assertFalse(Dimensions.CURRENT.equals(Dimensions.CHARGE));
		assertFalse(Dimensions.CURRENT.equals(Dimensions.ENERGY));
		assertFalse(Dimensions.ENERGY.equals(Dimensions.CURRENT));

		//Testing NullDimensions
		NullDimension nullD = new NullDimension();
		assertTrue(nullD.equals(new NullDimension()));
		assertTrue(nullD.equals(nullD));

		assertFalse(nullD.equals(Dimensions.CURRENT));
		assertFalse(Dimensions.CURRENT.equals(nullD));
		assertFalse(nullD.equals(Dimensions.TIME));
		assertFalse(Dimensions.TIME.equals(nullD));

		assertFalse(nullD.equals(Dimensions.CHARGE));
		assertFalse(Dimensions.CHARGE.equals(nullD));
		assertFalse(nullD.equals(Dimensions.FORCE));
		assertFalse(Dimensions.FORCE.equals(nullD));


	}

	@Test
	void testMultiply()
	{
		Dimension inverseTime = Dimensions.TIME.invert();

		assertEquals(Dimensions.SPEED, Dimension.multiply(inverseTime, Dimensions.LENGTH));
		assertEquals(Dimensions.SPEED, Dimension.multiply(Dimensions.LENGTH, inverseTime));

		assertEquals(Dimensions.ACCELERATION, Dimension.multiply(Dimensions.SPEED, inverseTime));
		assertEquals(Dimensions.ACCELERATION, Dimension.multiply(inverseTime, Dimensions.SPEED));

		assertEquals(Dimensions.ENERGY, Dimension.multiply(Dimensions.FORCE, Dimensions.LENGTH));

		assertEquals(Dimensions.MOMENTUM, Dimension.multiply(Dimensions.MASS, Dimensions.SPEED));

		assertEquals(Dimensions.FORCE, Dimension.multiply(Dimensions.MASS, Dimensions.ACCELERATION));
	}

	@Test
	void testInvert()
	{
		TreeMap<BaseDimension, Integer> dims = new TreeMap<>();
		dims.put(Dimensions.TIME, -1);
		CompoundDimension inverseTime = new CompoundDimension(dims);
		assertEquals(inverseTime, Dimensions.TIME.invert());

		dims = new TreeMap<>();
		dims.put(Dimensions.LENGTH, -1);
		CompoundDimension inverseLength = new CompoundDimension(dims);
		assertEquals(inverseLength, Dimensions.LENGTH.invert());

		dims.put(Dimensions.TIME, 1);
		CompoundDimension inverseSpeed = new CompoundDimension(dims);
		assertEquals(inverseSpeed, Dimensions.SPEED.invert());
	}

	@Test
	void testDivide()
	{
		assertEquals(Dimensions.SPEED, Dimension.divide(Dimensions.ACCELERATION, Dimensions.TIME.invert()));
		assertEquals(Dimensions.TIME.invert(), Dimension.divide(Dimensions.ACCELERATION, Dimensions.SPEED));
		assertEquals(Dimensions.TIME, Dimension.divide(Dimensions.SPEED, Dimensions.ACCELERATION));
		assertEquals(Dimensions.MASS, Dimension.divide(Dimensions.FORCE, Dimensions.ACCELERATION));
		assertEquals(Dimensions.MASS.invert(), Dimension.divide(Dimensions.ACCELERATION, Dimensions.FORCE));

		assertEquals(new NullDimension(), Dimension.divide(Dimensions.MASS, Dimensions.MASS));
		assertEquals(new NullDimension(), Dimension.divide(Dimensions.ACCELERATION, Dimensions.ACCELERATION));

	}
}