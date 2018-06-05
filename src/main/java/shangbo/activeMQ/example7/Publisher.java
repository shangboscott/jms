package shangbo.activeMQ.example7;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

class Publisher {
	// ActiveMQ 支持不同的协议，你可以在它的配置文件中 conf/activemq.xml 找到不同协议的连接方式
	public static String BROKER_URL = "tcp://0.0.0.0:61616";
	public static String USER = "admin";
	public static String PASSWORD = "admin";
	public static String DESTINATION = "systemA.systemB.Price.Topic";

	public static void main(String[] args) throws JMSException {
		// 创建连接工厂
		ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);

		// 创建连接
		Connection connection = factory.createConnection(USER, PASSWORD);

		// 创建会话
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// 创建消息
		TextMessage msg = session.createTextMessage("hello topic world");

		// 创建消息目的地
		Destination dest = new ActiveMQTopic(DESTINATION);

		// 发送消息
		MessageProducer producer = session.createProducer(dest);
		producer.send(msg);
		System.out.println("message sent");

		// 释放资源
		session.close();
		connection.close();
		System.exit(0);
	}
}