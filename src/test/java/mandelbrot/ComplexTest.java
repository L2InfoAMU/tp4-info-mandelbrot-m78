package mandelbrot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComplexTest {
    private final Complex onePlusI = new Complex(1,1);
    private final Complex minusI = new Complex(0,-1);
    private final Complex minusOne = new Complex(-1,0);
    private final Complex oneMinusI = new Complex(1, -1);
    private final Complex twoI = new Complex(0,2);
    private final Complex two = new Complex(2,0);
    private final double real = -12;
    private final double imaginary = 10;


    @Test
    void testConstructor(){
        assertEquals(0., twoI.real, Helpers.EPSILON);
        assertEquals(2., twoI.imaginary, Helpers.EPSILON);
        assertEquals(1., oneMinusI.real, Helpers.EPSILON);
        assertEquals(-1., oneMinusI.imaginary, Helpers.EPSILON);
        assertEquals(2., two.real, Helpers.EPSILON);
        assertEquals(0., two.imaginary, Helpers.EPSILON);
    }

    @Test
    void testGetReal(){
        assertEquals(2., two.getReal(), Helpers.EPSILON);
        assertEquals(1., oneMinusI.getReal(), Helpers.EPSILON);
        assertEquals(-1., new Complex(-1,1).getReal(), Helpers.EPSILON);
        assertEquals(real, new Complex(real, imaginary).getReal(), Helpers.EPSILON);
    }

    @Test
    void testGetImaginary(){
        assertEquals(2., twoI.getImaginary(), Helpers.EPSILON);
        assertEquals(1., new Complex(-1, 1).getImaginary(), Helpers.EPSILON);
        assertEquals(-1., oneMinusI.getImaginary(), Helpers.EPSILON);
        assertEquals(imaginary, new Complex(real, imaginary).getImaginary(), Helpers.EPSILON);
    }

    @Test
    void testOne(){
        assertEquals(1., Complex.ONE.getReal());
        assertEquals(0., Complex.ONE.getImaginary());
    }

    @Test
    void testI(){
        assertEquals(0, Complex.I.getReal());
        assertEquals(1, Complex.I.getImaginary());
    }

    @Test
    void testZero(){
        assertEquals(0, Complex.ZERO.getReal());
        assertEquals(0, Complex.ZERO.getImaginary());
    }

    @Test
    void testNegate(){
        assertEquals(minusOne, Complex.ONE.negate());
        assertEquals(Complex.I, minusI.negate());
        assertEquals(new Complex(-1, 1), oneMinusI.negate());
        assertEquals(new Complex(real, imaginary), new Complex(-real,-imaginary).negate());
    }

    @Test
    void testReciprocal(){
        assertEquals(Complex.ONE, Complex.ONE.reciprocal());
        assertEquals(Complex.I, minusI.reciprocal());
        assertEquals(new Complex(0.5,0), two.reciprocal());
        assertEquals(new Complex(0.5,0.5), oneMinusI.reciprocal());
    }

    @Test
    void testReciprocalOfZero(){
        assertThrows(ArithmeticException.class, ()->Complex.ZERO.reciprocal());
    }

    @Test
    void testSubstract(){
        assertEquals(minusOne, Complex.ZERO.subtract(Complex.ONE));
        assertEquals(oneMinusI, Complex.ONE.subtract(Complex.I));
        assertEquals(new Complex(real-1,imaginary-1),
                new Complex(real, imaginary).subtract(onePlusI));
    }

    @Test
    void testDivide(){
        assertEquals(onePlusI, onePlusI.divide(Complex.ONE));
        assertEquals(new Complex(0.5, 0), Complex.ONE.divide(two));
        assertEquals(minusI,oneMinusI.divide(onePlusI));
    }

    @Test
    void testDivideByZero(){
        assertThrows(ArithmeticException.class, ()->Complex.ONE.divide(Complex.ZERO));
    }

    @Test
    void testConjugate(){
        assertEquals(Complex.ZERO, Complex.ZERO.conjugate());
        assertEquals(Complex.ONE, Complex.ONE.conjugate());
        assertEquals(onePlusI, oneMinusI.conjugate());
        assertEquals(new Complex(real, -imaginary), new Complex(real, imaginary).conjugate());
    }

    @Test
    void testRotation(){
        assertEquals(Complex.I, Complex.rotation(Math.PI/2));
        assertEquals(minusI, Complex.rotation(-Math.PI/2));
        assertEquals(Complex.ONE, Complex.rotation(0));
        assertEquals(new Complex(Math.sqrt(2)/2., Math.sqrt(2)/2.),
                Complex.rotation(Math.PI/4));
        assertEquals(new Complex(1./2., Math.sqrt(3)/2.),
                Complex.rotation(Math.PI/3));
    }

    @Test
    void testToString(){
        assertEquals("Complex{real=1.0, imaginary=-1.0}", oneMinusI.toString());
        assertEquals("Complex{real="+real+", imaginary="+imaginary+"}", new Complex(real, imaginary).toString());
    }

    @Test
    void testHashCode() {
        Complex c1 = new Complex(real, imaginary);
        Complex c2 = new Complex(real, imaginary);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void testReal() {
        assertEquals(minusOne, Complex.real(minusOne.getReal()));
        assertEquals(two, Complex.real(two.getReal()));
        assertEquals(new Complex(real,0) , Complex.real(real));
    }

    @Test
    void testAdd() {
        assertEquals(new Complex(1, 0), onePlusI.add(minusI));
        assertEquals(new Complex(3, 1), onePlusI.add(two));
        assertEquals(new Complex(0, -1), oneMinusI.add(minusOne));
    }

    @Test
    void testSubtract() {
        assertEquals(new Complex(1, 2), onePlusI.subtract(minusI));
        assertEquals(new Complex(-1, 1), onePlusI.subtract(two));
        assertEquals(new Complex(2, -1), oneMinusI.subtract(minusOne));
    }

    @Test
    void testMultiply() {
        assertEquals(new Complex(1, -1),onePlusI.multiply(minusI));
        assertEquals(new Complex(2, 2 ),onePlusI.multiply(two));
        assertEquals(new Complex(-1 ,1),oneMinusI.multiply(minusOne));
    }

    @Test
    void testSquaredModulus() {
        assertEquals(2, onePlusI.squaredModulus());
        assertEquals(1, minusI.squaredModulus());
        assertEquals(1, minusOne.squaredModulus());
        assertEquals(2, oneMinusI.squaredModulus());
        assertEquals(4, twoI.squaredModulus());
        assertEquals(4, two.squaredModulus());
    }

    @Test
    void testModulus() {
        assertEquals(Math.sqrt(2), onePlusI.modulus());
        assertEquals(1, minusI.modulus());
        assertEquals(1, minusOne.modulus());
        assertEquals(Math.sqrt(2), oneMinusI.modulus());
        assertEquals(2, twoI.modulus());
        assertEquals(2, two.modulus());
    }

    @Test
    void testScale() {
        assertEquals(new Complex(-3,-3), onePlusI.scale(-3));
        assertEquals(new Complex(0,-12), minusI.scale(12));
        assertEquals(new Complex(-27,0), minusOne.scale(27));
        assertEquals(new Complex(-6,6), oneMinusI.scale(-6));
        assertEquals(new Complex(0,60), twoI.scale(30));
        assertEquals(new Complex(24.6,0), two.scale(12.3));
    }

    @Test
    void testEquals(){
        int x = 10;
        int y = -15;
        Complex c1 = new Complex(x,y);
        Complex c2 = new Complex(x,y);
        Complex c3 = new Complex(x, -y);
        Complex c4 = new Complex(-x, y);
        String s = new String();
        assertEquals(c1, c2);
        assertNotEquals(c1, c3);
        assertNotEquals(c2, c4);
        assertNotEquals(c1, s);
    }
}