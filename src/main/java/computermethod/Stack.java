package computermethod;

import java.util.Objects;

public class Stack {
    private Node top;
    private Integer length = 0;
    public synchronized void  push(Node e){
        if(!Objects.isNull(top)){
            //新增节点前驱指针指向,当前节点
            e.setPre(top);
            //当前节点的后继指针指向，新增节点
            top.setNext(e);
            //设置当前节点为栈顶
            top = top.getNext();
        }else{
            //边界处理
            top = e;
        }
        length++;
    }
    public synchronized Node pop(){
        Node tmp = new Node();
        while (top.getNext() == null){
            tmp.setValue(top.getValue());

            top = top.getPre();
            if(top == null){
                length--;
                return tmp;
            }
            //前驱指针置空
            top.getNext().setPre(null);
            //后驱指针置空
            top.setNext(null);
            //栈深减一
            length--;
            return tmp;
        }
        return tmp;
    }

    public Integer getLength() {
        return length;
    }


}
