package shobhik.exampletodolist;

/**
 * Created by Shobhik Ghosh on 3/13/2016.
 */
public class ListItemModel {
    String title;
    String text;

    public ListItemModel() {
    }

    public ListItemModel(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ListItemModel{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
