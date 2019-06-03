package cha.pao.fan.blogs.api;

import io.reactivex.Flowable;

public class RxJavaTest {

    public static void main(String[] args) {
        Flowable.just("Hello world").subscribe(System.out::println);
    }
}
