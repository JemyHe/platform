package com.xingxue.class11.framework.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 成对出现的值
 */

public class Pair<H, E> {

    private H head;
    private E end;

    public H getHead() {
        return head;
    }

    public void setHead(H head) {
        this.head = head;
    }

    public E getEnd() {
        return end;
    }

    public void setEnd(E end) {
        this.end = end;
    }

    public Pair(H head, E end) {
        this.head = head;
        this.end = end;
    }
}
