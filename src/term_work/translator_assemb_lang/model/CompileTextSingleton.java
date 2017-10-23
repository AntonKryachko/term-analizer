package term_work.translator_assemb_lang.model;

import java.util.ArrayList;
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

    private String compileText;
    private List<String> stringList = null;
    private List<String> listWithoutComments = null;

    public void setCompileText(String compileText) {
        this.compileText = compileText;
        stringList = createStringList(compileText);
        listWithoutComments = retListWithoutComments(stringList);
    }
    public String getCompileText() {
        return compileText;
    }
    public List<String> getStringList() {
        return stringList;
    }
    public List<String> getListWithoutComments() {
        return listWithoutComments;
    }

    private String deleteSpaces(String text){
        return text.replace(" ", "");
    }
    private String[] createArrayStrings(String text){
        return text.split("\n");
    }
    private List<String> deleteEmptyElements(String[] strings){
        List<String> stringList = new ArrayList<>(List.of(strings));
        for (int i = 0; i < stringList.size(); i++) {
            if(stringList.get(i).equals("")){
                stringList.remove(i);
            }
        }
        return stringList;
    }
    private List<String> createStringList(String compileText){
        String tmp = deleteSpaces(compileText);
        String[] tmpArr = createArrayStrings(tmp);
        return deleteEmptyElements(tmpArr);
    }

    private List<String> retListWithoutComments(List<String> stringList){
        List<String> tmpList = new ArrayList<>();
        stringList.forEach(s -> {
            int pos = s.lastIndexOf(";");
            if(pos != 0){
                switch (pos){
                    case -1: tmpList.add(s);
                        break;
                    default:{
                        String str = s.substring(0, pos);
                        tmpList.add(str);
                    }
                }
            }
        });
        return tmpList;
    }
}
