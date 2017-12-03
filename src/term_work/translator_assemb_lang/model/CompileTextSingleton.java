package term_work.translator_assemb_lang.model;

import javafx.stage.Stage;
import term_work.translator_assemb_lang.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class CompileTextSingleton {
    private Main main;
    private Stage stage;
    private static CompileTextSingleton instance;
    private CompileTextSingleton(){}
    public static CompileTextSingleton getInstance(){
        if(instance == null){
            instance = new CompileTextSingleton();
        }
        return instance;
    }

    private String compileText;

    private List<String> compileTextList = null;
    private List<String> listWithoutComments = null;
    private List<String> listWithoutEmptyElementsAndComments = null;
    private List<LineWithMnem> lineWithMnems = new ArrayList<>();

    private List<String> outputMnemosLines = null;

    private List<String> mnemonicList = null;
    private List<String> specialWords = null;
    private List<String> variables = null;

    public void setCompileText(String compileText, boolean startPerform) {
        this.compileText = compileText;
        if(startPerform){
            startPerformWithCompileText();
        }
    }

    private void startPerformWithCompileText(){
        compileTextList = retStringList(compileText);
        listWithoutComments = retListWithoutComments(compileTextList);
        listWithoutEmptyElementsAndComments = deleteEmptyElements(listWithoutComments);
        outputMnemosLines = retOutMnemLines();
        separateOnMnemAndSpecList();
    }

    public void separateOnMnemAndSpecList() {
        specialWords = new ArrayList<>();
        mnemonicList = new ArrayList<>();
        variables = new ArrayList<>();

        divideList();
        work(mnemonicList);
    }
    private void work(List<String> list) {
        Set<Integer> lengthWord = new TreeSet<>();
        for (String s : Store.getMn()) {
            lengthWord.add(s.length());
        }
        getObjectiveCode(list, Store.getMn(), lengthWord);
    }
    private void getObjectiveCode(List<String> list, String[] mnemons, Set<Integer> lengthMnem) {
        Integer[] len = lengthMnem.toArray(new Integer[lengthMnem.size()]);
        String[] operator = new String[list.size()];
        String[] operands = new String[list.size()];
        int k = 0;
        for (String line : list) {
            for (Integer mem : len) {
                for (String mnemon : mnemons) {
                    String tmp = line.substring(0, mem);
                    if (tmp.equalsIgnoreCase(mnemon)) {
                        operator[k] = tmp;
                        operands[k] = line.substring(mem);
                    }
                }
            }
            k++;
        }
        workWithOperands(operator, operands);
    }
    private void workWithOperands(String[] operators,String[] operands){
        List<String> oper1 = new ArrayList<>();
        List<String> oper2 = new ArrayList<>();

        for (String operand :operands) {
            if(operand.contains(",")){
                oper1.add(operand.split(",")[0]);
                oper2.add(operand.split(",")[1]);
            }else {
                oper1.add(operand);
                oper2.add("");
            }
        }
        List<String> list = new ArrayList<>(List.of(operators));
        for(int i = 0; i < list.size(); i++){
            lineWithMnems.add(new LineWithMnem(list.get(i), oper1.get(i), oper2.get(i)));
        }
    }

    private String[] retArrayStrings(String text){
        return text.toUpperCase().split("\n");
    }
    private List<String> retStringList(String text){
        List<String> list = new ArrayList<>();
        for (String s:retArrayStrings(text)) {
            list.add(s.trim());
        }
        return list;
    }
    private List<String> retListWithoutComments(List<String> stringList){
        List<String> tmpMap = new ArrayList<>();
        for (String s:stringList) {
            int pos = s.lastIndexOf(";");
            if(pos != 0){
                switch (pos){
                    case -1: tmpMap.add(s.trim());
                        break;
                    default:{
                        String str = s.substring(0, pos);
                        tmpMap.add(str.trim());
                    }
                }
            }
        }

        return tmpMap;
    }
    private List<String> deleteEmptyElements(List<String> list){
        List<String> stringList = new ArrayList<>(list);
        for (int i = 0; i < stringList.size(); i++){
            if(stringList.get(i).equalsIgnoreCase("")){
                stringList.remove(i);
                i--;
            }
        }
        return stringList;
    }
    public List<String> retListWithoutSpaces(List<String> strings) {
        StringBuilder stringBuilder = new StringBuilder();
        strings.forEach(str -> stringBuilder.append(str).append("\n"));
        String[] s = deleteSpaces(stringBuilder.toString()).split("\n");
        return new ArrayList<>(List.of(s));
    }
    private String deleteSpaces(String text) {
        return text.replace(" ", "");
    }
    public void divideList() {
        List<String> list = new ArrayList<>(retListWithoutSpaces(listWithoutEmptyElementsAndComments));
        Pattern pattern1 = Pattern.compile(Store.getMnemoRegExe());
        Pattern pattern2 = Pattern.compile(Store.getSpecialWordRegExe());

        list.forEach(el -> {
            if(!el.equalsIgnoreCase("END")){
                if(pattern1.matcher(el).find()){
                    mnemonicList.add(el);
                }else if(pattern2.matcher(el).find()){
                    specialWords.add(el);
                }else {
                    variables.add(el);
                }
            }else {
                return;
            }
        });
    }
    private List<String> retOutMnemLines(){
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(Store.getMnemoRegExe());
        listWithoutEmptyElementsAndComments.forEach(el -> {
            if(pattern.matcher(el).find()){
                list.add(el);
            }
        });
        return list;
    }

    public List<LineWithMnem> getLineWithMnems() {
        return lineWithMnems;
    }
    public List<String> getCompileTextList() {
        return compileTextList;
    }
    public List<String> getOutputMnemosLines() {
        return outputMnemosLines;
    }
    public List<String> getListWithoutComments() {
        return listWithoutComments;
    }
    public List<String> getListWithoutEmptyElementsAndComments() {
        return listWithoutEmptyElementsAndComments;
    }

    public List<String> getMnemonicList() {
        return mnemonicList;
    }
    public List<String> getSpecialWords() {
        return specialWords;
    }
    public List<String> getVariables() {
        return variables;
    }
    public void setMain() {
        this.main = main;
    }
    public void clear(){
        this.compileText = "";
        this.compileTextList.clear();
        this.lineWithMnems.clear();
        this.listWithoutComments.clear();
        this.listWithoutEmptyElementsAndComments.clear();
        this.mnemonicList.clear();
        this.specialWords.clear();
        this.variables.clear();
        this.outputMnemosLines.clear();
    }

}
