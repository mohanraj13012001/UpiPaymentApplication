import React, { useState, useContext } from "react";
import axios from "axios";
import { UPIContext } from "./UpiProvider";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate } from "react-router-dom"; 
import "../css/Login.css";
import mobipayLogo from "../assests/mobipayDesign.jpeg";  

const Login = () => {
  const { setPhoneNumber, setSecretPin } = useContext(UPIContext);
  const [phoneNumber, setLocalPhoneNumber] = useState("");
  const [secretPin, setLocalSecretPin] = useState("");
  const [upiEnabled, setUpiEnabled] = useState(true);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate(); 

  const handleLogin = async () => {
    if (!phoneNumber) {
      toast.error("Phone number is required."); 
      return;
    }
    if (!secretPin) {
      toast.error("Secret PIN is required."); 
      return;
    }

    setLoading(true);

    const loginData = {
      phoneNumber,
      secretPin,
      upiEnabled,
    };

    try {
      const response = await axios.post("http://localhost:8080/api/v1/upi/login", loginData);
      toast.success("Login successful!");
      console.log("Login Response:", response.data);

      setPhoneNumber(phoneNumber); 
      setSecretPin(secretPin); 
      setLocalPhoneNumber("");
      setLocalSecretPin("");

      navigate("/dashboard"); 
    } catch (err) {
      console.error("Login Error:", err);
      toast.error("Failed to log in. Please check your credentials and try again."); 
    } finally {
      setLoading(false);
    }
  };

  return (
    <div
      className="login-container"
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
            Login
          </h2>
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
          </div>
          <div className="form-group mb-3">
            <label htmlFor="secretPin">Secret PIN</label>
            <input
              type="password"
              id="secretPin"
              className="form-control"
              placeholder="Enter your secret PIN"
              value={secretPin}
              onChange={(e) => setLocalSecretPin(e.target.value)}
            />
          </div>
          <div className="form-group mb-3">
            <label htmlFor="upiEnabled">UPI Enabled</label>
            <select
              id="upiEnabled"
              className="form-control"
              value={upiEnabled}
              onChange={(e) => setUpiEnabled(e.target.value === "true")}
            >
              <option value="true">Yes</option>
              <option value="false">No</option>
            </select>
          </div>
          <button
            className="btn btn-primary login-button"
            onClick={handleLogin}
            disabled={loading}
          >
            {loading ? "Logging in..." : "Login"}
          </button>
        </div>
      </div>
    </div>
  );
};

export default Login;