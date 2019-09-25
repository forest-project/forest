package cn.forest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ApplicationGateway {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationGateway.class, args);
	}
	
	 /**
	  * 
	  * Description: fallback默认路径
	  * 
	  * @data 2019年9月25日
	  * @param 
	  * @return
	  */
    @RequestMapping("/defaultfallback")
    public String defaultfallback() {
    	return "gateway--------defaultfallback";
    }
}
