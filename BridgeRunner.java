/**
 * Runs all threads
 */

public class BridgeRunner {


	public static void main(String[] args) {
		//Needed variables 
		int Limit = 0;
		int Amount = 0;
		if(args[0] != null && args[1] != null){
		 Limit = Integer.parseInt(args[0]);
		 Amount = Integer.parseInt(args[1]);     
		}
		// TODO - check command line inputs

		try{
		OneLaneBridge bridge = new OneLaneBridge(Limit);
			
		if(Limit < 0 || Amount < 0){
			System.out.println(args.length);
			System.out.println("Not enough arguments inputted \n java BridgeRunner <bridge limit> <num cars>");
		} else if(args.length == 2){

		// TODO - allocate space for threads
		 Thread[] threads = new Thread[Amount];
		 for (int i = 0; i < Amount; i++) {
			threads[i] = new Thread(new Car(i,bridge));
			threads[i].start();
		}

		// TODO - start then join the threads
    	 for (int i = 0; i < Amount; i++) {
      		try {
      		  threads[i].join();
     		 } catch (InterruptedException e) {
			 }
		}
	}
		System.out.println("All cars have crossed!!");
	} catch(Exception e){
		System.err.println("Usage: javac BridgeRunner <bridge limit> <num cars> ");
	}
  }

}
