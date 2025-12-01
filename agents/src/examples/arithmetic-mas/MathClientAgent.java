import jade.core.Agent;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.domain.FIPANames;
import jade.proto.ContractNetInitiator;
import java.util.Date;
import java.util.Vector;

public class MathClientAgent extends Agent {

    protected void setup(){
        Object[] args=getArguments();
        String op=(String)args[0];
        double a=(double)args[1];
        double b=(double)args[2];

        ACLMessage cfp=new ACLMessage(ACLMessage.CFP);
        cfp.addReceiver(new AID("math-service",AID.ISLOCALNAME));
        cfp.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
        cfp.setReplyByDate(new Date(System.currentTimeMillis()+5000));
        cfp.setContent(op+","+a+","+b);

        addBehaviour(new ContractNetInitiator(this,cfp){
            protected void handleAllResponses(Vector responses,Vector acceptances){
                ACLMessage best=null;
                for(Object o:responses){
                    ACLMessage msg=(ACLMessage)o;
                    if(msg.getPerformative()==ACLMessage.PROPOSE) best=msg;
                }
                if(best!=null){
                    ACLMessage reply=best.createReply();
                    reply.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                    acceptances.add(reply);
                }
            }

            protected void handleInform(ACLMessage inform){
                System.out.println("Resultado recibido: "+inform.getContent());
            }
        });
    }
}

