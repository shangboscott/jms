package shangbo.activeMQ.example5;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

class Subscriber {
	// ActiveMQ 支持不同的协议，你可以在它的配置文件中 conf/activemq.xml 找到不同协议的连接方式
	public static String BROKER_URL = "tcp://0.0.0.0:61616";
	public static String USER = "admin";
	public static String PASSWORD = "admin";
	public static String DESTINATION = "systemA.systemB.Price.Topic";

	public static void main(String[] args) throws Exception {
		// 创建连接工厂
		ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);

		// 创建连接
		Connection connection = factory.createConnection(USER, PASSWORD);
		connection.start();

		// 创建会话
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// 创建消息源
		Destination dest = new ActiveMQTopic(DESTINATION);

		// 接收消息
		MessageConsumer consumer = session.createConsumer(dest);
		consumer.setMessageListener(new MessageListener() {

			public void onMessage(Message msg) {
				if (msg instanceof TextMessage) {
					String body;
					try {
						body = ((TextMessage) msg).getText();
						System.out.println(body);
					} catch (JMSException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Unexpected message type: " + msg.getClass());
				}
			}

		});

		while (true) {
			Thread.sleep(1 * 60 * 1000);
			break;
		}

		// 释放资源
		session.close();
		connection.close();
		System.exit(0);
	}

}