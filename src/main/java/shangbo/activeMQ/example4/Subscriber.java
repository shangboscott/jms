package shangbo.activeMQ.example4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;

class Subscriber {
	public static void main(String[] args) throws Exception {
		// 实例化 Spring IoC 容器
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

		// 接收消息
		while (true) {
			String msg = (String) jmsTemplate.receiveAndConvert();
			if (msg == null) {
				System.out.println("No new message, sleeping 5 secs");
				Thread.sleep(5 * 1000);
				continue;
			}
			System.out.println(msg);
		}
	}

}