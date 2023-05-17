1) The apps model is basically there is a static member variable of the class RequestHandler, which being accessed by multiple threads to change its value. 

2) The original code is prone to deadlock because a thread acquires a lock and then sits in a loop. As explained inote 8 one should never try to make the entire loop atomic. This causes one thread to execute forever and other threads waiting. Hence deadlock.

3) To prevent deadlock I acquire and relase lock in every iteration instead of trying to make the loop atomic.
