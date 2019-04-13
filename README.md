# Lil bill, rest api

Backend for the lil bill android app.


## Routes 

##### Get - / user / {userId}
example: / user / 2

```
response: 
{
id: 1,
username: "sara",
firstname: "sara",
lastname: "queen",
email: "sara.com",
password: "$2a$10$Jxjd8sIzPYvBGPOHf5Ze5eS.78xjQIkNaOO1t3z6eSnOeXyf8MFXi",
friendlist: [4,2,3]
}
```

##### Get - / login / {username}
example: / login / sara


```
response: 
{
id: 1,
username: "sara",
firstname: "sara",
lastname: "queen",
email: "sara.com",
password: "$2a$10$Jxjd8sIzPYvBGPOHf5Ze5eS.78xjQIkNaOO1t3z6eSnOeXyf8MFXi",
friendlist: [4,2,3]
}
```

##### Get - / user / {userID} / account / {accountId}
example: / user / 2 / account / 3

```
response: 
{
id: 3,
transactionList: [
5
],
user1: "sara",
user2: "julli",
netBalance: 666
}
```


##### Get - / user / {userId} / accounts
example: / user / 1 / accounts

```
response: 
{ accounts:[2,1,3,5] }
```


##### Get - / user / {userId} / transaction / {transactionId}
example: / user / 5 / transaction / 3

```
response: 
{
id: 3,
amount: 666,
descr: "virkar þetta",
date: "Apr 7, 2019"
}
```



##### Post - / user / new
example: / user / new
```
body: 
{
    "username": "sara",
    "firstname": "sara"
    "lastname": "queen",
    "email": "sara.com",
    "password": "sara"
}
```

```
response: 
{
    "id": 5,
    "username": "test",
    "firstname": "User",
    "lastname": "Lastname",
    "email": "test@test.com",
    "password": "$2a$10$yaGs/e807lIjiLJ0trjeDuQLsvJU3MPmy8rzW61NrBEpXVtuvpve6",
    "friendlist": []
}
```


##### Post - / user / {userId} / add / {friendName}
example: / user / 5 / add / isak
```
body: 
```

```
response: 
{
    "id": 6,
    "transactionList": [],
    "user1": "test",
    "user2": "isak",
    "netBalance": 0
}
```


##### Post - / user / {userId} / transaction / new
example: / user / 5 / transaction / new
```
body: 
{
    "accountId": "6",
    "amount": "500",
    "descr": "test ---- "
}
```

```
response: 
{
    "id": 6,
    "accountId": 6,
    "amount": 500,
    "descr": "test ---- ",
    "date": "Apr 7, 2019 9:33:54 PM"
}
```

