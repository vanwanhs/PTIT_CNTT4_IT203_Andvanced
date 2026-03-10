package session3.demo.demo_stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Demo_Map {
    public static void main(String[] args) {

        // Tạo danh sách ban đầu
        List<String> user = Arrays.asList("Cuong","Tien","Anhs");

        // Stream danh sách user
        // map(): biến đổi từng phần tử sang chữ in hoa
        // collect(): thu kết quả lại thành một List mới
        List<String> newUser = user.stream()
                .map(name -> name.toUpperCase())
                .collect(Collectors.toList());

        // In danh sách mới sau khi đã biến đổi
        newUser.forEach(e -> System.out.println(e));

        user.stream()
                .map(name -> name.toUpperCase())
                .forEach(e -> System.out.println(e));
    }
}