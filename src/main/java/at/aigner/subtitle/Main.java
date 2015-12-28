package at.aigner.subtitle;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IDCGAigner on 25.12.2015.
 */
public class Main {


    public static void main(String[] args) throws Exception {
        Main m = new Main();
        m.start();
    }

    private void start() throws Exception {
        File f = new File("inout.srt");

        List<Entry> read = readEntries(f);
//        for (Entry entry : read) {
//            System.out.println(entry);
//        }
        for (Entry entry : read) {

            LocalTime start = entry.getStart();
            LocalTime end = entry.getEnd();
            if (start != null && end != null) {
                entry.setStart(start.plusHours(1).minusMinutes(3).plusSeconds(54));
                entry.setEnd    (end.plusHours(1).minusMinutes(3).plusSeconds(54));
            }
        }

        for (Entry entry : read) {
            System.out.println(entry.out());
        }
    }

    private List<Entry> readEntries(File f) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader(f));

        List<Entry> result = new ArrayList<>();

        Entry e = new Entry();
        int counter = 0;
        while (br.ready()) {
            String line = br.readLine();
            if (StringUtils.isEmpty(line)) {
                result.add(e);
                e = new Entry();
                counter = 0;
            } else {
                counter++;
                switch (counter) {
                    case 1:
                        e.setId(line);
                        break;
                    case 2:
                        DateTimeFormatterBuilder b = new DateTimeFormatterBuilder();
                        String magix = " --> ";
                        String startString = line.substring(0, line.indexOf(magix));
                        LocalTime start = LocalTime.parse(startString, DateTimeFormat.forPattern("HH:mm:ss,SSS"));
                        e.setStart(start);
                        String endString = line.substring(line.indexOf(magix) + magix.length());
                        LocalTime end = LocalTime.parse(endString, DateTimeFormat.forPattern("HH:mm:ss,SSS"));
                        e.setEnd(end);
                        break;
                    default:
                        e.getText().add(line);
                }
            }
        }

        return result;
    }
}
