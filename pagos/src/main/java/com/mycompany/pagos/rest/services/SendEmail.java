/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pagos.rest.services;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.in;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;



/**
 *
 * @author Johan
 */

@Path("test")
@Produces(MediaType.APPLICATION_JSON)
public class SendEmail {
    
   private static final String EMAIL = "senasoft2018@gmail.com";
   private static final String PASSWORD = "senasoft2018**";
   private static final String FOLDER = "C:\\Users\\Johan\\Documents\\Scratch Projects\\";
   
   @POST
   @Path("email")
   public void sendEmail(@QueryParam("email") String email) {
       
       try {
           HtmlEmail he = new HtmlEmail();
           he.setHostName("smtp.gmail.com");
           he.setSmtpPort(465);
           he.setAuthentication(EMAIL, PASSWORD);
           he.setAuthenticator(new DefaultAuthenticator(EMAIL, PASSWORD));
           he.setSSLOnConnect(true);
           
           he.setFrom(EMAIL);
           
           StringBuffer sb = new StringBuffer();
           sb.append("</html></body>");
           sb.append("<img src=cid:").append(he.embed(new File(FOLDER.concat("25348836_952720421549352_4678510988746715395_n.jpg"))));
           sb.append("></body></html>");
           sb.append(he.embed(new File(FOLDER.concat("csv.csv"))));
           
           he.addTo(email);
           he.setCharset("UTF-8");
           he.setHtmlMsg(sb.toString());
           he.send();
           
       } catch (EmailException e) {
           System.out.println("ERROR " + e);
       }
   }
   
   @POST
   @Path("upload")
   public void uploadFile(@FormDataParam("file") InputStream in,
                          @FormDataParam("file") FormDataContentDisposition info ) throws IOException {
       try {
           File file = new File(FOLDER.concat(info.getFileName()));
           while (file.exists()) {
               int prefix = (int) (Math.random() * 10000000);
               file = new File(FOLDER.concat(String.valueOf(prefix)).concat(info.getFileName()));
           }
           Files.copy(in, file.toPath());
       } catch (IOException e) {
           throw e;
       }
   }
   
   @POST
   @Path("csv")
   public void uploadCsv(@FormDataParam("file") InputStream in){
       Scanner scanner = new Scanner(in);
       List<List<String>> lst = new ArrayList();
       while(scanner.hasNext()) {
           String[] lines =  scanner.nextLine().split(",");
           lst.add(Arrays.asList(lines));
       }
       System.out.println("OLA " + lst);
   }
   
   @POST
   @Path("login")
   public Response login() {
       List<String> roles = new ArrayList();
       roles.add("USER");
       roles.add("ADMIN");
       if (true) {
           String jwt = Jwts.builder().setSubject("id")
                   .signWith(SignatureAlgorithm.HS256, "asdf")

                   .claim("user", "Johan Zapta")
                   .claim("roles", Arrays.toString(roles.toArray()))
                   .compact();
           JsonObject json = Json.createObjectBuilder().add("token", jwt).build();
           return Response.status(Response.Status.CREATED).entity(json).build();
       } else {
           return Response.status(Response.Status.UNAUTHORIZED).build();
       }
   }
   
   @POST
   @Path("date")
   public Response date(@QueryParam("date") List<String> date) {
       System.out.println("FECHAS " + date);
       
       
       try {
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
           Date fechaUno = new Date();
       fechaUno = sdf.parse(date.get(0));
           System.out.println("ASD " + fechaUno);
           
           
           Date fecha = DateUtils.addDays(new Date(), -1);
           System.out.println("Salida menos un dia " + fecha);
           
       return Response.status(Response.Status.OK).build();
       } catch (Exception e) {
           System.out.println("ERR " + e);
           return Response.status(Response.Status.CONFLICT).build();
       }
       
     
   }
}
