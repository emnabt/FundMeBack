package tn.esprit.fundme.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.fundme.entities.GeoIP;
import tn.esprit.fundme.services.RawDBDemoGeoIPLocationService;

@RestController
public class GeoIPController {
   /* private RawDBDemoGeoIPLocationService locationService;
    
    public GeoIPController() throws IOException {
        locationService = new RawDBDemoGeoIPLocationService();
    }
    
    @PostMapping("/GeoIPTest")
    public GeoIP getLocation(String ipAddress) throws Exception {
    	ipAddress="116.73.210.21";
        RawDBDemoGeoIPLocationService locationService 
          = new RawDBDemoGeoIPLocationService();
        return locationService.getLocation(ipAddress);
    }*/


}