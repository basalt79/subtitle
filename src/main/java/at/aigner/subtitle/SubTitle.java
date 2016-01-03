package at.aigner.subtitle;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SubTitle {

    public static void main(String[] args) throws Exception {
        SubTitle m = new SubTitle();
        m.start("inout.srt", System.out);
    }

    private void start(String filename, PrintStream out) throws Exception {
        List<Entry> entryList = readEntries(new File(filename));
        for (Entry entry : entryList) {
            if (entry.getStart() != null && entry.getEnd() != null) {
                entry.setStart(adjust(entry.getStart()));
                entry.setEnd(adjust(entry.getEnd()));
            }
            out.println(entry.out());
        }
    }

    private LocalTime adjust(LocalTime localTime) {
//        return localTime.plusHours(1).minusMinutes(3).plusSeconds(54);
        return localTime.minusSeconds(2);
    }

    private List<Entry> readEntries(File f) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
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
                        String startString = line.substring(0, line.indexOf(Entry.TIME_SEP));
                        e.setStart(LocalTime.parse(startString, Entry.DATE_TIME_FORMATTER));
                        String endString = line.substring(line.indexOf(Entry.TIME_SEP) + Entry.TIME_SEP.length());
                        e.setEnd(LocalTime.parse(endString, Entry.DATE_TIME_FORMATTER));
                        break;
                    default:
                        e.getText().add(line);
                }
            }
        }
        return result;
    }
}
