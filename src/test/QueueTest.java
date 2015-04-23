package test;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class QueueTest {
	
	@Test
	public void run(){
		Queue<String> queue = new LinkedList<String>();  
        queue.offer("Hello");  
        queue.offer("World!");  
        queue.offer("你好！"); 
        queue.add("132456");
        System.out.println(queue.size());  
        String str;  
        while((str=queue.poll())!=null){  
            System.out.print(str);  
        }  
        System.out.println();  
        System.out.println(queue.size());  
	}
	
}
