package examples.yellowPages;
 
import jade.core.Agent;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import java.util.Iterator;
 
public class BuscaServicio extends Agent {
   
     protected void setup() {
        // Describir el tipo de servicio que busca el Agent 
        ServiceDescription servicio = new ServiceDescription();
        servicio.setType("Data Analytics");
 
        // Crear plantilla para buscar el servicio (use castellano)
        DFAgentDescription descripcion = new DFAgentDescription();
        descripcion.addLanguages("Castellano");
 
        // Agregar a la plantiila el servicio que busca el agente
        descripcion.addServices(servicio);
        try {
        // Buscar el servicio en el DFAgengt a través de la plantilla
            DFAgentDescription[] resultados = DFService.search(this,descripcion);
 
            if (resultados.length == 0)
                System.out.println("Ningun agente ofrece el servicio deseado");
 
            for (int i = 0; i < resultados.length; ++i) {
                System.out.println("El agente "+resultados[i].getName()+" ofrece sig. servicios:");
                Iterator servicios = resultados[i].getAllServices();
                int j = 1;
                while(servicios.hasNext()) {
                    servicio = (ServiceDescription)servicios.next();
                    System.out.println(j+"- "+servicio.getName());
                    System.out.println();
                    j++;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
