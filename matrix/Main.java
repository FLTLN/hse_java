import complex.ComplexNumber;
public class Main 
{   
    public static void main(final String args[])
    {
        System.out.print("Complexxxxxxxxxxxxxx\n");
        ComplexNumber myNumber1 = new ComplexNumber(-1, 0);
        ComplexNumber myNumber2 = new ComplexNumber(0, 0);
        ComplexNumber myNumber3 = new ComplexNumber(1, 0);
        ComplexNumber myNumber4 = new ComplexNumber(2, 0);
        ComplexNumber myNumber5 = new ComplexNumber(3, 0);

        ComplexNumber complexArray1[] = {myNumber3, myNumber4, myNumber2, myNumber5, myNumber3, myNumber1};
        ComplexNumber complexArray2[] = {myNumber3, myNumber4, myNumber5};

        Matrix matrix1 = new Matrix(complexArray1, 3, 2);
        Matrix matrix2 = new Matrix(complexArray2, 1, 3);

        System.out.println(matrix1);
        System.out.println(matrix2);

        Matrix matrix_sum = MatrixOperation.matrixMul(matrix1, matrix2);
        System.out.println(matrix_sum);
    }    
}
