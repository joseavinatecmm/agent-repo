package examples.yellowPages;
 
import jade.core.Agent;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
 
public class OfreceServicio extends Agent {
  
    protected void setup() {
    // Descripción del agente
        DFAgentDescription descripcion = new DFAgentDescription();
        descripcion.setName(getAID());
        descripcion.addLanguages("Castellano");
 
    // Descripcion de un servicio que proporciona el Agente
        ServiceDescription servicio = new ServiceDescription();
        servicio.setType("Data Analytics");
        servicio.setName("Linear Regression");
 
    // Añade dicho servicio a la lista de servicios de la descripción del agente
        descripcion.addServices(servicio);
 
    // Descripcion de otro servicio que proporciona el Agente
        servicio = new ServiceDescription();
        servicio.setType("Optimization");
        servicio.setName("Genetic Algorithms"); 
        descripcion.addServices(servicio);
 
        try {
            //Registrando el Agente con sus respectivo servicios
            DFService.register(this, descripcion);
        }
        catch (FIPAException e) {
            e.printStackTrace();
        }
    }
 
    protected void takeDown() {
        try {
            //Elimminado el registro del Agente con sus respectivos servicios
            DFService.deregister(this);
        }
        catch (FIPAException fe) {
            fe.printStackTrace();
        }
        System.out.println("El agente "+getAID().getName()+" ya no ofrece sus servicios.");
    }
}
