import com.hsh.aop.entity.User;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
public class SpringbootTest {

    @Test
    public void test1() {
        List<User> list = new ArrayList<>();
        list.add(new User("1","aaa",22L));
        list.add(new User("1","bbb",25L));
        list.add(new User("1","ccc",25L));
        list.add(new User("1","ddd",19L));
        list.add(new User("1","eee",32L));
        list.add(new User("1","fff",29L));
        list.add(new User("1","ggg",21L));
        list.add(new User("1","hhh",50L));

        // list根据年龄属性升序排序
        List<User> upList = list.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());

        // list根据年龄属性降序排序
        List<User> downList = list.stream().sorted(Comparator.comparing(User::getAge).reversed()).collect(Collectors.toList());

        // 平均数
        double asDouble = list.stream().mapToLong(User::getAge).average().getAsDouble();
        double avg = list.stream().collect(Collectors.averagingLong(User::getAge));
        System.out.println("average:" + avg);

        // 最大值
        long asLong = list.stream().mapToLong(User::getAge).max().getAsLong();
        System.out.println("max:" + asLong);

        // 最小值
        long asLong1 = list.stream().mapToLong(User::getAge).min().getAsLong();
        System.out.println("min:" + asLong1);

        // 求和
        long sum1 = list.stream().mapToLong(User::getAge).sum();
        System.out.println("sum:" + sum1);

        // 提取对象属性生成list
        List<Long> ids = list.stream().map(User::getAge).collect(Collectors.toList());
        System.out.println(ids);

        // list升序排序
        Collections.sort(ids);
        System.out.println(ids);

        // 生成中位数
        Long j;
        if (ids.size() % 2 == 0) {
            j = (ids.get(ids.size() / 2 - 1) + ids.get(ids.size() / 2)) / 2;
            System.out.println("中位数为" + j);
        } else {
            j = ids.get(ids.size() / 2);
            System.out.println("中位数为" + j);
        }

        // list倒序排序
        ids.sort(Comparator.reverseOrder());
        System.out.println(ids);

        /**
         * List -> Map
         * 需要注意的是：toMap 如果集合对象有重复的key，会报错Duplicate key ....
         *  apple1,apple12的id都为1。可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
         */
        Map<Long, User> userMap = list.stream().collect(Collectors.toMap(User::getAge, a -> a, (k1, k2) -> k1));
        System.out.println(userMap);

        //过滤出符合条件的数据
        List<User> filterList = list.stream().filter(a -> a.getName().equals("eee")).collect(Collectors.toList());
        System.out.println("filterList:" + filterList);

        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5);

        int sum = list2.stream().reduce(0, (acc, value) -> acc + value);
        System.out.println(sum);

        List<Integer> result = list2.stream().filter((value) -> value > 2).collect(Collectors.toList());
        result.forEach(System.out::println);

        List<String> result2 = list2.stream().map(value -> String.format("String:%s", value)).collect(Collectors.toList());
        result2.forEach(System.out::println);

        // 用于收集统计数据的状态对象，例如count，min，max，sum和平均。
        IntSummaryStatistics stats = list2.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Max : " + stats.getMax());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Sun: " + stats.getSum());
        System.out.println("Average : " + stats.getAverage());
        System.out.println("Count : " + stats.getCount());
        System.out.println("toString : " + stats.toString());
    }

}
