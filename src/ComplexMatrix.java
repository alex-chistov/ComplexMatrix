import java.util.Arrays;

public class ComplexMatrix {
    private ComplexNumber[][] matrix;
    private int rows;
    private int cols;

    public ComplexMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        matrix = new ComplexNumber[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                matrix[i][j] = new ComplexNumber();
    }

    public ComplexMatrix(ComplexNumber[][] matrix) {
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.matrix = new ComplexNumber[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                this.matrix[i][j] = new ComplexNumber(matrix[i][j]);
    }

    public int getRows() {return rows;}
    public int getCols() {return cols;}

    public ComplexNumber getElement(int row, int col) {return matrix[row][col];}
    public void setElement(int row, int col, ComplexNumber value) {matrix[row][col] = value;}
    //сложение
    public ComplexMatrix add(ComplexMatrix m) throws IllegalArgumentException {
        if (this.rows != m.rows || this.cols != m.cols) {
            throw new IllegalArgumentException("Matrices dimensions don't match for addition");
        }
        ComplexMatrix result = new ComplexMatrix(rows, cols);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result.matrix[i][j] = this.matrix[i][j].add(m.matrix[i][j]);
        return result;
    }
    //вычитание
    public ComplexMatrix subtract(ComplexMatrix m) throws IllegalArgumentException {
        if (this.rows != m.rows || this.cols != m.cols) {
            throw new IllegalArgumentException("Matrices dimensions don't match for subtraction");
        }
        ComplexMatrix result = new ComplexMatrix(rows, cols);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result.matrix[i][j] = this.matrix[i][j].subtract(m.matrix[i][j]);
        return result;
    }
    //умножение
    public ComplexMatrix multiply(ComplexMatrix m) throws IllegalArgumentException {
        if (this.cols != m.rows) {
            throw new IllegalArgumentException("Matrices dimensions don't match for multiplication");
        }
        ComplexMatrix result = new ComplexMatrix(this.rows, m.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < m.cols; j++) {
                ComplexNumber sum = new ComplexNumber();
                for (int k = 0; k < this.cols; k++) {
                    sum = sum.add(this.matrix[i][k].multiply(m.matrix[k][j]));
                }
                result.matrix[i][j] = sum;
            }
        }
        return result;
    }

    public ComplexMatrix multiplyByNumber(ComplexNumber num) {
        ComplexMatrix result = new ComplexMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.matrix[i][j] = this.matrix[i][j].multiply(num);
            }
        }
        return result;
    }

    //транспонирование
    public ComplexMatrix transpose() {
        ComplexMatrix result = new ComplexMatrix(cols, rows);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result.matrix[j][i] = new ComplexNumber(this.matrix[i][j]);
        return result;
    }

    public ComplexNumber determinant() throws IllegalArgumentException {
        if (rows != cols) {
            throw new IllegalArgumentException("Determinant is only defined for square matrices");
        }
        return detRec(this.matrix);
    }

    // рекурсивное вычисление определителя
    private ComplexNumber detRec(ComplexNumber[][] matrix) {
        int n = matrix.length;
        if (n == 1) {
            return matrix[0][0];
        }
        ComplexNumber det = new ComplexNumber();
        for (int col = 0; col < n; col++) {
            ComplexNumber sign = new ComplexNumber(((col % 2 == 0) ? 1 : -1), 0);
            ComplexNumber subDet = detRec(getSubMatrix(matrix, 0, col));
            det = det.add(sign.multiply(matrix[0][col]).multiply(subDet));
        }
        return det;
    }

    //подматрица для вычисления определителя путем разложения по первой строке
    private ComplexNumber[][] getSubMatrix(ComplexNumber[][] matrix, int kickRow, int kickCol) {
        int n = matrix.length;
        ComplexNumber[][] subMatrix = new ComplexNumber[n - 1][n - 1];
        int r = -1;
        for (int i = 0; i < n; i++) {
            if (i == kickRow)
                continue;
            r++;
            int c = -1;
            for (int j = 0; j < n; j++) {
                if (j == kickCol)
                    continue;
                c++;
                subMatrix[r][c] = matrix[i][j];
            }
        }
        return subMatrix;
    }

    //обртная матрица
    public ComplexMatrix inverse() throws IllegalArgumentException, ArithmeticException {
        ComplexNumber det = this.determinant();
        if (det.getRe() == 0 && det.getIm() == 0) {
            throw new ArithmeticException("Matrix is singular and cannot be inverted");
        }
        int n = this.rows;
        ComplexMatrix adjugate = this.adjugate();
        ComplexMatrix inverse = new ComplexMatrix(n, n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                inverse.matrix[i][j] = adjugate.matrix[i][j].divide(det);

        return inverse;
    }

    //присоединенная матрица
    private ComplexMatrix adjugate() {
        int n = this.rows;
        ComplexMatrix adj = new ComplexMatrix(n, n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                ComplexNumber sign = new ComplexNumber(((i + j) % 2 == 0 ? 1 : -1), 0);
                ComplexNumber det = detRec(getSubMatrix(this.matrix, i, j));
                adj.matrix[j][i] = sign.multiply(det);
            }
        return adj;
    }
    //деление
    public ComplexMatrix divide(ComplexMatrix m) throws IllegalArgumentException, ArithmeticException {
        return this.multiply(m.inverse());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ComplexNumber[] row : matrix) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        return sb.toString();
    }
}
