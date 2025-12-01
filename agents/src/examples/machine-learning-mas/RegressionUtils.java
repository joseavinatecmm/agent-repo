public class RegressionUtils {

    public static double[] linearRegression(DataSet data) {
        double[] x = data.getX();
        double[] y = data.getY();
        int n = x.length;
        double sumX = 0;
        double sumY = 0;
        double sumXX = 0;
        double sumXY = 0;
        for (int i = 0; i < n; i++) {
            sumX += x[i];
            sumY += y[i];
            sumXX += x[i] * x[i];
            sumXY += x[i] * y[i];
        }
        double denom = n * sumXX - sumX * sumX;
        double b = (n * sumXY - sumX * sumY) / denom;
        double a = (sumY - b * sumX) / n;
        return new double[]{a, b};
    }

    public static double[] polynomialRegression(DataSet data, int degree) {
        double[] x = data.getX();
        double[] y = data.getY();
        int n = x.length;
        int m = degree + 1;
        double[][] A = new double[m][m];
        double[] B = new double[m];
        double[] sx = new double[2 * degree + 1];
        for (int k = 0; k < sx.length; k++) {
            double s = 0;
            for (int i = 0; i < n; i++) {
                s += Math.pow(x[i], k);
            }
            sx[k] = s;
        }
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < m; col++) {
                A[row][col] = sx[row + col];
            }
        }
        for (int row = 0; row < m; row++) {
            double s = 0;
            for (int i = 0; i < n; i++) {
                s += y[i] * Math.pow(x[i], row);
            }
            B[row] = s;
        }
        return solveLinearSystem(A, B);
    }

    private static double[] solveLinearSystem(double[][] A, double[] B) {
        int n = B.length;
        for (int p = 0; p < n; p++) {
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }
            double[] temp = A[p];
            A[p] = A[max];
            A[max] = temp;
            double t = B[p];
            B[p] = B[max];
            B[max] = t;
            if (Math.abs(A[p][p]) < 1e-12) {
                throw new RuntimeException("Singular matrix");
            }
            for (int i = p + 1; i < n; i++) {
                double alpha = A[i][p] / A[p][p];
                B[i] -= alpha * B[p];
                for (int j = p; j < n; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (B[i] - sum) / A[i][i];
        }
        return x;
    }

    public static double rSquared(DataSet data, double[] coefficients) {
        double[] x = data.getX();
        double[] y = data.getY();
        int n = x.length;
        double meanY = 0;
        for (int i = 0; i < n; i++) {
            meanY += y[i];
        }
        meanY /= n;
        double ssTot = 0;
        double ssRes = 0;
        for (int i = 0; i < n; i++) {
            double yi = y[i];
            double yPred = predict(coefficients, x[i]);
            ssTot += (yi - meanY) * (yi - meanY);
            ssRes += (yi - yPred) * (yi - yPred);
        }
        return 1.0 - ssRes / ssTot;
    }

    public static double predict(double[] coefficients, double x) {
        double y = 0;
        double xp = 1;
        for (int i = 0; i < coefficients.length; i++) {
            y += coefficients[i] * xp;
            xp *= x;
        }
        return y;
    }
}

