package org.tanujb.amqp.rabbit.work.queues;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class WorkingQueueReceive {

	private final static String QUEUE_NAME = "task_queue";

	public static void main(String[] args) {

		ConnectionFactory factory = new ConnectionFactory();
		try {

			final Connection connection = factory.newConnection();
			final Channel channel = connection.createChannel();
			boolean durable = true;
			channel.queueDeclare(QUEUE_NAME, durable, false, false, null);

			final Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag,
						Envelope envelope, BasicProperties properties,
						byte[] body) throws IOException {

					String message = new String(body, "UTF-8");

					System.out.println(" [x] Received '" + message + "'");
					try {
						doWork(message);
					} finally {
						System.out.println(" [x] Done");
						channel.basicAck(envelope.getDeliveryTag(), false);
					}
				}

			};

			channel.basicConsume(QUEUE_NAME, true, consumer);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void doWork(String task) {
		for (char ch : task.toCharArray()) {
			if (ch == '.')
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
		}
	}
}
