package com.github.zjiajun.java.core.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by zhujiajun
 * 16/1/13 13:10
 */
public class StreamExample {

    public static void main(String[] args) {
        //创建stream
        //1.通过Stream静态工厂方法创建
        Stream<Integer> integerStream = Stream.of(1, 2, 3);
        Stream<String> stringStream = Stream.of("zhujiajun");

        Stream<StreamExample> exampleStream = Stream.generate(StreamExample::new).limit(5);

        Stream.iterate(1, item -> item + 1).limit(5).forEach(System.out::println);
        //2.通过Collection接口的默认方法
        List<Integer> ids = Arrays.asList(1,2,3,4,5,6);
        ids.stream().filter(x -> x <= 3).forEach(System.out::println);

        //转换Stream
        List<StreamTestObject> streamTestObjects = new ArrayList<>();
        for (int i = 0;i < 5;i++) {
            if (i < 3)
                streamTestObjects.add(new StreamTestObject("<3"));
            else
                streamTestObjects.add(new StreamTestObject(">3"));
        }
        //distinct: 对于Stream中包含的元素进行去重操作,去重操作依然元素的equals方法
        System.out.println("distinct:");
        streamTestObjects.stream().distinct().forEach(System.out::println);

        //filter: 对于Stream中包含的元素使用给定的过滤函数进行过滤操作，新生成的Stream只包含符合条件的元素
        System.out.println("filter:");
        streamTestObjects.stream().filter(x -> x.name.equals("<3")).forEach(System.out::println);

        // map: 对于Stream中包含的元素使用给定的转换函数进行转换操作，新生成的Stream只包含转换生成的元素
        // 这个方法有三个对于原始类型的变种方法，分别是：mapToInt，mapToLong和mapToDouble
        // 这三个方法也比较好理解，比如mapToInt就是把原始Stream转换成一个新的Stream，这个新生成的Stream中的元素都是int类型
        // 之所以会有这样三个变种方法，可以免除自动装箱/拆箱的额外消耗
        System.out.println("mapToInt:");
        streamTestObjects.stream().mapToInt(x -> x.name.equals(">3") ? 1 : 2).forEach(System.out::println);
        System.out.println("map:");
        streamTestObjects.stream().map(x -> x.name="newName").forEach(System.out::println);

        //flatMap：和map类似，不同的是其每个元素转换得到的是Stream对象，会把子Stream中的元素压缩到父集合中
        //也有flatMapToInt，flatMapToLong和flatMapToDouble
        System.out.println("flatMap:");
        streamTestObjects.stream().flatMap(x -> Stream.generate(() -> new StreamTestObject("1")).limit(2)).forEach(System.out::println);

        //peek: 生成一个包含原Stream的所有元素的新Stream，同时会提供一个消费函数（Consumer实例），新Stream每个元素被消费的时候都会执行给定的消费函数
        System.out.println("peek:");
        streamTestObjects.stream().peek(System.out::println).count();

        //limit: 对一个Stream进行截断操作，获取其前N个元素，如果原Stream中包含的元素个数小于N，那就获取其所有的元素
        System.out.println("limit:");
        streamTestObjects.stream().limit(3).forEach(System.out::println);
        //skip:  返回一个丢弃原Stream的前N个元素后剩下元素组成的新Stream，如果原Stream中包含的元素个数小于N，那么返回空Stream
        System.out.println("skip");
        streamTestObjects.stream().skip(2).forEach(System.out::println);

        List<Integer> nums = Arrays.asList(1,1,null,2,3,4,null,5,6,7,8,9,10);
        System.out.println(nums.stream().filter(x -> x != null).distinct().mapToInt(x -> x * 2).peek(System.out::println).skip(2).limit(4).sum());

    }


    private static class StreamTestObject {

        String name;

        public StreamTestObject(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            StreamTestObject that = (StreamTestObject) o;

            return name != null ? name.equals(that.name) : that.name == null;

        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }

        @Override
        public String toString() {
            return "StreamTestObject{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
