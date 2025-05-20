import React, { useState } from "react";
import axios from "axios";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import mobipayLogo from "../assests/mobipayDesign.jpeg"; 


const RegisterUser = () => {
  const [username, setUsername] = useState("");
  const [phoneNumber, setLocalPhoneNumber] = useState("");
  const [otp, setOtp] = useState("");
  const [generatedOtp, setGeneratedOtp] = useState("");
  const [secretPin, setSecretPin] = useState("");
  const [isOtpVerified, setIsOtpVerified] = useState(false);
  const [errors, setErrors] = useState({}); 

  const validateFields = () => {
    const newErrors = {};
    if (!username) newErrors.username = "Username is required.";
    if (!phoneNumber) newErrors.phoneNumber = "Phone number is required.";
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

  const handleRegister = async () => {
    if (!validateFields()) return;

    const userData = {
      username, 
      phoneNumber,
      upiEnabled: true,
      balance: 0,
      max_amount: 0,
      max_transfer: 0,
      secretPin,
      sentTransfers: [],
      receivedTransfers: [],
    };

    try {
      const response = await axios.post("http://localhost:8080/api/v1/upi", userData);
      toast.success("User registered successfully!");
      console.log(response.data);
      clearAllFields(); 
    } catch (error) {
      console.error("Error registering user:", error);
      toast.error("Failed to register user. Please try again.");
    }
  };

  const clearAllFields = () => {
    setUsername("");
    setLocalPhoneNumber("");
    setOtp("");
    setGeneratedOtp("");
    setSecretPin("");
    setIsOtpVerified(false);
    setErrors({});
  };

  return (
    <div
      className="register-container"
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        height: "100vh",
        backgroundColor: "navy", 
      }}
    >
      {/* Inner Div */}
      <div
        className="inner-container"
        style={{
          display: "flex",
          width: "80%",
          maxWidth: "900px",
          backgroundColor: "white",
          borderRadius: "10px",
          overflow: "hidden",
          boxShadow: "0 4px 8px rgba(0, 0, 0, 0.2)",
        }}
      >
        {/* Left Section (Logo) */}
        <div
          className="left-section"
          style={{
            flex: 1,
            backgroundColor: "#f5f5f5",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            padding: "20px",
          }}
        >
          <img
            src={mobipayLogo}
            alt="Mobipay Logo"
            style={{ width: "150px", height: "auto" }}
          />
        </div>

        {/* Right Section (Register Form) */}
        <div
          className="right-section"
          style={{
            flex: 1,
            padding: "40px",
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
          }}
        >
          <h2 className="text-center" style={{ marginBottom: "20px" }}>
            Register
          </h2>
          <div className="form-group mb-3">
            <label htmlFor="username">First Name</label>
            <input
              type="text"
              id="username"
              className="form-control"
              placeholder="Enter your first name"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
            {errors.username && <small className="text-danger">{errors.username}</small>}
          </div>
          <div className="form-group mb-3">
            <label htmlFor="phoneNumber">Phone Number</label>
            <input
              type="text"
              id="phoneNumber"
              className="form-control"
              placeholder="Enter your phone number"
              value={phoneNumber}
              onChange={(e) => setLocalPhoneNumber(e.target.value)}
            />
            {errors.phoneNumber && <small className="text-danger">{errors.phoneNumber}</small>}
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
              <label htmlFor="secretPin">Set Secret PIN</label>
              <input
                type="password"
                id="secretPin"
                className="form-control"
                placeholder="Enter a 6-digit PIN"
                value={secretPin}
                onChange={(e) => setSecretPin(e.target.value)}
              />
              {errors.secretPin && <small className="text-danger">{errors.secretPin}</small>}
            </div>
          )}
          <button className="btn btn-primary button" onClick={handleRegister} disabled={!isOtpVerified}>
            Register
          </button>
        </div>
      </div>
    </div>
  );
};

export default RegisterUser;