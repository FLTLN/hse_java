import complex.ComplexNumber;
import complex.Operation;

public interface MatrixOperation 
{
    public static Matrix matrixAdd(Matrix a, Matrix b)
    {
        if((a.detNcol() != b.detNcol()) || (a.detNrov() != b.detNrov()))
        {
            throw new IllegalArgumentException("Matrix size does not match");
        }

        Matrix res = new Matrix(a.detNcol(),a.detNrov());

        for(int i = 0; i < a.detNcol();i++)
        {
            for(int j = 0; j < a.detNrov();j++)
            {
                res.setElement(i, j, Operation.complexAdd(a.getElement(i, j), b.getElement(i, j)));
            }
        }
        return res;
    }

    public static Matrix matrixMulToDouble(Matrix a, double b)
    {
        Matrix res = new Matrix(a.detNcol(),a.detNrov());

        for(int i = 0; i < a.detNcol();i++)
        {
            for(int j = 0; j < a.detNrov();j++)
            {
                res.setElement(i, j, Operation.complexMulRe(a.getElement(i, j), b));
            }
        }
        return res;
    }

    public static Matrix matrixMulToComplex(Matrix a, ComplexNumber b)
    {
        Matrix res = new Matrix(a.detNcol(),a.detNrov());

        for(int i = 0; i < a.detNcol();i++)
        {
            for(int j = 0; j < a.detNrov();j++)
            {
                res.setElement(i, j, Operation.complexMul(a.getElement(i, j), b));
            }
        }
        return res;
    }

    public static Matrix matrixMul(Matrix a, Matrix b)
    {
        if(a.detNcol() != b.detNrov())
        {
            throw new IllegalArgumentException("Matrix size does not match");
        }

        Matrix res = new Matrix(b.detNcol(),a.detNrov());

        int m = a.detNrov();
        int n = b.detNcol();
        int o = b.detNrov();

        for (int i = 0; i < m; i++) 
        {
            for (int j = 0; j < n; j++) 
            {
                ComplexNumber temp = new ComplexNumber();

                for (int k = 0; k < o; k++) {

                    temp = Operation.complexAdd(temp, Operation.complexMul(a.getElement(k, i), b.getElement(j, k)));
                }

                res.setElement(i, j, temp);                
            }
        }

        return res;
    }
}