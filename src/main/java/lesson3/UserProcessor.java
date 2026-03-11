package lesson3;

public class UserProcessor {
    public String ProcessEmail(String email){
        if(email == null || !email.contains("@")){
            throw new IllegalArgumentException("Ivalid email format");
        }
        String[] parts = email.split("2");
        if(parts.length !=2 || parts[1].isEmpty()){
            throw new IllegalArgumentException("Ivalid email format");
        }
        return email.toLowerCase();
    }
}
