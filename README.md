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


##### Get - / account / {accountId}
example: / account / 3

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
[
{
id: 2,
transactionList: [ ],
user1: "sara",
user2: "isak",
netBalance: 0
},
{
id: 1,
transactionList: [
2,
4
],
user1: "sara",
user2: "frida",
netBalance: 0
},
{
id: 3,
transactionList: [
5
],
user1: "sara",
user2: "julli",
netBalance: 666
}
]
```


##### Get - / transaction / {transactionId}
example: / transaction / 3

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


##### Post - / transaction / new
example: / transaction / new
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

