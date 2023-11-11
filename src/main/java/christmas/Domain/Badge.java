package christmas.Domain;

import java.util.function.Function;

public enum Badge {
    STAR("별", price -> price >= 5000 && price < 10000),
    TREE("트리", price -> 10000 <= price && price < 20000),
    SANTA("산타", price -> 20000 <= price);
    private String rank;
    private Function<Integer, Boolean> expression;

    Badge(String rank, Function<Integer, Boolean> expression) {
        this.rank = rank;
        this.expression = expression;
    }

    public boolean apply(int price) {
        return expression.apply(price);
    }

    @Override
    public String toString() {
        return rank;
    }
}
