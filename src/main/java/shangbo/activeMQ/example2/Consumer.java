package shangbo.activeMQ.example2;

import javax.jms.JMSException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;

class Consumer {
	public static void main(String[] args) throws JMSException {
		// 实例化 Spring IoC 容器
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

		// 接收消息
		while (true) {
			String msg = (String) jmsTemplate.receiveAndConvert();
			if (msg == null) {
				System.out.println("No message received after 5 seconds");
			} else {
				System.out.println(msg);
			}
		}
	}

}