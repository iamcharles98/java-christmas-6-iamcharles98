package christmas.Utils;

import static christmas.Utils.Constants.AMOUNT_INDEX;
import static christmas.Utils.Constants.NAME_AND_AMOUNT_DELIMITER;
import static christmas.Utils.Constants.NAME_INDEX;

import christmas.Domain.Menu;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InputResolver {

    private InputResolver() {
    }

    public static Map<Menu, Integer> stringToOrderMap(String[] inputMenu)
            throws IllegalArgumentException {
        final Map<Menu, Integer> orders = new HashMap<>();
        for (String token : inputMenu) {
            addIfPresentMenu(orders, token);
        }
        return orders;
    }

    private static void addIfPresentMenu(Map<Menu, Integer> orders, String token) throws IllegalArgumentException {
        String[] order = token.split(NAME_AND_AMOUNT_DELIMITER);
        Optional<Menu> optionalMenu = Menu.getMenuByNameIfPresent(order[NAME_INDEX]);
        if (optionalMenu.isEmpty()) {
            throw new IllegalArgumentException();
        }
        orders.put(optionalMenu.get(), Integer.parseInt(order[AMOUNT_INDEX]));
    }


}
