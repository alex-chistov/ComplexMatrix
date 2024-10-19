public class Main {
    public static void main(String[] args) {
        try {
            ComplexNumber c1 = new ComplexNumber(1, 2);
            ComplexNumber c2 = new ComplexNumber(3, -4);
            ComplexNumber c3 = new ComplexNumber(-2, 0.5);
            ComplexNumber c4 = new ComplexNumber(0, -1);

            ComplexNumber[][] dataA = {{c1, c2}, {c3, c4}};
            ComplexNumber[][] dataB = {{c4, c3}, {c2, c1}};

            ComplexMatrix matrixA = new ComplexMatrix(dataA);
            ComplexMatrix matrixB = new ComplexMatrix(dataB);

            System.out.println("Matrix A:");
            System.out.println(matrixA);

            System.out.println("Matrix B:");
            System.out.println(matrixB);

            //сложение
            ComplexMatrix addResult = matrixA.add(matrixB);
            System.out.println("A + B:");
            System.out.println(addResult);

            //вычитание
            ComplexMatrix subResult = matrixA.subtract(matrixB);
            System.out.println("A - B:");
            System.out.println(subResult);

            //умножение
            ComplexMatrix mulResult = matrixA.multiply(matrixB);
            System.out.println("A * B:");
            System.out.println(mulResult);

            // Умножение на скаляр
            ComplexNumber scalar = new ComplexNumber(2, -1);
            ComplexMatrix scalarMulResult = matrixA.multiplyByNumber(scalar);
            System.out.println("Matrix A * (2 - i):");
            System.out.println(scalarMulResult);

            //транспонирование
            ComplexMatrix transResult = matrixA.transpose();
            System.out.println("Transpose of A:");
            System.out.println(transResult);

            //определитель матрицы A 2 на 2(вычисляет также для произвольного n)
            ComplexNumber detA = matrixA.determinant();
            System.out.println("Determinant of A:");
            System.out.println(detA);

            //обратная матрица
            ComplexMatrix invA = matrixA.inverse();
            System.out.println("Inverse of A:");
            System.out.println(invA);

            //деление A на B
            ComplexMatrix divResult = matrixA.divide(matrixB);
            System.out.println("A / B:");
            System.out.println(divResult);

            ComplexMatrix identity = matrixA.multiply(invA);
            System.out.println("A * invA (identity matrix):");
            System.out.println(identity);

        } catch (IllegalArgumentException e) {
            System.err.println("An error occurred with matrix dimensions:");
            System.err.println(e.getMessage());
        } catch (ArithmeticException e) {
            System.err.println("An arithmetic error occurred:");
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred:");
            e.printStackTrace();
        }
    }
}
