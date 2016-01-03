package at.aigner.subtitle;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IDCGAigner on 25.12.2015.
 */
public class Entry {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(Entry.TIME_PATTERN);
    public static final String TIME_SEP = " --> ";

    private static final String TIME_PATTERN = "HH:mm:ss,SSS";

    private String id;
    private LocalTime start;
    private LocalTime end;
    private List<String> text = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public List<String> getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id='" + id + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", text=" + text +
                '}';
    }

    public String out() {
        StringBuilder b = new StringBuilder();
        String newline = System.getProperty("line.separator");
        b.append(id).append(newline);
        b.append(start.format(DATE_TIME_FORMATTER)).append(TIME_SEP).append(end.format(DATE_TIME_FORMATTER)).append(newline);
        for (String s : text) {
            b.append(s).append(newline);
        }
        return b.toString();
    }
}
