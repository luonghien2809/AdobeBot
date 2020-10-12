import tensorflow as tf
import numpy as np
x = tf.constant([[1, 3, 1], [-3, 2, 1]])
tf.reduce_sum(x)  # 6
tf.reduce_sum(x, 0)  # [2, 2, 2]
m = tf.reduce_max(x, -1)  # [3, 3]
sess = tf.Session()
print(sess.run(m))