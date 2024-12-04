package kz.bitlab.rabbit.middle03rabbitreceiver.listener;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;
import kz.bitlab.rabbit.middle03rabbitreceiver.dto.OrderDto;

@Slf4j
@Component
public class MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "my-message-exchange", type = ExchangeTypes.DIRECT),
            value = @Queue(value = "my-message-queue"), key = "key555"))
    public void receiveMessage(String message){
        try {
            System.out.println("Message from queue : " + message);
            LOGGER.info("Received {}", message);
            throw new RuntimeException("Failed to retrieve data");
        }catch (Exception e){
            LOGGER.error("Error on processing code");
            throw e;
        }
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "almaty_orders_queue"),
            exchange = @Exchange(value = "${mq.topic.order.exchange}", type = ExchangeTypes.TOPIC),
            key = "order.*.send"
    ))
    public void receiveAlmatyOrder(OrderDto orderDto){
        LOGGER.info("Sent order No {}, with operation send Almaty {}", orderDto.getOrderNo(), orderDto.getOrderName());
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "astana_orders_queue"),
            exchange = @Exchange(value = "${mq.topic.order.exchange}", type = ExchangeTypes.TOPIC),
            key = "order.*.receive"
    ))
    public void receiveAstanaOrder(OrderDto orderDto){
        LOGGER.info("Receive order No {}, with operation receive Astana {}", orderDto.getOrderNo(), orderDto.getOrderName());
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "shymkent_orders_queue"),
            exchange = @Exchange(value = "${mq.topic.order.exchange}", type = ExchangeTypes.TOPIC),
            key = "order.*.receive"
    ))
    public void receiveShymkentOrder(OrderDto orderDto){
        LOGGER.info("Receive in Shymkent order No {}, with operation receive Shymkent {}", orderDto.getOrderNo(), orderDto.getOrderName());
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "first_fanout_queue"),
            exchange = @Exchange(value = "${mq.fanout.order.exchange}", type = ExchangeTypes.FANOUT),
            key = ""
    ))
    public void receiveFirstFanoutQueue(OrderDto orderDto){
        LOGGER.info("Received from first queue " + orderDto.getOrderName() + " " + orderDto.getOrderNo());
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "second_fanout_queue"),
            exchange = @Exchange(value = "${mq.fanout.order.exchange}", type = ExchangeTypes.FANOUT),
            key = ""
    ))
    public void receiveSecondFanoutQueue(OrderDto orderDto){
        LOGGER.info("Received from second queue " + orderDto.getOrderName() + " " + orderDto.getOrderNo());
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "third_fanout_queue"),
            exchange = @Exchange(value = "${mq.fanout.order.exchange}", type = ExchangeTypes.FANOUT),
            key = ""
    ))
    public void receiveThirdFanoutQueue(OrderDto orderDto){
        LOGGER.info("Received from third queue " + orderDto.getOrderName() + " " + orderDto.getOrderNo() + orderDto);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "my-message-queue-test",
                    arguments = {
                        @Argument(name = "x-dead-letter-exchange", value = "dlx"),
                        @Argument(name = "x-dead-letter-routing-key", value = "dlx.test-my-message-queue")
                    }),
            exchange = @Exchange(value = "my-message-exchange-test", type = ExchangeTypes.DIRECT),
            key = "12345"
    ))
    public void receiveOrder(String message){
        try {
            LOGGER.info("Received message {}", message);
            throw new RuntimeException("Some Runtime Exception");
        }catch (Exception e){
            LOGGER.error("Error on loading message");
            throw e;
        }
    }

}