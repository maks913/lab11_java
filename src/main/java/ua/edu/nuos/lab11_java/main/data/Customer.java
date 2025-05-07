package ua.edu.nuos.lab11_java.main.data;

import lombok.*;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor

public class Customer implements Serializable {
    private int id;
    private String customerName;
    private String city;
    private int cardNumber;
    private int accBalance;
    private int numberOfPurchases;
    private int totalPurchases;


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customerName=" + customerName +
                ", city=" + city +
                ", cardNumber=" + cardNumber +
                ", accBalance=" + accBalance +
                ", numberOfPurchases=" + numberOfPurchases +
                ", totalPurchases=" + totalPurchases +
                '}';
    }


}