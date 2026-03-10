package session3.demo.demo_stream;

import java.util.Arrays;
import java.util.List;

public class Demo_Sort {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Nguyễn Văn Ánh","Trần Thị Ánh", "Cao Minh Anh", "Chu Nguyên Chương");
        names.stream().sorted().forEach(e -> System.out.println(e));
        names.stream().sorted().forEach(System.out::println);

    }

}
