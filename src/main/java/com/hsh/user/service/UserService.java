package com.hsh.user.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hsh.user.entity.ListNode;
import com.hsh.user.entity.User;
import com.hsh.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 15:23
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User getUser(int id) {
        return userMapper.getUser(id);
    }

    public String getNum(int[] a) {
        int aSize = a.length;
        Arrays.sort(a);
        int max = a[aSize - 1];
        int[] b = new int[max + 1];
        for (int i = 0; i < max; i++) {
            if (i >= aSize) {
                break;
            }
            b[a[i]]++;
        }
        int maxNum = b[0];   //定义次数
        int value = 0;    //定义最大值
        for (int i = 1; i < b.length; i++) {
            if (b[i] > maxNum) {
                maxNum = b[i];
                value = i;
            }
        }
        int mid = 0;
        if (aSize / 2 == 0) {
            mid = (a[aSize / 2] + a[aSize / 2 - 1]) / 2;
        } else {
            mid = a[aSize / 2];
        }
        return "出现次数最多的值是:" + value + ", 出现了" + maxNum + "次，中位数是：" + mid;
    }

    public int getReduce(String str) {
        if (str == null || str == "") {
            return 0;
        }
        if (!str.contains("1")) {
            return str.length() - 1;
        }
        String newStr = str.replace(" ", "");
        String[] ary = newStr.split("1");
        int max = 0;
        for (String s : ary) {
            if (s.length() > max) {
                max = s.length() - 1;
            }
        }
        return max;
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 判空输入
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        // 遍历两个输入链表
        ListNode head = null;
        ListNode first = null;
        int tmp = 0;
        while (l1 != null || l2 != null) {
            tmp = tmp + (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0);
            if (first == null) {
                first = new ListNode(tmp % 10, null);
                head = first;
            } else {
                first.next = new ListNode(tmp % 10, null);
                first = first.next;
            }
            l1 = (l1 != null ? l1.next : null);
            l2 = (l2 != null ? l2.next : null);
            tmp /= 10;
        }
        // 处理相加的进位
        if (tmp != 0) {
            first.next = new ListNode(tmp, null);
        }
        return head;
    }

    public ListNode addTwoNumberss(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode first = null;
        ListNode head = null;
        int temp = 0;
        while (l1 != null || l2 != null) {
            temp = temp + (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0);
            if (first == null) {
                first = new ListNode(temp % 10, null);
                head = first.next;
            } else {
                first.next = new ListNode(temp % 10, null);
                first = first.next;
            }

            l1 = (l1 == null ? l1.next : null);
            l2 = (l2 == null ? l2.next : null);

            temp /= 10;
        }

        if (temp != 0) {
            first.next = new ListNode(temp % 10, null);
        }
        return head;
    }

    public ListNode addTwoNumbersss(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode first = null;
        ListNode head = null;
        int temp = 0;

        while (l1 != null || l2 != null) {
            temp = temp + (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0);
            if (first == null) {
                first = new ListNode(temp % 10, null);
                head = first;
            } else {
                first.next = new ListNode(temp % 10, null);
                first = first.next;
            }

            l1 = (l1 != null ? l1.next : null);
            l2 = (l2 != null ? l2.next : null);

            temp /= 10;
        }

        if (temp != 0) {
            first.next = new ListNode(temp, null);
        }


        return head;
    }

    public ListNode get(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode first = null;
        ListNode head = null;
        int temp = 0;

        while (l1 != null || l2 != null) {
            temp = temp + (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0);
            if (first == null) {
                first = new ListNode(temp % 10, null);
                head = first;
            } else {
                first.next = new ListNode(temp % 10, null);
                first = first.next;
            }

            l1 = (l1 != null ? l1.next : null);
            l2 = (l2 != null ? l2.next : null);

            temp /= 10;
        }

        if (temp != 0) {
            first.next = new ListNode(temp, null);
        }


        return head;
    }

    public int getAnmoMax(int nums[]) {
        int sum1 = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum1 += nums[i];
        }
        int sum2 = 0;
        for (int i = 1; i < nums.length; i += 2) {
            sum2 += nums[i];
        }
        return Math.max(sum1, sum2);
    }

    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while (i < j) {
            int left = height[i];
            int right = height[j];
            int max = 0;
            if (left < right) {
                max = Math.max(res, (j - i) * height[i++]);
            } else {
                max = Math.max(res, (j - i) * height[j--]);
            }
            res = max;
        }
        return res;
    }

}