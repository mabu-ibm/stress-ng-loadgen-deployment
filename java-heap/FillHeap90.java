import java.util.ArrayList;

public class FillHeap90 {

	public static void main(String[] args)throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("Take 90% Heap / of all Memory ");
		// Var for Memory
		long allmem; // maximum Mem to allocate
		long p90ofallmem; // 90% of Max mem
		long p10ofallmem; // 10% of max mem
		ArrayList<Long> list = new ArrayList<Long>();
		// Get memory
	    Runtime rt = Runtime.getRuntime();
	    allmem = rt.maxMemory();	 
	    p90ofallmem = (allmem /100 )*90;
	    p10ofallmem = (allmem /100 )*5;
	    System.out.println(allmem);
	    System.out.println(p10ofallmem);
	    System.out.println(rt.freeMemory());
	    Thread.sleep(5000); // 5s to allow starting visualvm
	    for (int loop=1; loop < 2;) {
			for (long l = 0; l < Long.MAX_VALUE; l++) {
				rt = Runtime.getRuntime();
				//  run until 90% of Mem filled, only 10% free
				if (rt.freeMemory() < p10ofallmem ) break;
				list.add(new Long(l));			
				System.out.println( allmem);
				System.out.println( allmem - rt.freeMemory());
				System.out.println(rt.freeMemory());
				for (int i = 0; i < 5000; i++) {
					// busy wait, 'cause 1ms sleep is too long
					if (i == 5000) break;
				}
			}
			Thread.sleep(36000000);
	    }
	}

}
