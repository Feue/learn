package Foundation.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.junit.Test;

/**
 * 以 enum 方式定义的常量使代码更具可读性，允许进行编译时检查，预先记录可接受值的列表，并避免由于传入无效值而引起的意外行为。
 */
public class Pizza {
    private PizzaStatus status;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum PizzaStatus {
        ORDERED (5){
            @Override
            public boolean isOrdered() {
                return true;
            }
        },
        READY (2){
            @Override
            public boolean isReady() {
                return true;
            }
        },
        DELIVERED (0){
            @Override
            public boolean isDelivered() {
                return true;
            }
        };

        private int timeToDelivery;

        public boolean isOrdered() {return false;}

        public boolean isReady() {return false;}

        public boolean isDelivered(){return false;}

        public int getTimeToDelivery() {
            return timeToDelivery;
        }

        PizzaStatus (int timeToDelivery) {
            this.timeToDelivery = timeToDelivery;
        }
    }

    public boolean isDeliverable() {
        return this.status.isReady();
    }

    public void printTimeToDeliver() {
        System.out.println("Time to delivery is " +
                this.getStatus().getTimeToDelivery());
    }

    private PizzaStatus getStatus() {
        return status;
    }

    private void setStatus(PizzaStatus status) {
        this.status = status;
    }

    public static void main(String[] args) {
        System.out.println(PizzaStatus.ORDERED.name()); // ORDERED
        System.out.println(PizzaStatus.ORDERED); // ORDERED
        System.out.println(PizzaStatus.ORDERED.name().getClass()); // class java.lang.String
        System.out.println(PizzaStatus.ORDERED.getClass()); // class Foundation.Enum.enumTest$PizzaStatus

        PizzaStatus pizza = null;
        //System.out.println(pizza.equals(PizzaStatus.DELIVERED)); // 空指针异常PizzaStatus
        System.out.println(PizzaStatus.DELIVERED.equals(pizza)); // 正常运行
        System.out.println(pizza == PizzaStatus.DELIVERED); // 正常运行
    }

//    public void jsonTest() {
//        Pizza pz = new Pizza();
//        pz.setStatus(Pizza.PizzaStatus.READY);
//        System.out.println(Pizza.getJsonString(pz));
//    }
}
