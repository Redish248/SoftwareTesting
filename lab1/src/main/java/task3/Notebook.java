package task3;

import lombok.Data;

@Data
public class Notebook {

    private Content content;

    public Notebook(Content content) {
        this.content = content;
    }

    public enum Content {
        POEMS, STORIES, ADDRESSES, PHONES, FUTURE_PLANS, TIMETABLE
    }

}
