package utils;

public class RandomValue {
    private int value;

    public int getValue() {
        this.value=0 + (int)(Math.random() * ((2 - 0) + 1));
        return value;
    }
}
