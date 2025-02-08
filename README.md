# Razorpay Payment Gateway Service

This is a **Razorpay Payment Gateway Service** application, which allows you to make payments, receive payment responses, and update transaction status using Razorpay's API.

The application exposes multiple API endpoints to interact with the Razorpay service, including initiating payments, receiving payment responses, and updating transactions. Once payment information is submitted, the system generates a short URL, and after the payment is completed, a webhook notification will be sent to your application.

## Features

- **Make Payment API**: Initiates a payment and returns a short URL.
- **Receive Payment Response**: Handles the payment response and updates your application accordingly.
- **Transaction Update API**: Updates the transaction status after the payment has been made.
- **Webhook Notification**: A webhook is sent to your application after the payment is completed.
- **Swagger Documentation**: Access all the available APIs via Swagger UI.

## Swagger UI Documentation

You can explore all available API endpoints and interact with them via **Swagger UI**.

Access the Swagger documentation here:  
[Swagger UI - Razorpay Service](http://localhost:8080/razorpay-service/swagger-ui/index.html#/razorpay-controller/makePayment)

---

## API Endpoints

### 1. **Make Payment**
- **Endpoint**: `/api/payment/makePayment`
- **Method**: `POST`
- **Description**: Initiates a payment transaction.
-- **Curl**: ```curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
'{
  "amount": 105,
  "referenceId": "TXN00000001",
  "description": "Payment for policy",
  "name": "Amarjeet",
  "contact": "9948813284",
  "email": "amarjeet@gmail.com"
}' \
 'http://localhost:8080/razorpay-service/api/make-payment'```
