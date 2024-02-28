import com.example.cs304.common.Util;
import com.example.cs304.entity.CourseForTimetable;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) throws IOException, URISyntaxException {
       String[] x = "[1-16周][一教307][1-2节]".split("]");
       for (String x1 : x) {
           System.out.println(x1);
       }
    }
}
