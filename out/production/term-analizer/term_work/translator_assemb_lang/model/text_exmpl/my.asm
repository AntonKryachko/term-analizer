ns segment
    assume cs: ns, ds: ns, ss: ns
    org 100h
 start:                          
    ;�������:
    ;� - �������
    ;�� - ���������������� �������
    ;�� - ����������� ������ (��������� ������-���������)
    
    ; �������
    ; MOV P, P
    ; MOV P, ��
    ; MOV P, ��
    ; MOV ��,P
    ; AND P, P
    ; AND P, ��
    ; AND P, ��
    ; AND ��,P
    ; SAR �, �
    ; SAR �, ��
    ; JZ (�����)
     
    ;===============================
    ;���������� MOV   
    
    mov ax, bx   ;MOV P, P   
    
    mov bx, 5    ;MOV P, HO
    mov ax, 5            
    
    mov ax, [bx + si]  ; MOV P, �� 
    mov bx, [bx + di]
    mov dx, [bp + si]
    mov cx, [bp + di]
     
    mov [bx + si], ax  ; MOV ��,P
    mov [bx + di], bx
    mov [bp + si], cx
    mov [bp + di], dx
    
    xor ax,ax
    xor bx,bx
    xor cx,cx
    xor dx,dx
    ;===============================
    ;���������� AND        
    
    add ax, bx  ; AND P, P
    
    add bx, 5   ; AND P, ��
    add ax, 5
    
    add ax, [bx + si]  ; add P, �� 
    add bx, [bx + di]
    add dx, [bp + si]
    add cx, [bp + di]
    
    add [bx + si], ax  ; add ��,P
    add [bx + di], bx
    add [bp + si], cx
    add [bp + di], dx   
                  
    xor ax,ax
    xor bx,bx
    xor cx,cx
    xor dx,dx              
    ;===============================
    ;���������� SAR   
    mov cl, 2
    sar ax, cl  ; SAR �, �
    sar ax, 2   ; SAR �, ��   
    
    ;===============================
    ;���������� JZ 
    jz m1       ; JZ (�����)
  m1: 
  int 20h
    
  a dw 5h      
  ns ends 
    end start
