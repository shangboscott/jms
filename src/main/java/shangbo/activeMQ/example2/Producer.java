package shangbo.activeMQ.example2;

import javax.jms.JMSException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;

class Producer {

	public static void main(String[] args) throws JMSException {
		// 实例化 Spring IoC 容器
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

		// 发送消息
		jmsTemplate.convertAndSend("hello queue world");
		System.out.println("message sent");
		System.exit(0);
	}
}