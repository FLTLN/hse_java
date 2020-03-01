package complex;
public class ComplexNumber 
{
    public ComplexNumber(double re, double im)
    {
        this.re = re;
        this.im = im;
    }

    public ComplexNumber()
    {
        this.re = 0;
        this.im = 0;
    }

    public ComplexNumber(ComplexNumber a)
    {
        this.re = a.getRe();
        this.im = a.getIm();
    }

    public double getRe() {return re; }
    public double getIm() {return im; }

    public void setRe(double re) {this.re = re; }
    public void setIm(double im) {this.im = im; }

    public String toString()
    {
        return String.format("%8.4f + (%8.4f)i", re, im);
    }

    private double re, im;
}