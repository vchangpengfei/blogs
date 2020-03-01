package guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Random;
import java.util.concurrent.ExecutionException;

public class CacheTest {

    public static void main(String args[]) throws ExecutionException {
        LoadingCache<Integer, Double> cache= CacheBuilder.newBuilder().build(
                new CacheLoader<Integer, Double>() {
                    public Double load(Integer key)  {
                        return new Random().nextDouble();
                    }
                });


        for(int i=0;i<10;i++){
            System.out.println(i+":"+cache.get(i));
        }

        for(int i=0;i<10;i++){
            System.out.println(i+":"+cache.get(i));
        }


    }


}
