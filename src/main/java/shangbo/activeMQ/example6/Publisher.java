package shangbo.activeMQ.example6;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;

class Publisher {

	public static void main(String[] args) throws Exception {
		// 实例化 Spring IoC 容器
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		JmsTemplate JmsTemplate = context.getBean(JmsTemplate.class);

		// 发送消息
		JmsTemplate.convertAndSend("hello topic world");
		System.out.println("message sent");
		
		Thread.sleep(1000);
		System.exit(0);
	}
}