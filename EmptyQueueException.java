/**
   A class of runtime exceptions thrown when an attempt
   is made to access an element of an empty queue.

   @author Lawrence Schoch
   @version 1.0
*/
@SuppressWarnings("serial")
public class EmptyQueueException extends RuntimeException
{
   public EmptyQueueException()
   {
      this("Attempted to access an element of an empty queue.");
   }// end default constructor
 
   public EmptyQueueException(String message)
   {
      super(message);
   }// end constructor
   
}// end EmptyQueueException
