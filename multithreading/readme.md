Concurrent vs Single-Threaded Event Loop

Event loop
- Single-threaded

- Handles one task at a time
- Uses non-blocking I/O and callbacks

Thread Pool (Concurrent):
- Multiple threads running truly in parallel
- Multiple tasks execute simultaneously
- Can utilize multiple CPU cores
```java
    ThreadPoolExecutor executor = new ThreadPoolExecutor(
        3,  // 3 threads can run SIMULTANEOUSLY
        3,
        60, TimeUnit.SECONDS,
        new LinkedBlockingQueue<>()
    );

    // These can run truly in parallel
    executor.execute(() -> heavyComputation1());
    executor.execute(() -> heavyComputation2());
    executor.execute(() -> heavyComputation3());
```

Blocking/non-blocking IO

- when read/write to a file or network request, either program can wait until the operation is done, or move on to the next task, and use a callback function to wait for the operation to complete. 
    ```python
    # Blocking I/O example
    data = file.read()  # Thread stops here until read completes
    process(data)       # Continues only after read is done
    ```

    ```python
    # Non-blocking I/O example
    file.read_async(callback=process_data)  # Initiates read and continues
    print("This runs immediately!")         # Doesn't wait for read to complete

    def process_data(data):                 # Called when data is ready
        process(data)
    ```

> Blocking queue
- Simple queue
```
class UnsafeQueue {
    Queue<String> queue = new LinkedList<>();
    
    public void addItem(String item) {
        queue.add(item);    // Not thread-safe!
    }
    
    public String removeItem() {
        return queue.poll(); // Not thread-safe!
    }
}

// What could go wrong:
// 1. Two threads add simultaneously:
Thread1: checks size is 5
Thread2: checks size is 5
Thread1: adds at position 5
Thread2: adds at position 5 (OVERWRITES Thread1's data!)

// 2. Two threads remove simultaneously:
Thread1: checks item exists
Thread2: checks item exists
Thread1: removes item
Thread2: tries to remove same item (NULL POINTER!)
```
- What blocking queue offers us
- Thread Safety: Built-in synchronization
- Flow Control: Automatic blocking when full/empty
- Resource Efficiency: No busy waiting
- Simplicity: No need for explicit synchronization code

```
class BetterProducerConsumer {
    BlockingQueue<String> queue = new LinkedBlockingQueue<>(10); // Capacity of 10
    
    class Producer implements Runnable {
        public void run() {
            try {
                while(true) {
                    // put() will automatically wait if queue is full
                    queue.put("Task " + System.currentTimeMillis());
                    // No need to check queue size or implement waiting
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    class Consumer implements Runnable {
        public void run() {
            try {
                while(true) {
                    // take() will automatically wait if queue is empty
                    String item = queue.take();
                    process(item);
                    // No need to check if queue is empty or implement waiting
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
```
- Difference between linkedblocking queue and arrayblocking queue.
//https://www.geeksforgeeks.org/difference-between-arrayblockingqueue-and-linkedblockingqueue/

- ArrayBlockingQueue:
    - Single lock (more restrictive)
    - Simpler implementation
    - Better for smaller queues
    - Fixed size array
    - Less memory overhead

- LinkedBlockingQueue:
    - Double lock (more concurrent)
    - Separate locks for head and tail
    - Better for high contention
    - Dynamically growing linked nodes
    - Uses AtomicInteger for count

Thread pool task executor
- define the least active threads, max active threads, number of tasks in queue.
- They either use linkedblockingqueue or arrayblockingqueue. 

> Async example: 
- https://github.com/Java-Techie-jt/orderFulfillmentService-async/tree/main/src/main/java/com/javatechie
- which ever method is defined as Async it will run in a different thread from the ThreadPoolExecutor.
- 