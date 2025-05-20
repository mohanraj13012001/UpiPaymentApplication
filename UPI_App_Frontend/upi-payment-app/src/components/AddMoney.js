import React, { useState, useContext } from "react";
import axios from "axios";
import { UPIContext } from "./UpiProvider";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const AddMoney = () => {
  const { phoneNumber } = useContext(UPIContext);
  const [amount, setAmount] = useState("");
  const [otp, setOtp] = useState("");
  const [generatedOtp, setGeneratedOtp] = useState("");
  const [secretPin, setSecretPin] = useState("");
  const [isOtpVerified, setIsOtpVerified] = useState(false);
  const [errors, setErrors] = useState({});
  const [loading, setLoading] = useState(false);

  const validateFields = () => {
    const newErrors = {};
    if (!amount || isNaN(amount) || amount <= 0) newErrors.amount = "Valid amount is required.";
    if (!otp && generatedOtp) newErrors.otp = "OTP is required.";
    if (isOtpVerified && !secretPin) newErrors.secretPin = "Secret PIN is required.";
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const generateOtp = () => {
    if (!phoneNumber) {
      setErrors({ phoneNumber: "Phone number is required to generate OTP." });
      return;
    }
    const randomOtp = Math.floor(100000 + Math.random() * 900000).toString();
    setGeneratedOtp(randomOtp);
    toast.info(`Your OTP is: ${randomOtp}`, { autoClose: 5000 });
    setErrors({});
  };

  const verifyOtp = () => {
    if (!otp) {
      setErrors({ otp: "OTP is required to verify." });
      return;
    }
    if (otp === generatedOtp) {
      setIsOtpVerified(true);
      toast.success("OTP verified successfully!");
      setErrors({});
    } else {
      setErrors({ otp: "Invalid OTP. Please try again." });
      toast.error("Invalid OTP. Please try again.");
    }
  };

  const handleAddMoney = async () => {
    if (!validateFields()) return;

    const requestData = {
      phoneNumber,
      amount: parseFloat(amount),
      secretPin,
    };

    try {
      setLoading(true);
      const response = await axios.post("http://localhost:8080/api/v1/upi/add-money", requestData);

      if (response.data.message) {
        toast.error(response.data.message);
      } else {
        toast.success("Money added successfully!");
        console.log(response.data);
        clearAllFields();
      }
    } catch (error) {
      console.error("Error adding money:", error);

      if (error.response && error.response.data && error.response.data.message) {
        toast.error(error.response.data.message);
      } else {
        toast.error("Failed to add money. Please try again.");
      }
    } finally {
      setLoading(false);
    }
  };

  const clearAllFields = () => {
    setAmount("");
    setOtp("");
    setGeneratedOtp("");
    setSecretPin("");
    setIsOtpVerified(false);
    setErrors({});
  };

  return (
    <div className="container mt-5">
      <h2 className="text-center">Add Money</h2>
      <div className="form-group mb-3">
        <label htmlFor="amount">Enter Amount</label>
        <input
          type="number"
          id="amount"
          className="form-control"
          placeholder="Enter the amount to add"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
        />
        {errors.amount && <small className="text-danger">{errors.amount}</small>}
      </div>
      <div className="form-group mb-3">
        <button className="btn btn-primary" onClick={generateOtp}>
          Generate OTP
        </button>
      </div>
      {generatedOtp && (
        <div className="form-group mb-3">
          <label htmlFor="otp">Enter OTP</label>
          <input
            type="text"
            id="otp"
            className="form-control"
            placeholder="Enter the OTP"
            value={otp}
            onChange={(e) => setOtp(e.target.value)}
          />
          {errors.otp && <small className="text-danger">{errors.otp}</small>}
          <button className="btn btn-success mt-2" onClick={verifyOtp}>
            Verify OTP
          </button>
        </div>
      )}
      {isOtpVerified && (
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
          {errors.secretPin && <small className="text-danger">{errors.secretPin}</small>}
        </div>
      )}
      <button
        className="btn btn-primary"
        onClick={handleAddMoney}
        disabled={!isOtpVerified || loading}
      >
        {loading ? "Processing..." : "Add Money"}
      </button>
    </div>
  );
};

export default AddMoney;