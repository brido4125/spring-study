package com.example.designpattern.singleton;

public class SyncChocolateBoiler {
    private boolean empty;
    private boolean boiled;
    private static final SyncChocolateBoiler uniqueInstance = new SyncChocolateBoiler();

    private SyncChocolateBoiler() {
        empty = true;
        boiled = false;
    }

    public static SyncChocolateBoiler getInstance() {
        return uniqueInstance;
    }

    public void fill() {
        System.out.println("ChocolateBoiler.fill");
        if (isEmpty()) {
            empty = false;
            boiled = false;
            // 보일러에 우유/초콜릿을 혼합한 재료를 집어넣음
        }
    }

    public void drain() {
        System.out.println("ChocolateBoiler.drain");
        if (!isEmpty() && isBoiled()) {
            // 끓인 재료를 다음 단계로 넘김
            empty = true;
        }
    }

    public void boil() {
        System.out.println("ChocolateBoiler.boil");
        if (!isEmpty() && !isBoiled()) {
            // 재료를 끓임
            boiled = true;
        }
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isBoiled() {
        return boiled;
    }
}
