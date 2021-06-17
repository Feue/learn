package DesignPattern.Singleton;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SingletonTest {
    public static void main(String[] args) {
        PizzaDeliveryStrategy deliveryStrategy = PizzaDeliverySystemConfiguration.getInstance().getDeliveryStrategy();
        // 通过 PizzaDeliverySystemConfiguration.getInstance() 获取的就是单例的 PizzaDeliverySystemConfiguration
    }

    @Test
    public void givenPizaOrder_whenDelivered_thenPizzaGetsDeliveredAndStatusChanges() {
        Pizza pz = new Pizza();
        pz.setStatus(Pizza.PizzaStatus.READY);
        pz.deliver();
        assertTrue(pz.getStatus() == Pizza.PizzaStatus.DELIVERED);
    }

}
