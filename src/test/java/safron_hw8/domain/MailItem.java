package safron_hw8.domain;

public enum MailItem {
    SOCIALNET("Соцсети", "technodom", ".Results-queryText"),
    VIDEO("Видео", "Технодом", ".Caption-nick"),
    NEWS("Новости", "Последние публикации", ".NewsResults-lastNews");

    private String desc;

    private String resultSelector;

    private String select;

    MailItem(String desc, String resultSelector, String select) {
        this.desc = desc;
        this.resultSelector = resultSelector;
        this.select = select;
    }

    public String getDesc() {
        return desc;
    }

    public String getResultSelector() {
        return resultSelector;
    }

    public String getSelect() {
        return select;
    }
}
