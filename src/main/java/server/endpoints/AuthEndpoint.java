package server.endpoints;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.google.gson.Gson;
import server.models.User;
import server.providers.UserProvider;
import server.util.Auth;
import server.util.Config;
import server.util.Log;

import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Filip on 10-10-2017.
 */

@Path("/auth")
public class AuthEndpoint {

    //Creating objects of the classes UserProvider and User

    ArrayList<String> tokenArray = new ArrayList<String>();

   UserProvider userProvider = new UserProvider();
   User foundUser = new User();
   Log log = new Log();
   String checkHashed;

    /** This method authorizes an user by e-mail and password. To protect the users password, this method employ salted password hashing.
     * This method also converts from JSON to GSON
     * @param jsonUser
     * @return This method returns different response status codes defined by HTTP
     */
   @POST
   public Response AuthUser(String jsonUser) {

       User authUser = new Gson().fromJson(jsonUser, User.class);
       String token = null;

       //Creating try-catch to check if the user is authorized by e-mail and password
       try {
           foundUser = userProvider.getUserByEmail(authUser.getEmail());
       } catch (Exception e) {

           log.writeLog("DB",this.getClass(),("An exception occurred while running AuthUser - " +
                   "User active was: " + authUser.getEmail()),1);

           return Response.status(401).type("json/application").entity(new Gson().toJson("User not authorized")).build();
       }
      checkHashed = Auth.hashPassword(authUser.getPassword(), foundUser.getSalt());

      //Creating if-else statement to check if the hashed password equals the password of a specific user.
      if (checkHashed.equals(foundUser.getPassword())) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(Config.getJwtSecret());
            long timevalue;
            timevalue = (System.currentTimeMillis()*1000)+20000205238L;
            Date expDate = new Date(timevalue);

            token = JWT.create().withClaim("email",foundUser.getEmail()).withKeyId(String.valueOf(foundUser.getId()))
                    .withExpiresAt(expDate).withIssuer("ROFL").sign(algorithm);
           // tokenArray.add(token);
        }catch (UnsupportedEncodingException e){

            log.writeLog("DB",this.getClass(),("An UnsupportedEncoding occurred while running AuthUser - " +
                    "User active was: " + authUser.getEmail()),1);

            e.printStackTrace();
        }catch (JWTCreationException e){

            log.writeLog("DB",this.getClass(),("A JWTCreation exception occurred while running AuthUser - " +
                    "User active was: " + authUser.getEmail()),1);

            e.printStackTrace();
        }

          log.writeLog(this.getClass().getName(),this.getClass(),("AuthUser was successful and user was authorized - " +
                  "User active was: " + authUser.getEmail()),0);

          return Response.status(200).type("json/application").entity(new Gson().toJson(token)).build();
      } else {

          log.writeLog(this.getClass().getName(),this.getClass(),("AuthUser was successful but user not authorized - " +
                  "User active was: " + authUser.getEmail()),0);

          return Response.status(401).type("json/application").entity(new Gson().toJson("User not authorized")).build();
   }
   }

   }
