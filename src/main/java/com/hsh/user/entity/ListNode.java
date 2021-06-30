package com.hsh.user.entity;

import lombok.Data;

@Data
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x,ListNode n) {
        val = x;
        next = n;
    }
}