ns segment
    assume cs: ns, ds: ns, ss: ns
    org 100h
 start:                          
    ;Легенда:
    ;Р - регистр
    ;НО - непосредственный операнд
    ;ОП - оперативная панять (адресация базова-индексная)
    
    ; Задание
    ; MOV P, P
    ; MOV P, НО
    ; MOV P, ОП
    ; MOV ОП,P
    ; AND P, P
    ; AND P, НО
    ; AND P, ОП
    ; AND ОП,P
    ; SAR Р, Р
    ; SAR Р, НО
    ; JZ (метка)
     
    ;===============================
    ;реализация MOV   
    
    mov ax, bx   ;MOV P, P   
    
    mov bx, 5    ;MOV P, HO
    mov ax, 5            
    
    mov ax, [bx + si]  ; MOV P, ОП 
    mov bx, [bx + di]
    mov dx, [bp + si]
    mov cx, [bp + di]
     
    mov [bx + si], ax  ; MOV ОП,P
    mov [bx + di], bx
    mov [bp + si], cx
    mov [bp + di], dx
    
    xor ax,ax
    xor bx,bx
    xor cx,cx
    xor dx,dx
    ;===============================
    ;реализация AND        
    
    add ax, bx  ; AND P, P
    
    add bx, 5   ; AND P, НО
    add ax, 5
    
    add ax, [bx + si]  ; add P, ОП 
    add bx, [bx + di]
    add dx, [bp + si]
    add cx, [bp + di]
    
    add [bx + si], ax  ; add ОП,P
    add [bx + di], bx
    add [bp + si], cx
    add [bp + di], dx   
                  
    xor ax,ax
    xor bx,bx
    xor cx,cx
    xor dx,dx              
    ;===============================
    ;реализация SAR   
    mov cl, 2
    sar ax, cl  ; SAR Р, Р
    sar ax, 2   ; SAR Р, НО   
    
    ;===============================
    ;реализация JZ 
    jz m1       ; JZ (метка)
  m1: 
  int 20h
    
  a dw 5h      
  ns ends 
    end start
