package shangbo.activeMQ.example2;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class AppConfig {
	// ActiveMQ 支持不同的协议，你可以在它的配置文件中 conf/activemq.xml 找到不同协议的连接方式
	public static String BROKER_URL = "tcp://0.0.0.0:61616";
	public static String USER = "admin";
	public static String PASSWORD = "admin";
	public static String PRICE_QUEUE = "systemA.systemB.Price.Queue";

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setTargetConnectionFactory(activeMQConnectionFactory());
		connectionFactory.setSessionCacheSize(20);

		return connectionFactory;
	}

	private ActiveMQConnectionFactory activeMQConnectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL(BROKER_URL);
		factory.setUserName(USER);
		factory.setPassword(PASSWORD);

		return factory;
	}

	@Bean
	public Queue priceQueue() {
		return new ActiveMQQueue(PRICE_QUEUE);
	}

	@Bean
	public JmsTemplate priceJmsTemplate(ConnectionFactory factory, Queue priceQueue) {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(factory);
		jmsTemplate.setDefaultDestination(priceQueue);
		jmsTemplate.setReceiveTimeout(5 * 1000);

		return jmsTemplate;
	}

}
