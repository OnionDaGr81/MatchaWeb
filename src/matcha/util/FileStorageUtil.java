package matcha.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class FileStorageUtil {
    // Menggunakan GsonBuilder untuk format JSON yang rapi (pretty printing)
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Menyimpan ArrayList ke dalam file JSON.
     */
    public static <T> boolean saveToFile(ArrayList<T> list, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(list, writer);
            return true;
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data ke " + filePath + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Membaca file JSON dan mengembalikannya sebagai ArrayList.
     * Penggunaan: ArrayList<Talent> talents = FileStorageUtil.readFromFile("data/talents.json", new TypeToken<ArrayList<Talent>>(){}.getType());
     */
    public static <T> ArrayList<T> readFromFile(String filePath, Type typeOfT) {
        try (FileReader reader = new FileReader(filePath)) {
            ArrayList<T> result = gson.fromJson(reader, typeOfT);
            return result != null ? result : new ArrayList<>();
        } catch (IOException e) {
            // Jika file belum ada, kembalikan list kosong
            return new ArrayList<>();
        }
    }
}