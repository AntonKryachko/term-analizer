ns segment
    assume cs: ns, ds: ns, ss: ns
    org 100h
 start:
    mov ax, bx
    mov al, bl
    mov bx, ax
    mov bl, al
    mov dh, bh
    mov bx, 5
    mov bh, 5
    mov ax, 5
    mov al, 5
    mov ax, [bx] + [si] +
    mov ah, [bx] + [si]
    mov bx, [bx] + [di]
    mov bh, [bx] + [di]
    mov dx, [bp] + [si]
    mov dl, [bp] + [si]
    mov cx, [bp] + [di]
    mov cl, [bp] + [di]
    mov [bx] + [si], ax
    mov [bx] + [si], ah
    mov [bx] + [di], bx
    mov [bx] + [di], bh
    mov [bp] + [si], dx
    mov [bp] + [si], dl
    mov [bp] + [di], cx
    mov [bp] + [di], cl
    xor ax,ax
    xor bx,bx
    xor cx,cx
    xor dx,dx
    add ax, bx
    add al, bl
    add bx, ax
    add bl, al
    add dh, bh
    add bx, 5
    add bh, 5
    add ax, 5
    add al, 5
    add ax, [bx] + [si]
    add ah, [bx] + [si]
    add bx, [bx] + [di]
    add bh, [bx] + [di]
    add dx, [bp] + [si]
    add dl, [bp] + [si]
    add cx, [bp] + [di]
    add cl, [bp] + [di]
    add [bx] + [si], ax
    add [bx] + [si], ah
    add [bx] + [di], bx
    add [bx] + [di], bh
    add [bp] + [si], dx
    add [bp] + [si], dl
    add [bp] + [di], cx
    add [bp] + [di], cl
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