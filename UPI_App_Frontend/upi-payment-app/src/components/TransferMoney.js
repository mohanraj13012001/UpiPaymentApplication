import React, { useState, useContext } from "react";
import axios from "axios";
import { UPIContext } from "./UpiProvider";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css"; 

const TransferMoney = () => {
  const { phoneNumber } = useContext(UPIContext); 
  const [recipientPhoneNumber, setRecipientPhoneNumber] = useState(""); 
  const [amount, setAmount] = useState(""); 
  const [secretPin, setSecretPin] = useState(""); 
  const [loading, setLoading] = useState(false); 

  const handleTransfer = async () => {
  
    if (!phoneNumber) {
      toast.error("Your phone number is not available. Please log in.");
      return;
    }
    if (!recipientPhoneNumber) {
      toast.error("Recipient's phone number is required.");
      return;
    }
    if (!amount || isNaN(amount) || amount <= 0) {
      toast.error("Please enter a valid amount."); 
      return;
    }
    if (!secretPin) {
      toast.error("Secret PIN is required to complete the transaction.");
      return;
    }

    setLoading(true);

    try {
      
      const senderResponse = await axios.get(
        `http://localhost:8080/api/v1/upi/${phoneNumber}`
      );
      const fromUserId = senderResponse.data.id; 
      
      const recipientResponse = await axios.get(
        `http://localhost:8080/api/v1/upi/${recipientPhoneNumber}`
      );
      const toUserId = recipientResponse.data.id; 

   
      const transferData = {
        fromUserId,
        toUserId,
        amount: parseFloat(amount),
        secretPin,
      };

     
      const transferResponse = await axios.post(
        "http://localhost:8080/api/v1/transactions",
        transferData
      );

      toast.success("Money transferred successfully!"); 
      console.log("Transfer Response:", transferResponse.data);

    
      setRecipientPhoneNumber("");
      setAmount("");
      setSecretPin("");
    } catch (err) {
      console.error("Error during transfer:", err);

    
      if (err.response && err.response.data && err.response.data.message) {
        toast.error(err.response.data.message); 
      } else {
        toast.error("Failed to transfer money. Please check the details and try again."); 
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container mt-5">
      <h2 className="text-center">Transfer Money</h2>
      <div className="form-group mb-3">
        <label htmlFor="recipientPhoneNumber">Recipient's Phone Number</label>
        <input
          type="text"
          id="recipientPhoneNumber"
          className="form-control"
          placeholder="Enter recipient's phone number"
          value={recipientPhoneNumber}
          onChange={(e) => setRecipientPhoneNumber(e.target.value)}
        />
      </div>
      <div className="form-group mb-3">
        <label htmlFor="amount">Amount</label>
        <input
          type="number"
          id="amount"
          className="form-control"
          placeholder="Enter amount to transfer"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
        />
      </div>
      <div className="form-group mb-3">
        <label htmlFor="secretPin">Enter Secret PIN</label>
        <input
          type="password"
          id="secretPin"
          className="form-control"
          placeholder="Enter your secret PIN"
          value={secretPin}
          onChange={(e) => setSecretPin(e.target.value)}
        />
      </div>
      <button
        className="btn btn-primary"
        onClick={handleTransfer}
        disabled={loading}
      >
        {loading ? "Processing..." : "Transfer Money"}
      </button>
    </div>
  );
};

export default TransferMoney;