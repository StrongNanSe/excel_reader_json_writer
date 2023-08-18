import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Objects;

public class Write {
    public static void writeJson() {
        String jsonPath = findJson(System.getProperty("user.dir"));
        String printJsonPath = System.getProperty("user.dir") + "\\modified_license_attribute.json";
        FileReader fileReader = null;
        FileWriter fileWriter = null;
//        LicenseValue licenseValue;

        try {
            fileReader = new FileReader(jsonPath);

            JSONParser jsonParser = new JSONParser();
            JSONObject licenseAttribute = (JSONObject) jsonParser.parse(fileReader);
            JSONArray licenseArray = (JSONArray) licenseAttribute.get("licenseattribute");
            ArrayList<String> insertLicenseList = new ArrayList<>();

            for (String keyLicenseName : Read.LICENSE_VALUE_MAP.keySet()) {
                LicenseValue licenseValue = Read.LICENSE_VALUE_MAP.get(keyLicenseName);
                for (int i = 0; i < licenseArray.size(); i ++) {
                    JSONObject license = (JSONObject) licenseArray.get(i);
                    String name = (String) license.get("LICENSE_NAME");

                    LinkedHashMap<String, String> sortLicense = new LinkedHashMap<>();
                    if (name.equals(licenseValue.getName())) {
                        sortLicense.put("1", licenseValue.c1);
                        sortLicense.put("2",licenseValue.c2);
                        sortLicense.put("3",licenseValue.c3);
                        sortLicense.put("4",licenseValue.c4);
                        sortLicense.put("5",licenseValue.c5);
                        sortLicense.put("6",licenseValue.c6);
                        sortLicense.put("7",licenseValue.c7);
                        sortLicense.put("8",licenseValue.c8);
                        sortLicense.put("9",licenseValue.c9);
                        sortLicense.put("10",licenseValue.c10);
                        sortLicense.put("11",licenseValue.c11);
                        sortLicense.put("12",licenseValue.c12);
                        sortLicense.put("13",licenseValue.c13);

                        license.put("DATA", sortLicense);
                        insertLicenseList.add(name);

                        System.out.print(insertLicenseList.size() + " ");
                        System.out.println(" Modified " + name);

                        break;
                    }
                }
            }

            for (String licenseKey : Read.LICENSE_VALUE_MAP.keySet()) {
                if (!insertLicenseList.contains(licenseKey)) {
                    LicenseValue licenseValue = Read.LICENSE_VALUE_MAP.get(licenseKey);

                    if (licenseValue.getName() != null) {
                        LinkedHashMap<String, String> sortLicense = new LinkedHashMap<>();
                        sortLicense.put("LICENSE_NAME", licenseValue.getName());

                        sortLicense.put("DATA","");

                        sortLicense.put("LICENSE_IDENTIFIER", licenseValue.getIdentifier());

                        LinkedHashMap<String, String> sortData = new LinkedHashMap<>();
                        sortData.put("1", licenseValue.c1);
                        sortData.put("2", licenseValue.c2);
                        sortData.put("3", licenseValue.c3);
                        sortData.put("4", licenseValue.c4);
                        sortData.put("5", licenseValue.c5);
                        sortData.put("6", licenseValue.c6);
                        sortData.put("7", licenseValue.c7);
                        sortData.put("8", licenseValue.c8);
                        sortData.put("9", licenseValue.c9);
                        sortData.put("10", licenseValue.c10);
                        sortData.put("11", licenseValue.c11);
                        sortData.put("12", licenseValue.c12);
                        sortData.put("13", licenseValue.c13);

                        JSONObject licenseObject = new JSONObject(sortLicense);
                        licenseObject.put("DATA", sortData);

                        licenseArray.add(licenseObject);

                        System.out.println("added " + licenseKey);
                    }
                }
            }

            Gson prettyJson = new GsonBuilder().setPrettyPrinting().create();
            fileWriter = new FileWriter(printJsonPath);
            fileWriter.write(prettyJson.toJson(licenseAttribute));
            fileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }

                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static String findJson(String dirPath) {
        File file = new File(dirPath);
        FileFilter filter = f -> f.getName().endsWith("json");
        File[] files = file.listFiles(filter);

        return Objects.requireNonNull(files)[0].toString();
    }
}
