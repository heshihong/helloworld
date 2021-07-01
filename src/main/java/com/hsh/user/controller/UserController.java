package com.hsh.user.controller;

import com.hsh.user.entity.ListNode;
import com.hsh.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 14:42
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("getUser/{id}")
    public String GetUser(@PathVariable int id) {
        return userService.getUser(id).toString();
    }

    @PostMapping("getNumMax")
    public String getNumMax(@RequestBody int[] nums) {
        return userService.getNum(nums);
    }

    @GetMapping("getReduce")
    public int getReduce(String str) {
        return userService.getReduce(str);
    }

    @GetMapping("addTwoNumbers")
    public ListNode addTwoNumbers() {
        ListNode l1 = new ListNode(6, new ListNode(1, new ListNode(7, null)));
        ListNode l2 = new ListNode(2, new ListNode(9, new ListNode(5, null)));
        return userService.get(l1, l2);
    }

    @GetMapping("getAnmoMax")
    public int getAnmoMax(@RequestBody int[] nums) {
        return userService.getAnmoMax(nums);
    }

    @GetMapping("maxArea")
    public int maxArea(@RequestBody int[] nums) {
        return userService.maxArea(nums);
    }

    @GetMapping("reverseList")
    public ListNode reverseList(@RequestBody ListNode head) {
        return userService.reverseList(head);
    }

    @GetMapping("isValid")
    public boolean isValid(String s) {
        return userService.isValid(s);
    }

    @GetMapping("removeDuplicates")
    public int removeDuplicates(@RequestBody int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int p = 0;
        int q = 1;
        while (q < nums.length) {
            if (nums[p] != nums[q]) {
                if (q - p > 1) {
                    nums[p + 1] = nums[q];
                }
                p++;
            }
            q++;
        }
        return p + 1;
    }

    @GetMapping("rob")
    public int rob(@RequestBody int[] nums) {
        int len = nums.length;
        if (len == 0)
            return 0;
        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[len];
    }

    @GetMapping("sortColors")
    public void sortColors(@RequestBody int[] nums) {
        int n = nums.length;
        int ptr = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
        for (int i = ptr; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
    }
}