import java.io.Serializable;

public class RegressionResult implements Serializable {
    private String modelName;
    private double[] coefficients;
    private double r2;

    public RegressionResult(String modelName, double[] coefficients, double r2) {
        this.modelName = modelName;
        this.coefficients = coefficients;
        this.r2 = r2;
    }

    public String getModelName() {
        return modelName;
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    public double getR2() {
        return r2;
    }
}

