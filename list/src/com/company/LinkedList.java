package com.company;

import java.util.function.Consumer;

public class LinkedList {
    private ListNode first;

    public LinkedList(){

    }

    private LinkedList(ListNode first){
        this.first = first;
    }

    public LinkedList SortedMerge(LinkedList linkedList){
        ListNode currFirst = this.first;
        ListNode currSecond = linkedList.first;
        ListNode a1;
        if(currFirst.value > currSecond.value){
            a1 = currSecond;
            currSecond = currSecond.next;
        }
        else{
            a1 = currFirst;
            currFirst = currFirst.next;
        }

        ListNode add = a1;

        while (currFirst != null && currSecond != null){
            if(currFirst.value > currSecond.value){
                add.next = currSecond;
                currSecond = currSecond.next;
            }
            else{
                add.next = currFirst;
                currFirst = currFirst.next;
            }
            add = add.next;
        }


        if(currFirst != null){
            add.next = currFirst;
        }
        if(currSecond != null){
            add.next = currSecond;
        }


        return new LinkedList(a1);
    }

    public Integer at(int pos){
        if(pos < 0)
            return null;
        int currPos = 0;
        ListNode curr = first;
        while (currPos < pos){
            curr = curr.next;
            currPos++;
        }
        return curr.value;
    }

    public void Add(int valueNode){
        if(first == null){
            first = new ListNode(valueNode);
            return;
        }
        ListNode c = first;
        while (c.next != null){
            c = c.next;
        }
        c.next = new ListNode(valueNode);
    }

    public boolean Remove(int value){
        if(first == null)
            return false;
        if(first.value == value){
            first = first.next;
            return true;
        }
        ListNode curr = first;
        while (curr.next != null) {
            if(curr.next.value == value){
                curr.next = curr.next.next;
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    private void forEachNode(Consumer<ListNode> nodes){
        if(first != null){
            ListNode curr = first;
            while (curr != null) {
                nodes.accept(curr);
                curr = curr.next;
            }
        }
    }

    public void forEach(Consumer<Integer> consumer){
        forEachNode(new Consumer<ListNode>() {
            @Override
            public void accept(ListNode listNode) {
                consumer.accept(listNode.value);
            }
        });
    }


    static class ListNode {
        ListNode next;
        int value;

        public ListNode(int value, ListNode next){
            this.next = next;
            this.value = value;
        }

        public ListNode(int value){
            this(value, null);
        }
    }
}
