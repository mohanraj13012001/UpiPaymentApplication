import React, { useContext, useState } from "react";
import axios from "axios";
import { UPIContext } from "./UpiProvider";

const CheckBalance = () => {
  const { phoneNumber } = useContext(UPIContext);
  const [secretPin, setSecretPin] = useState("");
  const [balance, setBalance] = useState(null);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const fetchBalance = async () => {
    if (!phoneNumber) {
      setError("Phone number is not available. Please register or log in.");
      return;
    }

    if (!secretPin) {
      setError("Secret PIN is required to fetch the balance.");
      return;
    }

    setLoading(true);
    setError("");
    try {
      const response = await axios.get(
        `http://localhost:8080/api/v1/upi/balance?phoneNumber=${phoneNumber}&secretPin=${secretPin}`
      );
      setBalance(response.data.balance);
    } catch (err) {
      console.error("Error fetching balance:", err);
      setError("Failed to fetch balance. Please check your credentials and try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container mt-5">
      <h2 className="text-center">Check Balance</h2>
      {error && <div className="alert alert-danger">{error}</div>}
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
      <div className="text-center mb-3">
        <button className="btn btn-primary" onClick={fetchBalance} disabled={loading}>
          {loading ? "Fetching..." : "Check Balance"}
        </button>
      </div>
      {balance !== null && (
        <div className="alert alert-success text-center">
          <h4>Your Balance: ₹{balance}</h4>
        </div>
      )}
    </div>
  );
};

export default CheckBalance;