1) The apps model is basically there is a static member variable of the class RequestHandler, which being accessed by multiple threads to change its value. 

2) The original code is not thread safe because price is a shared variable and multiple threads are trying access at once racing to change its value without mutual exclusion. This could cause data consistency because the java '+='(compound assignment operand) is not atomic and executes multiple operations to finish. For example it could be a Load, register arithmetic add and then a store operation. If the thread gets prempted before the store and some other thread tries to get price, it reads the old, stale value of price. Hence data inconsistency.

3) To prevent data incosistency I enforced mutual exclusion by using a lock. I also added 2 step thread exit.
