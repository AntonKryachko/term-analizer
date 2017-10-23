package term_work.translator_assemb_lang.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompileTextSingleton {
    private static CompileTextSingleton instance;
    private CompileTextSingleton(){}
    public static CompileTextSingleton getInstance(){
        if(instance == null){
            instance = new CompileTextSingleton();
        }
        return instance;
    }
    private List<String> stringList = null;
    private String compileText;

    public void setCompileText(String compileText) {
        this.compileText = compileText;
        createStringList();
    }
    public String getCompileText() {
        return compileText;
    }

    public List<String> getStringList() {
        return stringList;
    }

    private void createStringList(){
        String[] strings = compileText.replace(" ", "").split("\n");
        stringList = new ArrayList<>(List.of(strings));
        for (int i = 0; i < stringList.size(); i++) {
            if(stringList.get(i).equals("")){
                stringList.remove(i);
            }
        }
//        System.out.println("size = " + stringList.size() + "\nList " + stringList.toString());
    }
}
