/**
   A class that implements a queue of objects by using
   a chain of linked nodes that has both head and tail references.
 
   @author Frank M. Carrano
   @author Timothy M. Henry
   @author Lawrence Schoch
   @version 5.0
*/
public final class LinkedQueue<T> implements QueueInterface<T>
{
   private Node firstNode; // References node at front of queue
   private Node lastNode;  // References node at back of queue
  	
   public LinkedQueue()
   {
	   firstNode = null;
	   lastNode = null;
   }// end default constructor

   /** Adds a new entry to the back of this queue.
    * @param newEntry  An object to be added. */
   public void enqueue(T newEntry)
   {
	   Node newNode = new Node(newEntry, null);
	   if (isEmpty())
		   firstNode = newNode;
	   else
		   lastNode.setNextNode(newNode);
	   lastNode = newNode;
   }// end enqueue

   /** Removes and returns the entry at the front of this queue.
    * @return  The object at the front of the queue.
    * @throws  EmptyQueueException if the queue is empty before the operation. */
   public T dequeue() throws EmptyQueueException
   {
	   T front = firstNode.getData(); // Might throw EmptyQueueException
	   // Assertion firstNode != null
	   firstNode.setData(null);
	   firstNode = firstNode.getNextNode();
	   if (firstNode == null)
		   lastNode = null;
	   return front;
   }// end dequeue

   /**  Retrieves the entry at the front of this queue.
    * @return  The object at the front of the queue.
    * @throws  EmptyQueueException if the queue is empty. */
   public T getFront() throws EmptyQueueException
   {
	   if (isEmpty())
		   throw new EmptyQueueException();
	   else
		   return firstNode.getData();
   }// end getFront

	/** Detects whether this queue is empty.
	 * @return  True if the queue is empty, or false otherwise. */
	public boolean isEmpty()
	{
		return firstNode == null && lastNode == null;
	}// end isEmpty

	/** Removes all entries from this queue. */
	public void clear()
	{
		firstNode = null;
		lastNode = null;
	}// end clear
	
	/**
	 * A method to print a string to identify the objects
	 * in the queue by their toString method. 
	 * @return The string to be returned.
	 */
	@Override
	public String toString() {
		String str = " ";
		Node currentNode = firstNode;
		if (isEmpty())
			return " The line is empty.";
		else if (currentNode == lastNode)
			return " " + currentNode.getData().toString() + " is in line.\n"
					+ " Waiting ...";
		else
		{
			while(currentNode != lastNode)
			{
				if (currentNode == firstNode && currentNode.getNextNode() == lastNode)
					str+= currentNode.getData().toString()+ " ";
				else
					str+= currentNode.getData().toString() + ", ";
			    currentNode = currentNode.getNextNode();
			}
			str+= "and " + currentNode.getData().toString() 
				+ " are in line. " + firstNode.getData().toString() 
				+ " is at the front of the line and " 
				+ lastNode.getData().toString() + " is at the back.\n";
			str+= " Waiting ...";
			return str;
		}
	}// end toString
	
	/** A class of objects which contain a data portion of generic type
	 * and a pointer to the next node in the chain of nodes.
	 * 
	 * @author Lawrence Schoch */
	private class Node
	{
		private T    data; // Entry in queue
		private Node next; // Link to next node
		
		/** Constructor to initialize the dataPortion of the node while
		 * leaving the linkPortion null.
		 * @param dataPortion The initial entry for this node.*/
		private Node(T dataPortion)
		{
			data = dataPortion;
			next = null;
		}// end dataPortion constructor
		
		/** Constructor to initialize both the dataPortion and the linkPortion
		 * of the node.
		 * @param dataPortion The dataPortion of the new node.
		 * @param linkPortion The linkPortion of the new node. */
		private Node(T dataPortion, Node linkPortion)
		{
			data = dataPortion;
			next = linkPortion;
		}// end full constructor
		
		/** Method to get the data portion of the calling node
		 * @return The data portion of the calling node. */
		private T getData()
		{
			return data;
		}// end getData
		
		/** Method to set the dataPortion of the calling node.
		 * @param newData The data to be set. */
		private void setData(T newData)
		{
			data = newData;
		}// end setData
		
		/** Method to get the next node in the chain of nodes.
		 * @return The next node in the chain of nodes. */
		private Node getNextNode()
		{
			return next;
		}// end getNextNode
		
		/** Method to set the next node in the chain of nodes
		 * @param nextNode The next node in the chain of nodes.
		 */
		private void setNextNode(Node nextNode)
		{
			next = nextNode;
		}// end setNextNode
	}// end Node

}// end LinkedQueue
