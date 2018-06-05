package shangbo.activeMQ.example1;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class Consumer {
	// ActiveMQ 支持不同的协议，你可以在它的配置文件中 conf/activemq.xml 找到不同协议的连接方式
	public static String BROKER_URL = "tcp://0.0.0.0:61616";
	public static String USER = "admin";
	public static String PASSWORD = "admin";
	public static String DESTINATION = "systemA.systemB.Price.Queue";

	public static void main(String[] args) throws JMSException {
		// 创建连接工厂
		ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);

		// 创建连接
		Connection connection = factory.createConnection(USER, PASSWORD);
		connection.start();

		// 创建会话
		Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

		// 创建消息源
		Destination dest = new ActiveMQQueue(DESTINATION);

		// 接收消息
		MessageConsumer consumer = session.createConsumer(dest);
		Message msg = consumer.receive(1000);

		// 解析消息
		if (msg != null) {
			if (msg instanceof TextMessage) {
				String body = ((TextMessage) msg).getText();
				System.out.println(body);
			} else {
				System.out.println("Unexpected message type: " + msg.getClass());
			}

			// 发送确认通知
			msg.acknowledge();
		} else {
			System.out.println("No new message");
		}

		// 释放资源
		session.close();
		connection.close();
		System.exit(0);
	}

}