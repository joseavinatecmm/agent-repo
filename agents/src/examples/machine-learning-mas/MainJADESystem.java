import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class MainJADESystem {

    public static void main(String[] args) throws Exception {
        double[] x = new double[]{1, 2, 3, 4, 5, 6};
        double[] y = new double[]{2, 4.1, 6.2, 8.1, 10.2, 12.1};
        DataSet dataSet = new DataSet(x, y);

        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl();
        AgentContainer mainContainer = rt.createMainContainer(p);

        AgentController linear = mainContainer.createNewAgent("linear-agent", LinearRegressionAgent.class.getName(), null);
        AgentController quadratic = mainContainer.createNewAgent("quadratic-agent", QuadraticRegressionAgent.class.getName(), null);
        AgentController cubic = mainContainer.createNewAgent("cubic-agent", CubicRegressionAgent.class.getName(), null);
        AgentController client = mainContainer.createNewAgent("ml-client", MLClientAgent.class.getName(), new Object[]{dataSet});

        linear.start();
        quadratic.start();
        cubic.start();
        client.start();
    }
}

