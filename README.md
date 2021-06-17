Sample requests:

1) for creating one payment:
- method POST
- URI    localhost:8080/credorax/payments
- body   
{
  "invoice": 1234533,
  "amount": 30,
  "currency": "CAD",
  "cardholder": {
    "name": "Ass Dave",
    "email": "sss@domain.com"
  },
  "card": {
    "pan": "4200029000000001",
    "expiry": "0523",
    "cvv": "789"
  }
}
- header content-type application/json

2) for retrieving one invoice:
- method     GET
- URL        localhost:8080/credorax/transactions/1234533



