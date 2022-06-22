# Bus-Routes
Theme:
  A bus route consists of a sequence of different stops. Several buses can stop at one stop at the same time, but not more than a given number. If buses are standing at a stop passengers can transfer from one bus to another or stay at the stop. Buses can each go their own route. 
Goal:
  Develop a multithreaded application that uses shared resources. Any entity that wants to access a shared resource must be a thread. The application must implement the functionality defined in the individual task. 

Requirements:
  1) The application must use the synchronization capabilities provided by the java.util.concurrent and java.util.concurrent.locks libraries. 
  2) Classes and other entities in the application should be intelligently structured into packages and have a name that reflects their functionality. 
  3) Use the State pattern to describe the object's states, unless there are more than two states. 
  4) Do not use synchronized, volatile, BlockingQueueue and other thread-safe collections. 
  5) Use Callable or Runnable to create threads. 
  6) Use TimeUnit enumeration features instead of Thread.sleep. 
  7) Read object initialization data from a file. 
  8) The application must have a thread-safe Singleton. 
  9) To write logs use Log4J2. 
  10) It is allowed to use main and System.out methods to output threads. 
