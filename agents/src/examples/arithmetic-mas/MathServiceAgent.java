import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.domain.FIPANames;
import jade.proto.ContractNetResponder;

public class MathServiceAgent extends Agent {

    protected void setup(){

        MessageTemplate mt=MessageTemplate.and(
                MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET),
                MessageTemplate.MatchPerformative(ACLMessage.CFP)
        );

        addBehaviour(new ContractNetResponder(this,mt){
            protected ACLMessage handleCfp(ACLMessage cfp){
                try{
                    String[] data=cfp.getContent().split(",");
                    String op=data[0];
                    double a=Double.parseDouble(data[1]);
                    double b=Double.parseDouble(data[2]);
                    AritmeticOperations ar=new AritmeticOperations();
                    double result=0;
                    if(op.equals("+")) result=ar.add(a,b);
                    else if(op.equals("-")) result=ar.sub(a,b);
                    ACLMessage reply=cfp.createReply();
                    reply.setPerformative(ACLMessage.PROPOSE);
                    reply.setContent(String.valueOf(result));
                    return reply;
                }catch(Exception e){
                    ACLMessage reply=cfp.createReply();
                    reply.setPerformative(ACLMessage.REFUSE);
                    reply.setContent("error");
                    return reply;
                }
            }

            protected ACLMessage prepareResultNotification(ACLMessage cfp,ACLMessage propose,ACLMessage accept){
                ACLMessage inform=accept.createReply();
                inform.setPerformative(ACLMessage.INFORM);
                inform.setContent(propose.getContent());
                return inform;
            }
        });
    }
}

