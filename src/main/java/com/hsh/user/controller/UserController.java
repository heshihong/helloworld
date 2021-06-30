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
}