package session3.demo.demo_stream;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Demo_filter {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Nguyễn Văn Ánh","Nguyễn Văn B","Trịnh Quốc Thắng","Trần Minh Anh");
        names.stream()
                .filter(name ->name.startsWith("Nguyễn"))
                .forEach(e-> System.out.println(e));
    }
}
