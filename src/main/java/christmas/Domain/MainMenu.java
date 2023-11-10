package christmas.Domain;

public enum MainMenu {
    T_BONE_STEAK("티본스테이크", 55000),
    LIB("바비큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000);

    private final String name;
    private final int price;

    MainMenu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

}
