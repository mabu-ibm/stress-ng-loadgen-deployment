package heaptest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class heaptest {
	
	
    private static void webServer() throws IOException {
        InetSocketAddress addr = new InetSocketAddress(8080);
        HttpServer webserver = HttpServer.create(addr, 0);
        webserver.createContext("/test", new TestHandler());
        webserver.setExecutor(Executors.newCachedThreadPool());
        webserver.start();
        System.out.println("Server is listening on port 8080" );
    }
	
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
			//rt = Runtime.getRuntime();
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
		//Thread.sleep(36000000);
		if (rt.freeMemory() < p10ofallmem ) break;
	
    }
   
   

	try {
		webServer();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

}

/* Sample java handler
*/
class TestHandler implements HttpHandler {
public void handle(HttpExchange exchange) throws IOException {
    String requestMethod = exchange.getRequestMethod();
    Runtime rtx = Runtime.getRuntime();
    String Track = "<!<!DOCTYPE html>"
    		+ "<html>\n"
    		+ "<head>\n"
    		+ "<script>\n"
    		+ "  (function(s,t,a,n){s[t]||(s[t]=a,n=s[a]=function(){n.q.push(arguments)},\n"
    		+ "  n.q=[],n.v=2,n.l=1*new Date)})(window,\"InstanaEumObject\",\"ineum\");\n"
    		+ "\n"
    		+ "  ineum('reportingUrl', 'https://instana.fritz.box/eum/');\n"
    		+ "  ineum('key', 'e-AdrSQQQ8mdIBUsgMFYWg');\n"
    		+ "  ineum('trackSessions');\n"
    		+ "</script>\n"
    		+ "<script defer crossorigin=\"anonymous\" src=\"https://instana.fritz.box/eum/eum.min.js\"></script>" 
    		+ "</head>\n "
            + "</html>" ;
    if (requestMethod.equalsIgnoreCase("GET")) {
        URI responseURI = exchange.getRequestURI();
        String pathName = responseURI.getPath();
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/plain");
        exchange.sendResponseHeaders(200, 0);

        OutputStream responseBody = exchange.getResponseBody();
        Headers requestHeaders = exchange.getRequestHeaders();
        Set<String> keySet = requestHeaders.keySet();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            List values = requestHeaders.get(key);
            String s = key + " = " + values.toString() + "\n";
            responseBody.write(s.getBytes());
        }
        String pathString = "Pathname = " + pathName + "\n";
        String maxHeap = "\n" + "maxMemory Call=" + rtx.maxMemory()+ "\n";
        String freeHeap = "\n" + "freeMemory =" + rtx.freeMemory()+ "\n";
        responseBody.write(Track.getBytes());
        responseBody.write(maxHeap.getBytes());
        responseBody.write(freeHeap.getBytes());
        responseBody.write(pathString.getBytes());
        responseBody.close();
    }
}
