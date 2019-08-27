package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp(){
    String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("159.255.11.202");
    assertEquals(geoIP, "<GeoIP><Country>RU</Country><State>05</State></GeoIP>");
  }
}
