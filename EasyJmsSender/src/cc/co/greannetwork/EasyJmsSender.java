/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.co.greannetwork;

import com.sun.messaging.Queue;
import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author Grean
 */
public class EasyJmsSender {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JMSException {
        QueueConnection con = createConnection();
        QueueSession createQueueSession = con.createQueueSession(true, Session.SESSION_TRANSACTED);
        Queue createQueue = (Queue) createQueueSession.createQueue("MyQueue");
        TextMessage createTextMessage = createQueueSession.createTextMessage("EasyJmsSender Sending...");
        QueueSender createSender = createQueueSession.createSender(createQueue);
        createSender.send(createTextMessage);
        createQueueSession.commit();
        createQueueSession.close();
        con.close();
    }

    private static QueueConnection createConnection() throws JMSException {
        com.sun.messaging.QueueConnectionFactory qcf = new com.sun.messaging.QueueConnectionFactory();
        qcf.setProperty(com.sun.messaging.ConnectionConfiguration.imqAddressList, "192.168.17.129:7676");
        qcf.setProperty(com.sun.messaging.ConnectionConfiguration.imqDefaultUsername, "admin");
        qcf.setProperty(com.sun.messaging.ConnectionConfiguration.imqDefaultPassword, "admin");
        return qcf.createQueueConnection();
    }
}
