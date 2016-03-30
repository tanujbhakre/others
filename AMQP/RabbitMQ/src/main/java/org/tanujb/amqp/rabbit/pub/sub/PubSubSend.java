package org.tanujb.amqp.rabbit.pub.sub;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class PubSubSend {

	private static final String EXCHANGE_NAME = "logs";

	public static void main(String[] args) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try {
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

			String message = "This is a log message";

			channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());

			System.out.println(" [x] Sent '" + message + "'");

			channel.close();
			connection.close();
		} catch (IOException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
