package proj.kolot.com.discountatb.repository.retrofit;

/**
 * Created by Kolot Liza on 9/11/18.
 */
public enum Category {
    ALL(0, "Все товары"),
    KITCHEN(1, "Для кухни"),
    CLOTHES(2, "Одежда"),
    CHILDREN(3, "Детские товары"),
    HOUSEHOLD(4, "Хозяйственые товары");

    private int value;

    private String description;

    Category(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

