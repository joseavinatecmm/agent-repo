package examples.messaging;
 
import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
 
public class BlockReceptor extends Agent {
   
    
    protected void setup() {
        addBehaviour(new ReceptorComportaminento());
    }

    private class ReceptorComportaminento extends SimpleBehaviour {
            private boolean fin = false;

            public void action() {
                System.out.println(" Preparandose para recibir");
 
            //Obtiene el primer mensaje de la cola de mensajes
                ACLMessage mensaje = receive();
 
                if (mensaje!= null) {
                    System.out.println(getLocalName() + ": acaba de recibir el siguiente mensaje: ");
                    System.out.println(mensaje.toString());
                    fin = true;
                } else{
                    System.out.println("Receptor: Esperando a recibir mensaje...");
                    block();
                }
            }
            public boolean done() {
               return fin;
            }
    }
}







