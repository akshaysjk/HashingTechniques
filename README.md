# Hashing using Linear Probing with Dynamic Resizing

Hashing Performed using three collision Probing techniques linear probing, quadratic probing, Double Hashing for different set of values such as 1000,5000,10000 and checked for the load factor constraint on the hash table.

Based on the empirical analysis and statistical report the load factor value between 0.5 and 0.6 possesses least time of execution.
Initially the array was being resized for every load factor value.

Based on the empirical analysis and statistical report found out that the load factor of 0.5 or 0.6 should be the case when the hash table should be resized.
In the second part based on the statistical report 0.5 is load factor set when the hash table is resized. The values in the new hash table are stored incrementally based on the old key hash table and the remaining entry keys into the new hash table. To perform this task incrementally, made use of threads to perform the task of insertion of old hash values into the new one hash table in background.

The search operation performed to search the key in the hash table is done using the hash search technique which uses the hash function to search for key in the hash table rather than performing sequential or serial search.

# Technologies and Concepts used
Java Multithreading and Hashing Techniques.
