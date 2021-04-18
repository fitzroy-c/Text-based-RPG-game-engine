//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Json {
//    /**
//     * Saves this collection to the specified JSON file.
//     *
//     * @param file The path to a file.
//     */
//    public void saveToJSONFile(File file) {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//        try(FileWriter fw = new FileWriter(file)){
//            gson.toJson(books, fw);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * Load a pre-existing book collection from a JSON file.
//     *
//     * @param file The file to load from. This is guaranteed to exist.
//     * @return An initialised book collection.
//     */
//    public static BookCollection loadFromJSONFile(File file) {
//        Gson gson = new Gson();
//        JsonReader jsonReader = null;
//
//        final Type CUS_LIST_TYPE = new TypeToken<List<Book>>() {}.getType();
//        //or TypeToken.getParameterized(ArrayList.class, PersonJSON.class).getType();
//
//        try{
//            jsonReader = new JsonReader(new FileReader(file));
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        BookCollection bc = new BookCollection(gson.fromJson(jsonReader, CUS_LIST_TYPE));
//        return bc;
//    }
}
