ns segment
    assume cs: ns, ds: ns, ss: ns
    org 100h
 start:
    mov ax, bx
    mov bx, 5
    mov ax, 5
    mov ax, [bx + si]
    mov bx, [bx + di]
    mov dx, [bp + si]
    mov cx, [bp + di]
    mov [bx + si], ax
    mov [bx + di], bx
    mov [bp + si], cx
    mov [bp + di], dx
    xor ax,ax
    xor bx,bx
    xor cx,cx
    xor dx,dx
    add ax, bx
    add bx, 5
    add ax, 5
    add ax, [bx + si]
    add bx, [bx + di]
    add dx, [bp + si]
    add cx, [bp + di]
    add [bx + si], ax
    add [bx + di], bx
    add [bp + si], cx
    add [bp + di], dx
    xor ax,ax
    xor bx,bx
    xor cx,cx
    xor dx,dx
    mov cl, 2
    sar ax, cl
    sar ax, 2
    jz m1
  m1: 
  int 20h
  a dw 5h      
  ns ends 
    end start