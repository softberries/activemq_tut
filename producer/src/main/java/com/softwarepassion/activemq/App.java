package com.softwarepassion.activemq;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;



public class App 
{

	public static String brokerURL = "tcp://localhost:61616";
	
    public static void main( String[] args ) throws JMSException
    {
    	// setup the connection to ActiveMQ
        ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
        Producer producer = new Producer(factory, "test");
        producer.run();
        producer.close();
    }
}
