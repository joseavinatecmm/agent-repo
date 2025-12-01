import jade.core.Runtime;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class MainJADE {
    public static void main(String[] args)throws Exception{
        Runtime rt=Runtime.instance();
        AgentContainer mc=rt.createMainContainer(new ProfileImpl());

        mc.createNewAgent("math-service",MathServiceAgent.class.getName(),null).start();
        mc.createNewAgent("client-add",MathClientAgent.class.getName(),new Object[]{"+",7.0,3.0}).start();
        mc.createNewAgent("client-sub",MathClientAgent.class.getName(),new Object[]{"-",10.0,4.0}).start();
        
        mc.createNewAgent("client-sub2",MathClientAgent.class.getName(),new Object[]{"+",1.0,1.0}).start();
    }
}

