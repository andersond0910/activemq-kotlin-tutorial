package activemq.tutorial

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.jms.config.JmsListenerContainerFactory
import javax.jms.ConnectionFactory
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.config.DefaultJmsListenerContainerFactory
import org.springframework.jms.listener.MessageListenerContainer
import org.springframework.jms.support.converter.MessageConverter
import org.springframework.jms.support.converter.MappingJackson2MessageConverter
import org.springframework.jms.support.converter.MessageType
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.jms.core.JmsTemplate

@SpringBootApplication
@EnableJms
open class ActivemqStarterTutorialApplication{
	@Bean
	open fun myFactory(connectionFactory : ConnectionFactory, configurer : DefaultJmsListenerContainerFactoryConfigurer) : JmsListenerContainerFactory<out MessageListenerContainer> {
		val	factory = DefaultJmsListenerContainerFactory()
		configurer.configure(factory, connectionFactory)
		return factory
	}
	
	@Bean
	open fun jacksonJmsMessageConverter() : MessageConverter {
		var converter = MappingJackson2MessageConverter()
		converter.setTargetType(MessageType.TEXT)
		converter.setTypeIdPropertyName("_type")
		return converter
	}
	
}

fun main(args: Array<String>) {
	val context : ConfigurableApplicationContext =  runApplication<ActivemqStarterTutorialApplication>(*args)
	val jmsTemplate = context.getBean(JmsTemplate::class.java)
	println("Sending an email message")
	jmsTemplate.convertAndSend("mailbox",Email("info@example.com","Hello"))
}
