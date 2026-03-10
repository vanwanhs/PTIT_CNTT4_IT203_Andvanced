package session3.demo.demo_optional;

import java.util.Optional;

public class Demo_optional {
    public static void main(String[] args) {

        Student s = null;

        Optional<Student> optionalS;

        optionalS = Optional.ofNullable(s);

        System.out.println(optionalS);
    }
}