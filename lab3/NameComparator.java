import java.util.Comparator;
public class NameComparator implements Comparator<Name> {
    public int compare(Name n1, Name n2) {


        if (n1.getName().equals(n2.getName())) {
            return 0;
        } else {

            return n1.getName().compareTo(n2.getName());
        }



    }
    
}
