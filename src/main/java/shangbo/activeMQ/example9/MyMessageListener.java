package shangbo.activeMQ.example9;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.jms.support.converter.MessageConverter;

public class MyMessageListener implements MessageListener {
	private MessageConverter converter;

	public void onMessage(Message msg) {
		try {
			Person p = (Person) converter.fromMessage(msg);
			System.out.println(p);
		} catch (JMSException e1) {
			e1.printStackTrace();
		}
	}

	public void setConverter(MessageConverter converter) {
		this.converter = converter;
	}

}
