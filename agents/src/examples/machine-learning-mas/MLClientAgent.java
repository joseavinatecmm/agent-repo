import jade.core.Agent;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.domain.FIPANames;
import jade.proto.ContractNetInitiator;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class MLClientAgent extends Agent {

    protected void setup() {
        Object[] args = getArguments();
        DataSet data = (DataSet) args[0];
        ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
        cfp.addReceiver(new AID("linear-agent", AID.ISLOCALNAME));
        cfp.addReceiver(new AID("quadratic-agent", AID.ISLOCALNAME));
        cfp.addReceiver(new AID("cubic-agent", AID.ISLOCALNAME));
        cfp.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
        cfp.setReplyByDate(new Date(System.currentTimeMillis() + 10000));
        try {
            cfp.setContentObject(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        addBehaviour(new ContractNetInitiator(this, cfp) {
            private Map<String, Double> r2Map = new HashMap<>();
            private RegressionResult bestResult;

            protected void handlePropose(ACLMessage propose, Vector v) {
                try {
                    double r2 = Double.parseDouble(propose.getContent());
                    String name = propose.getSender().getLocalName();
                    r2Map.put(name, r2);
                    System.out.println("Propuesta de " + name + " con R2=" + r2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void handleAllResponses(Vector responses, Vector acceptances) {
                double best = -1;
                ACLMessage bestMsg = null;
                Enumeration e = responses.elements();
                while (e.hasMoreElements()) {
                    ACLMessage msg = (ACLMessage) e.nextElement();
                    if (msg.getPerformative() == ACLMessage.PROPOSE) {
                        try {
                            double r2 = Double.parseDouble(msg.getContent());
                            if (r2 > best) {
                                best = r2;
                                bestMsg = msg;
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                e = responses.elements();
                while (e.hasMoreElements()) {
                    ACLMessage msg = (ACLMessage) e.nextElement();
                    ACLMessage reply = msg.createReply();
                    if (msg.getPerformative() == ACLMessage.PROPOSE) {
                        if (msg == bestMsg) {
                            reply.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                            System.out.println("Aceptando propuesta de " + msg.getSender().getLocalName());
                        } else {
                            reply.setPerformative(ACLMessage.REJECT_PROPOSAL);
                            System.out.println("Rechazando propuesta de " + msg.getSender().getLocalName());
                        }
                        acceptances.add(reply);
                    }
                }
                System.out.println("R2 recibidos:");
                for (Map.Entry<String, Double> entry : r2Map.entrySet()) {
                    System.out.println(entry.getKey() + " -> " + entry.getValue());
                }
            }

            protected void handleInform(ACLMessage inform) {
                try {
                    bestResult = (RegressionResult) inform.getContentObject();
                    System.out.println("Modelo seleccionado: " + bestResult.getModelName() + " con R2=" + bestResult.getR2());
                    double[] xs = new double[]{1.0, 2.0, 3.0};
                    for (double xv : xs) {
                        double yp = RegressionUtils.predict(bestResult.getCoefficients(), xv);
                        System.out.println("Prediccion para x=" + xv + " es y=" + yp);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

