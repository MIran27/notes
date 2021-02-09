# User Note Management Application 

####Prerequisites
 - maven (version 3.6.1)
 - Java  (JDK 1.8)
 
####setup

clone the project from github use the fallowing commands

 -  git clone https://github.com/MIran27/Note.git
 - cd Note
 - mvn clean install 
 - cd target
 - java -jar notes-0.0.1-SNAPSHOT.jar
 
 
 #### API Guidance
 **First of all you have to use login api to get the token. This token must have in a request header in note APIs 
   this token is used to identify the user. Use the following username and password to get access token.
 - username  - test_user
 - password - test_user   
 
 I committed the Postman collection( Note.postman_collection.json) as well. Replace the token before using the note APIs. call the login API to generate token.
 **Login user API**   
 - URL - localhost:8080/api/v1/user/login
 - Method - POST
 - Request Body.  
  {
  
  
                    	"userName" : "test_user",
                    	"password" : "test_user"
                    }
                    
 - Response Body.
 
  {
  
                "status": 200,
                "message": "SUCCESS",
                "result": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0X3VzZXIiLCJleHAiOjE2MTA5MjgyNDAsImlhdCI6MTYxMDg5MjI0MH0.6YCV4zaxztXuvVlh11BxUQxCo1fSKV87Q17KMM-Typg"
            }
#####**Create the new user** 

- URL - localhost:8080/api/v1/user/
 - Method - POST
 - Request Body.
 
 {
 
 	"username" : "new_user",
 	"password" : "123"
 }
 
 - Response Body.
 
 {
 
     "status": 201,
     "message": "USER CREATED SUCCESSFULLY",
     "result": {
         "username": "new_user",
         "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZXdfdXNlciIsImV4cCI6MTYxMDkyNTI1NywiaWF0IjoxNjEwODg5MjU3fQ.yXiSFqpqCbTfYtd2uE-3Fbgx6-MZAEox93dy7O49Ack"
     }
     
 }
 ####**Create a new note** 
 ##### Following API must have a token in request header to authenticate the user. 
 - URL - localhost:8080/api/v1/note
  - Method - POST
  - Request Body.
  
  {
  
  	"title":"code challenge",
  	"note":"testing note",
  	"archive":false
  }
  
 - Request Header
 
 "Content-Type: "application/json"
 
 "Authorization": "Bearer QWVwb25hVfdghfdghdfghdfgWF6eHN3MjNl"
 
 - Response Body
 {
 
 
     "status": 201,
     "message": "SUCCESSFULLY CREATE A NOTE",
     "result": {
         "noteId": 7,
         "note": "test",
         "title": "code challenge"
     }
 }
 
 ####**List saved notes that aren't archived** 
  - URL - localhost:8080/api/v1/note
   - Method - GET
   - Request Header
    
    "Content-Type: "application/json"
    
    "Authorization": "Bearer QWVwb25hVfdghfdghdfghdfgWF6eHN3MjNl"
    
    - Response Body
    {
    
    
        "status": 200,
        "message": "SUCCESS",
        "result": [
            {
                "noteId": 4,
                "note": "test note 1",
                "title": "code challenge"
            },
            {
                "noteId": 5,
                "note": "test note 1",
                "title": "code challenge"
            },
            {
                "noteId": 6,
                "note": "test note 1",
                "title": "code challenge"
            }
        ]
    }
    
####**List notes that are archived** 
   - URL - localhost:8080/api/v1/note/archive
   - Method - GET
   - Request Header
      
    "Content-Type: "application/json"
    "Authorization": "Bearer QWVwb25hVfdghfdghdfghdfgWF6eHN3MjNl"
    
   - Response Body
   
   {
   
       "status": 200,
       "message": "SUCCESS",
       "result": [
           "code challenge",
           "code challenge"
       ]
   }
   
####**Unarchive a previously archived note** 
   - URL - localhost:8080/api/v1/note/archive/{id}
   - Method - PUT
   - Request Header
   
    "Content-Type: "application/json"
    
    "Authorization": "Bearer QWVwb25hVfdghfdghdfghdfgWF6eHN3MjNl"
   
   - Request Body
   
   {
   
   	"archive":false
   
   } 
   
   - Response Body
   
   {
   
       "status": 200,
       "message": "SUCCESSFULLY UPDATED"
   }
   
####**Archive a note** 
   - URL - localhost:8080/api/v1/note/archive/{id}
   - Method - PUT
   - Request Header
   
    "Content-Type: "application/json"
    
    "Authorization": "Bearer QWVwb25hVfdghfdghdfghdfgWF6eHN3MjNl"
   
   - Request Body
   
   {
   
   	"archive":true
   
   } 
   
   - Response Body
   
   {
   
       "status": 200,
       "message": "SUCCESSFULLY UPDATED"
   }
   
####**Update a previously saved note** 
   - URL - localhost:8080/api/v1/note/{id}
   - Method - PUT
   - Request Header
   
    "Content-Type: "application/json"
       
    "Authorization": "Bearer QWVwb25hVfdghfdghdfghdfgWF6eHN3MjNl"
    
   - Request Body
   
   {
   
   	"archive":false,
   	"note": "change the note ",
    "title": "code challenge"
   }
   
   - Response Body
   
   {
   
       "status": 200,
       "message": "SUCCESSFULLY UPDATED"
   }
   
   ####**Delete a saved note** 
   
   - URL - localhost:8080/api/v1/note/{id}
   - Method - Delete
   - Request Header
     
    "Content-Type: "application/json"
          
    "Authorization": "Bearer QWVwb25hVfdghfdghdfghdfgWF6eHN3MjNl"
    
   - Response Body
   
   {
   
       "status": 200,
       "message": "SUCCESS",
       "result": "Successfully deleted"
   }
   
   I used java and Spring Boot framework because I'm mostly familiar with java and easily build REST API using Spring Boot framework
   
   ###Improvements 
   - We can add user mange APIs(for CRUD operations)
   - We can dockerize the application.
   - We can improve the authentication part using refresh token. 
   - We can improve the authorization part using different user levels
   - I used the in-memory database for this application. 
   - We can use a proper database like MysqlIn a  production deployment  
   - we have to use database replication. 
   - In a  production deployment we have to use a load balancer.We can create a CICD pipeline using Jenkins.  
    
   
   
   
   
   
   
   
    
    
 
 
 
 

 
 
 
 
         
            
            
            
            
            
            
            
            
            
            
 
 


