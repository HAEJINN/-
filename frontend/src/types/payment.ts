export interface Payment {
  receiver: string;
  amount: number;
  impUid: number;
  merchantUid: number;
}

export interface PaymentRequest {
  data: Payment;
  jwtToken: string;
}

export interface PaymentResponse {
  data: Payment;
}
