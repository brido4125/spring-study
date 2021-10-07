package hellojpa;

public class ValueMain {
    public static void main(String[] args) {
        int a = 10;
        int b = 10;

        System.out.println(" a == b " + ( a == b));//true

        Address address1 = new Address("daegu", "daebak", "23211");
        Address address2 = new Address("daegu", "daebak", "23211");

        System.out.println("address1 == address2 : " + (address1 == address2));//false
        System.out.println("address1 == address2 : " + (address1.equals(address2)));//override 해야 true 나옴.

    }
}
