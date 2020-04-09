package complex;
public interface Operation {

    public static ComplexNumber complexAdd(ComplexNumber a, ComplexNumber b)
    {
        ComplexNumber res = new ComplexNumber();

        res.setRe(a.getRe() + b.getRe());
        res.setIm(a.getIm() + b.getIm());

        return res;
    }

    public static ComplexNumber complexSub(ComplexNumber a, ComplexNumber b)
    {
        ComplexNumber res = new ComplexNumber();

        res.setRe(a.getRe() - b.getRe());
        res.setIm(a.getIm() - b.getIm());

        return res;
    }

    public static ComplexNumber complexMul(ComplexNumber a, ComplexNumber b)
    {
        ComplexNumber res = new ComplexNumber();

        res.setRe(a.getRe()*b.getRe() - a.getIm()*b.getIm());
        res.setIm(a.getIm()*b.getRe() + a.getRe()*b.getIm());

        return res;
    }

    public static ComplexNumber complexMulRe(ComplexNumber a, double re)
    {
        ComplexNumber res = new ComplexNumber(a.getRe()*re, a.getIm()*re);
        return res;
    }

    public static ComplexNumber complexDiv(ComplexNumber a, ComplexNumber b)
    {
        if((b.getRe() == 0) && (b.getIm() == 0))
        {
            throw new IllegalArgumentException("Both re and im parts of divisor is zero");
        }

        ComplexNumber res = new ComplexNumber();
        double divisor = b.getRe()*b.getRe() + b.getIm()*b.getIm();

        res.setRe((a.getRe()*b.getRe() + a.getIm()*b.getIm()) / divisor);
        res.setIm((a.getIm()*b.getRe() - a.getRe()*b.getIm()) / divisor);

        return res;
    }
}