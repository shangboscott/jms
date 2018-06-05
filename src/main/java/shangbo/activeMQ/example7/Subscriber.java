package shangbo.activeMQ.example7;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

class Subscriber {
	// ActiveMQ 支持不同的协议，你可以在它的配置文件中 conf/activemq.xml 找到不同协议的连接方式
	public static String BROKER_URL = "tcp://0.0.0.0:61616";
	public static String USER = "admin";
	public static String PASSWORD = "admin";
	public static String DESTINATION = "systemA.systemB.Price.Topic";
	public static String DURABLE_SUBSCRIBER = "systemA.systemB.Price.Topic.myDurable";
	public static String CLIENT_ID = "systemB";

	public static void main(String[] args) throws Exception {
		// 创建连接工厂
		ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);

		// 创建连接
		Connection connection = factory.createConnection(USER, PASSWORD);
		connection.setClientID(CLIENT_ID);
		connection.start();

		// 创建会话
		Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

		// 创建消息源
		Topic dest = new ActiveMQTopic(DESTINATION);

		// 接收消息
		MessageConsumer consumer = session.createDurableSubscriber(dest, DURABLE_SUBSCRIBER);
		while (true) {
			Message msg = consumer.receive(1000);
			if (msg == null) {
				System.out.println("No new message, sleeping 5 secs");
				Thread.sleep(5 * 1000);
				continue;
			}

			// 解析消息
			if (msg instanceof TextMessage) {
				String body = ((TextMessage) msg).getText();
				System.out.println(body);
			} else {
				System.out.println("Unexpected message type: " + msg.getClass());
			}

			// 发送确认通知
			msg.acknowledge();
		}

		// 释放资源
		// session.close();
		// connection.close();
		// System.exit(0);
	}

}