package at.aigner.subtitle;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IDCGAigner on 25.12.2015.
 */
public class Entry {

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

    public void setText(List<String> text) {
        this.text = text;

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
        StringBuffer b = new StringBuffer();
        String newline = System.getProperty("line.separator");
        b.append(id).append(newline);
        b.append(start).append(" --> ").append(end).append(newline);
        for (String s : text) {
            b.append(s).append(newline);
        }
        return b.toString();
    }
}
