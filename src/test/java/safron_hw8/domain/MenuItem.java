package safron_hw8.domain;

public enum MenuItem {
    SEARCH("Поиск"),
    IMAGES("Картинки"),
    VIDEO("Видео");

    private String desc;

    MenuItem(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
