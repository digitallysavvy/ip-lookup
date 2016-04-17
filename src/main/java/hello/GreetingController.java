package hello;


import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;


@RestController
public class GreetingController{

	private final AtomicLong counter = new AtomicLong();

	private InetAddress serverInet;
	private String client;

	@RequestMapping("/greeting")
	public Greeting greeting(HttpServletRequest request) {
        
    	try {

			serverInet = InetAddress.getLocalHost();
			client = request.getRemoteAddr();

		} catch (UnknownHostException e) {

			e.printStackTrace();

		}

		String server = serverInet.getHostAddress();

        return new Greeting(counter.incrementAndGet(),
        					server, client);
    }



}