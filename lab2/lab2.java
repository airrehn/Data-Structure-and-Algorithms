import java.io.*;
import java.io.IOException;
import java.util.HashMap;

class spelling {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int lines = Integer.parseInt(br.readLine());
        HashMap<String,String> map = makemap();
        for (int i=0;i<lines;i++) {
            String line = br.readLine();
            pw.print(String.format("Case #%d: ", i+1));
            String prevch =" ";
            for (int j=0; j < line.length(); j++) {
                Character temp = line.charAt(j);
                String ch = temp.toString();
                Character char1 = map.get(ch).charAt(0); //gets the first character of the number/string returned
                Character char2 = map.get(prevch).charAt(0);
                if (char1.equals(char2)) pw.print(" ");
                pw.print(map.get(ch));
                prevch = ch;
            }
            pw.println();       

        }
        br.close();
        pw.close();
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
    

