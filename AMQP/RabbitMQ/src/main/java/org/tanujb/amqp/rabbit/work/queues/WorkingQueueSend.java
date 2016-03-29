package org.tanujb.amqp.rabbit.work.queues;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class WorkingQueueSend {

	private final static String QUEUE_NAME = "task_queue";

	public static void main(String args[]) {
		ConnectionFactory factory = new ConnectionFactory();
		try {
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			boolean durable = true;
			channel.queueDeclare(QUEUE_NAME, durable, false, false, null);

			String message = "Hello...";

			channel.basicPublish("", QUEUE_NAME,
					MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

			System.out.println(" [x] Sent '" + message + "'");

			channel.close();
			connection.close();

		} catch (IOException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
