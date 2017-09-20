import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map.Entry;
/**
 * Does things.
 *@see <a href="https://github.com/ghli2/Lab3">Code on GitHub</a>
 */
public class NewClass {
    /**
     * Does things.
     *
     * @param unused unused input arguments
     * @throws Exception an exception
     */
    public static void main(final String[] unused) throws Exception {
        String c =
                   getText("http://erdani.com/tdpl/hamlet.txt").toLowerCase().replaceAll("[^a-z ']",
                                                                                          "");
        String[] sContent = String.join(" ", c.split("\n")).split(" ");
        HashMap<String, Integer> table = new HashMap<String, Integer>();
        for (String s : sContent) {
            if (!table.containsKey(s)) {
                table.put(s, 1);
            } else {
                table.put(s, table.get(s) + 1);
            }
        }
        table.remove("");
        System.out.printf("prince appeared %d times", table.get("prince"));
        //ArrayList<Entry<String, Integer>> sorted = sort(table);
        //Collections.reverse(sorted);
        //for (Entry<String, Integer> mem : sorted) {
            //System.out.println(mem.getKey() + " : " + mem.getValue());
        //}
    }
    /**
     *
     * @param hm hash map to sort by value
     * @return sorted arraylist by value
     */
    public static ArrayList<Entry<String, Integer>> sort(final HashMap<String, Integer> hm) {
        ArrayList<Entry<String, Integer>> arr = new ArrayList<>(hm.entrySet());
        Collections.sort(arr, new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(final Entry<String, Integer> arg0,
                               final Entry<String, Integer> arg1) {
                return arg0.getValue() - arg1.getValue();
            }
        });
        return arr;
    }
    /**
     *
     * @param url the url of the website to read
     * @return a string representing the content pointed to by the url
     * @throws Exception an exception
     */
    public static String getText(final String url) throws Exception {
        URL website = new URL(url);
        URLConnection con = website.openConnection();
        con.setDoOutput(true); // we want the response
        con.setRequestProperty("Cookie", "myCookie=test123");
        con.connect();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                    con.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        return response.toString();
    }
}
