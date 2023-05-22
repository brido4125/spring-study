package sample.cafekiosk.unit;

import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;

public class CafeKioskRunner {

    public static void main(String[] args) {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());
        System.out.println("Americano 추가");
        cafeKiosk.add(new Latte());
        System.out.println("Latte 추가");

        System.out.println("총 가격: " + cafeKiosk.getTotalPrice());

    }
}
