package shangbo.activeMQ.example10;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;

class Publisher {

	public static void main(String[] args) throws Exception {
		// 实例化 Spring IoC 容器
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

		// 发送消息
		jmsTemplate.convertAndSend(newPerson());

		Thread.sleep(1000);
		System.exit(0);
	}
	
	public static Person newPerson() {
		Person p = new Person();
		p.setFirstName("Bo");
		p.setLastName("Shang");
		
		
		return p;
	}
}