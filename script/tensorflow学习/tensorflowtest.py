# -*- coding: utf-8 -*-
import tensorflow as tf

a=tf.random.normal([2,2],mean=0,stddev=1)
print(tf.zeros([2,3]))
a=tf.zeros([2,3])
b=tf.ones([2,3])


print (tf.add(a,b))
