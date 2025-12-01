import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.domain.FIPANames;
import jade.proto.ContractNetResponder;

public class LinearRegressionAgent extends Agent {

    protected void setup() {
        MessageTemplate template = MessageTemplate.and(
                MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET),
                MessageTemplate.MatchPerformative(ACLMessage.CFP)
        );
        addBehaviour(new ContractNetResponder(this, template) {
            private RegressionResult result;

            protected ACLMessage handleCfp(ACLMessage cfp) {
                try {
                    DataSet data = (DataSet) cfp.getContentObject();
                    double[] coef = RegressionUtils.linearRegression(data);
                    double r2 = RegressionUtils.rSquared(data, coef);
                    result = new RegressionResult("linear", coef, r2);
                    ACLMessage reply = cfp.createReply();
                    reply.setPerformative(ACLMessage.PROPOSE);
                    reply.setContent(String.valueOf(r2));
                    System.out.println(getLocalName() + " calculo R2=" + r2);
                    return reply;
                } catch (Exception e) {
                    ACLMessage reply = cfp.createReply();
                    reply.setPerformative(ACLMessage.REFUSE);
                    reply.setContent("error");
                    e.printStackTrace();
                    return reply;
                }
            }

            protected ACLMessage prepareResultNotification(ACLMessage cfp, ACLMessage propose, ACLMessage accept) {
                ACLMessage inform = accept.createReply();
                inform.setPerformative(ACLMessage.INFORM);
                try {
                    inform.setContentObject(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return inform;
            }
        });
    }
}

