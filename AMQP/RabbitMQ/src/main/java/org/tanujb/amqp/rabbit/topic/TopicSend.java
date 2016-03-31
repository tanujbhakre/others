package org.tanujb.amqp.rabbit.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TopicSend {
	private static final String EXCHANGE_NAME = "topic_logs";

	public static void main(String[] argv) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, "topic");

		String routingKey = "aa.bb.cc";
		String message = "This is a message for aa.bb.cc";

		channel.basicPublish(EXCHANGE_NAME, routingKey, null,
				message.getBytes());
		System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

		connection.close();
	}
}
