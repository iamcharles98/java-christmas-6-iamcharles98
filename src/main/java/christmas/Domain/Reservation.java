package christmas.Domain;

import static christmas.Utils.Constants.LINE;
import static christmas.Utils.Constants.MAX_AMOUNT;
import static christmas.Utils.Constants.SPACE;

import christmas.Utils.ErrorMessage;
import java.util.Map;


public class Reservation {
    private Map<Menu, Integer> menus;
    private int day;

    public Reservation(Map<Menu, Integer> menus, int day) {
        validateMenus(menus);
        this.menus = menus;
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public int NumberOfDessertMenu() {
        int count = 0;
        for (Menu menu : menus.keySet()) {
            if (menu.getType().equals(MenuType.DESSERT)) {
                count += menus.get(menu);
            }
        }
        return count;
    }

    public int NumberOfMainMenu() {
        int count = 0;
        for (Menu menu : menus.keySet()) {
            if (menu.getType().equals(MenuType.MAIN)) {
                count += menus.get(menu);
            }
        }
        return count;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Menu menu : menus.keySet()) {
            totalPrice += (menu.getPrice() * menus.get(menu));
        }
        return totalPrice;
    }

    private void validateMenus(Map<Menu, Integer> menus) throws IllegalArgumentException {
        if (hasOnlyDrinks(menus)) {
            throw new IllegalArgumentException(ErrorMessage.ONLY_DRINK_ORDER.getMessage());
        }
        if (isOverOrder(menus)) {
            throw new IllegalArgumentException(ErrorMessage.OVER_ORDER.getMessage());
        }
    }

    private boolean hasOnlyDrinks(Map<Menu, Integer> menus) {
        return menus.keySet().stream()
                .filter(menu -> menu.getType().equals(MenuType.DRINK)).count() == menus.keySet().size();
    }

    private boolean isOverOrder(Map<Menu, Integer> menus) {
        int count = 0;
        for (Menu key : menus.keySet()) {
            count += menus.get(key);
        }
        return count > MAX_AMOUNT;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Menu menu : menus.keySet()) {
            stringBuilder.append(menu.getName()).append(SPACE).append(String.format("%d개", menus.get(menu)))
                    .append(LINE);
        }
        return stringBuilder.toString();
    }
}
