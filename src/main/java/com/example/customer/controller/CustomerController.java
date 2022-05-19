package com.example.customer.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CustomerController {

    @Autowired(required=true)
    RestTemplate restTemplate;



    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(value = "/customer/personal")
    public String getCustomerPersonalDetails() {

        try {
            HttpHeaders headers = new HttpHeaders();
            InetAddress inetAddress = InetAddress.getLocalHost();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity <String> entity = new HttpEntity<String>(headers);
            System.out.println("*******Calling Customer Service to fetch Personal details*******");
            return restTemplate.exchange("http://"+inetAddress+":8082/personal", HttpMethod.GET, entity, String.class).getBody();
            //return restTemplate.exchange("http://localhost:8083/personal", HttpMethod.GET, entity, String.class).getBody();
        }catch(Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/customer/personal/address")
    public String getCustomerAddressDetails() {

        try {
            HttpHeaders headers = new HttpHeaders();
            String ipAddress=null;
            InetAddress inetAddress = InetAddress.getLocalHost();
            inetAddress = InetAddress.getLocalHost();

            ipAddress = inetAddress.getHostAddress();
            System.out.println("Cannonical host Host name"+inetAddress.getCanonicalHostName());
            System.out.println("Host name"+inetAddress.getHostName());
            System.out.println("getLoopbackAddress==="+inetAddress.getLoopbackAddress());
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity <String> entity = new HttpEntity<String>(headers);
            System.out.println("*******Calling Customer Service to fetch Personal Address details*******");
            System.out.println("IPAddress in customer service 44444 "+ipAddress);
            System.out.println("Updated");
            //return restTemplate.exchange("http://"+ipAddress+":8089/personal/address", HttpMethod.GET, entity, String.class).getBody();
            //return restTemplate.exchange("http://customer-service.local:1000/personal/address", HttpMethod.GET, entity, String.class).getBody();
            return restTemplate.exchange("http://127.0.0.1:1000/personal/address", HttpMethod.GET, entity, String.class).getBody();
            //return restTemplate.exchange("http://127.0.0.1:8089/personal/address", HttpMethod.GET, entity, String.class).getBody();
            //return restTemplate.exchange("http://localhost:8083/personal/address", HttpMethod.GET, entity, String.class).getBody();
        }catch(Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/customer/employment")
    public String getCustomerEmploymentDetails() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity <String> entity = new HttpEntity<String>(headers);
            System.out.println("Calling Customer Employment details");
            return restTemplate.exchange("http://127.0.0.1:8085/company", HttpMethod.GET, entity, String.class).getBody();
        }catch(Exception e) {
            return e.getMessage();
        }
    }


    @RequestMapping(value = "/admin/company/employee")
    public String getCustomerDetails() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);
        System.out.println("Inside second method88888");
        return restTemplate.exchange("http://127.0.0.1:8087/company", HttpMethod.GET, entity, String.class).getBody();

        //return restTemplate.exchange("http://localhost:8082/company/employee", HttpMethod.GET, entity, String.class).getBody();
    }


    @RequestMapping(value = "/admin")
    public String getAdmin(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        String ipAddress = request.getHeader("X-Forwarded-For");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        System.out.println("Inside admin service555555");
        System.out.println(request.getRequestURI());
        System.out.println(request.getLocalAddr());
        System.out.println(request.getRemoteAddr());
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
            ipAddress = inetAddress.getHostAddress();
            System.out.println("inetAddress"+inetAddress);
            System.out.println("ipAddress"+ipAddress);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Service Updated");
        HttpEntity <String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange("http://"+ipAddress+":8087/company", HttpMethod.GET, entity, String.class).getBody();
        //return "Inside Admin Page";
    }
}