package term_work.translator_assemb_lang.model;

public class Test {
    public static void main(String[] args) {
        CompileTextSingleton text = CompileTextSingleton.getInstance();
        text.setCompileText(someText(), true);
        System.out.println(text.getCompileTextList().toString());
        System.out.println(text.getListWithoutComments().toString());
        System.out.println(text.getVariables().toString());
        System.out.println(text.getMnemonicList().toString());
        System.out.println(text.getSpecialWords().toString());
        CreateObjectiveCodeSingleton createObjectiveCodeSingleton = CreateObjectiveCodeSingleton.getInstance();
        createObjectiveCodeSingleton.setShift(Store.getValFromList(text.getSpecialWords(), "ORG"));
        createObjectiveCodeSingleton.perfWithMnemlines();
        createObjectiveCodeSingleton.getResults().forEach(c ->
                System.out.println(c.getAdress() + " | " + c.getObjectCode() + " | " + c.getProgCode()));
    }

    private static String someText2(){
        return "ns segment\n" +
                "    assume cs: ns, ds: ns, ss: ns\n" +
                "    org 100h\n" +
                "    ;Легенда:\n" +
                "    ;Р - регистр\n" +
                "    ;НО - непосредственный операнд\n" +
                "    ;ОП - оперативная панять (адресация базова-индексная)\n" +
                "    \n" +
                "    ; Задание\n" +
                "    ; MOV P, P\n" +
                "    ; MOV P, НО\n" +
                "    ; MOV P, ОП\n" +
                "    ; MOV ОП,P\n" +
                "    ; AND P, P\n" +
                "    ; AND P, НО\n" +
                "    ; AND P, ОП\n" +
                "    ; AND ОП,P\n" +
                "    ; SAR Р, Р\n" +
                "    ; SAR Р, НО\n" +
                "    ; JZ (метка)\n" +
                "     \n" +
                "    ;===============================\n" +
                "    ;реализация MOV   \n" +
                "    \n" +
                "    mov ax, bx   ;MOV P, P   \n" +
                "    \n" +
                "    mov bx, 5    ;MOV P, HO\n" +
                "    mov ax, 5            \n" +
                "    \n" +
                "    mov ax, [bx + si]  ; MOV P, ОП \n" +
                "    mov bx, [bx + di]\n" +
                "    mov dx, [bp + si]\n" +
                "    mov cx, [bp + di]\n" +
                "     \n" +
                "    mov [bx + si], ax  ; MOV ОП,P\n" +
                "    mov [bx + di], bx\n" +
                "    mov [bp + si], cx\n" +
                "    mov [bp + di], dx\n" +
                "    \n" +
                "    xor ax,ax\n" +
                "    xor bx,bx\n" +
                "    xor cx,cx\n" +
                "    xor dx,dx\n" +
                "    ;===============================\n" +
                "    ;реализация AND        \n" +
                "    \n" +
                "    mov ax, bx   ;MOV P, P\n" +
                "    mov al, bl\n" +
                "    mov bl, al\n" +
                "    mov dh, bh\n" +
                "\n" +
                "    mov bx, 5    ;MOV P, HO\n" +
                "    mov bh, 5\n" +
                "    mov ax, 5\n" +
                "    mov al, 5\n" +
                "\n" +
                "    mov ax, [bx + si]  ; MOV P, ОП\n" +
                "    mov ah, [bx + si]\n" +
                "    mov bx, [bx + di]\n" +
                "    mov bh, [bx + di]\n" +
                "    mov dx, [bp + si]\n" +
                "    mov dl, [bp + si]\n" +
                "    mov cx, [bp + di]\n" +
                "    mov cl, [bp + di]\n" +
                "                  \n" +
                "    xor ax,ax\n" +
                "    xor bx,bx\n" +
                "    xor cx,cx\n" +
                "    xor dx,dx              \n" +
                "    ;===============================\n" +
                "    ;реализация SAR   \n" +
                "    mov cl, 2\n" +
                "    sar ax, cl  ; SAR Р, Р\n" +
                "    sar ax, 2   ; SAR Р, НО   \n" +
                "    \n" +
                "    ;===============================\n" +
                "    ;реализация JZ \n" +
                "    jz m1       ; JZ (метка)\n" +
                "  m1: \n" +
                "  int 20h\n" +
                "    \n" +
                "  a dw 5h      \n" +
                "  ns ends \n" +
                "    end separateOnMnemAndSpecList";
    }

    private static String someText(){
      return  "ns segment\n" +
              "    assume cs: ns, ds: ns, ss: ns\n" +
              "    org 100h\n" +
              "    mov ax, bx\n" +
              "    mov al, bl\n" +
              "    mov bx, ax\n" +
              "    mov bl, al\n" +
              "    mov dh, bh\n" +
              "    mov bx, 5\n" +
              "    mov bh, 16\n" +
              "    mov ax, 7\n" +
              "    mov al, 25\n" +
              "    mov ax, [bx] + [si]\n" +
              "    mov ah, [bx] + [si]\n" +
              "    mov bx, [bx] + [di]\n" +
              "    mov bh, [bx] + [di]\n" +
              "    mov dx, [bp] + [si]\n" +
              "    mov dl, [bp] + [si]\n" +
              "    mov cx, [bp] + [di]\n" +
              "    mov cl, [bp] + [di]\n" +
              "    mov [bx] + [si], ax\n" +
              "    mov [bx] + [si], ah\n" +
              "    mov [bx] + [di], bx\n" +
              "    mov [bx] + [di], bh\n" +
              "    mov [bp] + [si], dx\n" +
              "    mov [bp] + [si], dl\n" +
              "    mov [bp] + [di], cx\n" +
              "    mov [bp] + [di], cl\n" +
              "    add ax, bx\n" +
              "    add al, bl\n" +
              "    add bx, ax\n" +
              "    add bl, al\n" +
              "    add dh, bh\n" +
              "    add bx, 270\n" +
              "    add bh, 5\n" +
              "    add ax, 5\n" +
              "    add al, 5\n" +
              "    add ax, [bx] + [si]\n" +
              "    add ah, [bx] + [si]\n" +
              "    add bx, [bx] + [di]\n" +
              "    add bh, [bx] + [di]\n" +
              "    add dx, [bp] + [si]\n" +
              "    add dl, [bp] + [si]\n" +
              "    add cx, [bp] + [di]\n" +
              "    add cl, [bp] + [di]\n" +
              "    add [bx] + [si], ax\n" +
              "    add [bx] + [si], ah \n" +
              "    add [bx] + [di], bx\n" +
              "    add [bx] + [di], bh\n" +
              "    add [bp] + [si], dx\n" +
              "    add [bp] + [si], dl\n" +
              "    add [bp] + [di], cx\n" +
              "    add [bp] + [di], cl\n" +
              "    sar ax, cl\n" +
              "    sar ax, 2\n" +
              "    jz m1\n" +
              "  m1: \n" +
              "  int 20h\n" +
              "  a dw 5h      \n" +
              "  ns ends \n" +
              "    end start";
    }
}
