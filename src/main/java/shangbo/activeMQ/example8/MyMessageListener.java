package shangbo.activeMQ.example8;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener implements MessageListener {

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

}
