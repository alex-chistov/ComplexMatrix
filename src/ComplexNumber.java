public class ComplexNumber {
    private double re;
    private double im;

    public ComplexNumber() {
        this.re = 0;
        this.im = 0;
    }

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public ComplexNumber(ComplexNumber c) {
        this.re = c.re;
        this.im = c.im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    public void setRe(double re) {
        this.re = re;
    }

    public void setIm(double im) {
        this.im = im;
    }

    public ComplexNumber add(ComplexNumber c) {
        return new ComplexNumber(this.re + c.re, this.im + c.im);
    }

    public ComplexNumber subtract(ComplexNumber c) {
        return new ComplexNumber(this.re - c.re, this.im - c.im);
    }

    public ComplexNumber multiply(ComplexNumber c) {
        double rePart = this.re * c.re - this.im * c.im;
        double imagPart = this.re * c.im + this.im * c.re;
        return new ComplexNumber(rePart, imagPart);
    }

    public ComplexNumber divide(ComplexNumber c) throws ArithmeticException {
        double denom = c.re * c.re + c.im * c.im;
        if (denom == 0) {
            throw new ArithmeticException("Division by zero");
        }
        double rePart = (this.re * c.re + this.im * c.im) / denom;
        double imagPart = (this.im * c.re - this.re * c.im) / denom;
        return new ComplexNumber(rePart, imagPart);
    }

    @Override
    public String toString() {
        if (im == 0) return String.format("%.2f", re);
        if (re == 0) return String.format("%.2fi", im);
        if (im < 0)
            return String.format("%.2f - %.2fi", re, -im);
        else
            return String.format("%.2f + %.2fi", re, im);
    }
}
