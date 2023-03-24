import java.util.HashMap;

public class playground {
    public static void main(String args[]) {
        HashMap<String,String> map = makemap(); //defined below
        System.out.println(map.get("f"));
        
    }   
    static HashMap<String, String> makemap() {
        HashMap<String, String> map = new HashMap<>();
        Integer i = 2;
        int counter=0;
        for (Character ch = 'a'; ch<='z'; ch++) {
            counter ++;
            map.put(ch.toString(), i.toString().repeat(counter));
            
            if (i == 7 || i == 9) {
                if (counter == 4) {
                    counter = 0;
                    i++;
                }
                
            } else {
                    if (counter == 3) { 
                counter = 0;
                i++;
                }
            
            }
        }      
        map.put(" ", "0");
        return map;
    } 
}

