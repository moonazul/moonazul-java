import com.esotericsoftware.yamlbeans.YamlReader;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


public class Moonazul {
    public Map<String, String> getProvider(final String filename) {
        Map<String, String> result = null;

        try {
            String fullURL = String.format("https://raw.githubusercontent.com/moonazul/moonazul-data/master/%s.yaml", filename);
            String content = getRawContentFromURL(fullURL);
            YamlReader reader = new YamlReader(content);
            Object object = reader.read();

            result = (Map<String, String>) object;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return result;
    }

    private String getRawContentFromURL(final String url) throws Exception {
        URL providerLocation = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) providerLocation.openConnection();
        InputStream stream = connection.getInputStream();

        return this.getStringFromStream(stream);
    }

    private String getStringFromStream(final InputStream stream) throws IOException {
        String result = null;

        if (stream != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[2048];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                int counter;
                while ((counter = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, counter);
                }
            } finally {
                stream.close();
            }
            result = writer.toString();
        }

        return result;
    }


    private static Boolean validate(String test) {
        return true;
    }

    public static void main(String[] args) {
        Moonazul test = new Moonazul();
        Map<String, String> cases = test.getProvider("zip");

        for (String item : cases.keySet()) {
            String expectation = cases.get(item);


         }
    }
}
