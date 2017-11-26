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
    
    mov ax, bx   ;MOV P, P
    mov al, bl
    mov bl, al
    mov dh, bh

    mov bx, 5    ;MOV P, HO
    mov bh, 5
    mov ax, 5
    mov al, 5

    mov ax, [bx + si]  ; MOV P, ОП
    mov ah, [bx + si]
    mov bx, [bx + di]
    mov bh, [bx + di]
    mov dx, [bp + si]
    mov dl, [bp + si]
    mov cx, [bp + di]
    mov cl, [bp + di]
                  
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
