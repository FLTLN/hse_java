import complex.ComplexNumber;
public class Matrix 
{
    public Matrix()
    {
        ncol = nrov = 1;
        data = new ComplexNumber[ncol*nrov];
        data[0] = new ComplexNumber();
    }

    public Matrix(ComplexNumber[] data, int ncol, int nrov)
    {
        if(data.length < ncol*nrov)
        {
            throw new IllegalArgumentException("Not enough items for matrix in \"data\"");
        }
        else
        {
            this.ncol = ncol;
            this.nrov = nrov;

            this.data = new ComplexNumber[ncol*nrov];

            for(int i = 0; i < ncol*nrov;i++)
            {
                this.data[i] = new ComplexNumber(data[i]);
            }
        }
    }

    public Matrix(int ncol, int nrov)
    {
        this.ncol = ncol;
        this.nrov = nrov;

        this.data = new ComplexNumber[ncol*nrov];

        for(int i = 0; i < ncol*nrov;i++)
        {
            this.data[i] = new ComplexNumber();
        }
    }

    public String toString()
    {
        String res = "";

        for(int i = 0; i < nrov;i++)
        {
            for(int j = 0; j < ncol;j++)
            {
                res = res + this.data[j + i * this.ncol] + " ";
            }
            res = res + "\n";
        }

        return res;
    }

    public ComplexNumber getElement(int ncol, int nrov)
    {
        if(this.ncol*this.nrov < ncol*nrov)
        {
            throw new IllegalArgumentException("Item out of range");
        }

        return new ComplexNumber(this.data[ncol + nrov * this.ncol]);
    }

    public void setElement(int ncol, int nrov, ComplexNumber element)
    {
        if(this.ncol*this.nrov < ncol*nrov)
        {
            throw new IllegalArgumentException("Item out of range");
        }

        this.data[ncol + nrov * this.ncol] = new ComplexNumber(element);
    }

    public int detNcol() {return this.ncol; }
    public int detNrov() {return this.nrov; }

    private int ncol, nrov;
    private ComplexNumber[] data;
}