package activemq.tutorial

import org.springframework.stereotype.Component
import org.springframework.jms.annotation.JmsListener

@Component
class Receiver {
	
	@JmsListener(destination = "mailbox", containerFactory = "myFactory")
	fun receiveMessage(email : Email) {
		println("Received <${email}>")
	}
}