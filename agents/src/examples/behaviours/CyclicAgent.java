package examples.behaviours;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;

public class CyclicAgent extends Agent {

  protected void setup() {
    System.out.println("Agent "+getLocalName()+" started.");
    addBehaviour(new MyCyclicBehaviour());
  } 

  private class MyCyclicBehaviour extends CyclicBehaviour {

    public void action() {
        System.out.println("Agent's action method is executed");
    } 
   
    public boolean done() {
	    return true;
    }

    public int onEnd() {
      myAgent.doDelete();
      return super.onEnd();
    } 
  }    // END of inner class ...Behaviour
}
