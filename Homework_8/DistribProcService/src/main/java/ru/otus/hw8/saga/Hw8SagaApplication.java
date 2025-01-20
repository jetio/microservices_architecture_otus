package ru.otus.hw8.saga;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.SagaPropagation;
import org.apache.camel.saga.InMemorySagaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Hw8SagaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hw8SagaApplication.class, args);
    }

    @Bean
    public InMemorySagaService sagaService() {
        return new InMemorySagaService();
    }

    @Bean
    public RouteBuilder sagaRoute() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // Start the saga

                from("direct:startSaga")
                        .saga()
                        .propagation(SagaPropagation.REQUIRES_NEW)
                        .option("orderId", header("orderId"))
                        .to("direct:createOrder");

                // Create Order
                from("direct:createOrder")
                        .saga()
                        .compensation("direct:cancelOrder")
//                        .setProperty("orderId", header("orderId"))
                        .log("Creating order with ID ${header.orderId}")
                        .setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http.HttpMethods.POST))
                        .to("http://localhost:8082/order/create")
                        .to("direct:makePayment");

                // Make Payment
                from("direct:makePayment")
                        .saga()
                        .compensation("direct:refundPayment")
                        .log("Making payment for order with ID ${header.orderId}")
                        .setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http.HttpMethods.POST))
                        .to("http://localhost:8083/payment/create")
                        .to("direct:reserveItem");

                // Reserve Item in Warehouse
                from("direct:reserveItem")
                        .saga()
                        .compensation("direct:cancelReservation")
                        .log("Making warehouse reservation for order with ID ${header.orderId}")
                        .setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http.HttpMethods.POST))
                        .to("http://localhost:8084/item/book")
                        .to("direct:bookDelivery");

                // Book Delivery
                from("direct:bookDelivery")
                        .saga()
                        .compensation("direct:cancelDelivery")
                        .log("Creating delivery for order with ID ${header.orderId}")
                        .setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http.HttpMethods.POST))
                        .to("http://localhost:8081/delivery/create")
                        .end();

                // Compensation routes
                from("direct:cancelOrder")
                        .setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http.HttpMethods.POST))
                        .log("Cancelling delivery")
                        .to("http://localhost:8082/order/cancel");

                from("direct:refundPayment")
                        .setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http.HttpMethods.POST))
                        .log("Cancelling payment")
                        .to("http://localhost:8083/payment/cancel");

                from("direct:cancelReservation")
                        .setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http.HttpMethods.POST))
                        .log("Cancelling reservation")
                        .to("http://localhost:8084/item/release");

                from("direct:cancelDelivery")
                        .setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http.HttpMethods.POST))
                        .log("Cancelling delivery")
                        .to("http://localhost:8081/delivery/cancel");
            }
        };
    }
}