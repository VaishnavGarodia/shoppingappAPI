
All Routes to the API are - 

●	To login user- 
1.	Request Method is post
2.	Link is http://3.16.109.253:8080/login
3.	Input required is json and should have “email” and “password”
4.	Output is string which gives-
    1)	InvalidUser if password is incorrect.
    2)	Returns a Token if the password is correct
    3)  404 Not Found if user does not exist           
5.	Example-
{"email":"abc@gmail.com","password":"deep"}
 

               
●	To signUp new User-
1.	Request Method is post
2.	Link is http://3.16.109.253:8080/addUser
3.	Input required is json and should have “email”,“password”,”phone”,”address”,”name”
4.	“Password” is first hashed and only then saved on the server.
5.	Output is string which returns a Token if successful.
6.	Example-
{"email":"abc@gmail.com","password":"deep"}


●	To Place new order on Ecommerce app- 
1.	  Request Method is post
2.	  Link is http://3.16.109.253:8080/addProduct
3.	 Input required is json and should have “email” , ”token” , ”productName”                
4.	Output is string which gives-
          1.success if the token is correct and product is added.
          2.Returns code : 403 Forbidden if the token is incorrect.
           
5.Example-
{"email":"abcd@gmail.com","productName":"laptop","token":"oocnxm0.540390055d2494227dasd"}

●	To View all orders of a particular user on Ecommerce app
1.	Request Method is GET
2.	 Link is-
http://3.16.109.253:8080/viewOrder/{email}/token/{token}
3. Output is JsonArray which gives an Array of Json and each Json in the array gives all details that are : “id”,”email”,”productName”,"time and date of order"(if token is incorrect we get 403 forbidden)  
4.Example-
http://3.16.109.253:8080/viewOrder/abc@gmail.com/token/oocnxm0.37122841907082316dasd
















