# shopping-app
This is a demo spring boot rest api  application which exposes the following endpoints,

**1. Post items to a cart of a user:**
A user can add items to his cart using this endpoint.
Ex. request
```
curl --location --request POST 'http://localhost:8080/cart' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userContactNo" : "91-9742527000",
    "productQuantityList": [
        {
        "product": "a7b8972b-71d7-45ff-bca5-78ef40297461",
        "quantity": 5
        }
    ]
}'
```
Response:
```
Status code : 201 (Created)
```
**2. Get items present in the cart of a user**
Retrieve details of items present in the cart of the user
Ex. request
```
curl --location --request GET 'http://localhost:8080/cart?userContactNo=91-9742527000'
```
Response:
```
Status code: 200 Ok

respone body:
[
    {
        "productId": "a7b8972b-71d7-45ff-bca5-78ef40297461",
        "productName": "apple",
        "quantity": 5,
        "pricePerItem": 10,
        "offerInformation": "nil",
        "price": 50
    }
]
```
**How to run this app?**
1. Check out this project in your IDE and run as java application, the endpoints will be exposed to localhost:8080
2. If you have docker and maven in your system, you can run the following command,
```
1. mvn spring-boot:build-image
2. docker run -p 8080:8080 -t shopping-app:0.0.1-SNAPSHOT
```
the endpoints will be exposed to localhost:8080

**DB schema diagram**

<img width="641" alt="Screenshot 2021-05-16 at 11 26 48" src="https://user-images.githubusercontent.com/25589745/118387072-b1f5d000-b639-11eb-9105-91f33de226eb.png">

**DB Used**

This Db uses in memory H2 database, with preloaded data from the sql script, _/shopping-app/src/main/resources/data.sql_

**Improvements - To do**

1. Do not pass user id in rest api calls, add authentication/ authorization via identiy provider
2. Expose end points to do curd operations on the data base
3. Use Swagger to do endpoints documentations/pact for others to use


