package com.softwarepassion.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
 
public class Producer
{
    private ConnectionFactory factory;
    private Connection connection;
    private Session session;
    private MessageProducer producer;
 
    public Producer(ConnectionFactory factory, String queueName) throws JMSException
    {
        this.factory = factory;
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(queueName);
        producer = session.createProducer(destination);
    }
 
    public void run() throws JMSException
    {
        for (int i = 0; i < 10; i++)
        {
            System.out.println("Creating Message " + i);
            Message message = session.createTextMessage(this.mTxt);
            producer.send(message);
        }
    }
 
    public void close() throws JMSException
    {
        if (connection != null)
        {
            connection.close();
        }
    }
 
    private String mTxt =   "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
    						"<note>" +
				    		"<to>Anyone</to>" +
				    		"<from>Kris</from>" +
				    		"<heading>Simple Message</heading>" +
				    		"<body>Check out my Twitter account @grajo</body>" +
				    		"</note>";
}