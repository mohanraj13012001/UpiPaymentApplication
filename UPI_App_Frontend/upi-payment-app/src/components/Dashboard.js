import React, { useState, useContext, useEffect } from "react";
import { Modal, Button, Card, Container, Row, Col } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import axios from "axios";
import AddMoney from "./AddMoney";
import CheckBalance from "./CheckBalance";
import TransferMoney from "./TransferMoney";
import { UPIContext } from "./UpiProvider";
import addMoneyIcon from "../assests/add-money.jpeg";
import checkBalanceIcon from "../assests/check-balance.jpeg";
import transferMoneyIcon from "../assests/image.png";
import upiLogo from "../assests/logo.png";

const Dashboard = () => {
  const [showModal, setShowModal] = useState(false);
  const [activeFeature, setActiveFeature] = useState(null);
  const [upiEnabled, setUpiEnabled] = useState(false);
  const [userDetails, setUserDetails] = useState(null);
  const { phoneNumber, setPhoneNumber, setSecretPin, logout } = useContext(UPIContext);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchUserDetails = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/v1/upi/${phoneNumber}`);
        setUserDetails(response.data);
        setUpiEnabled(response.data.upiEnabled);
      } catch (error) {
        console.error("Error fetching user details:", error);
        toast.error("Failed to fetch user details.");
        logout();
        navigate("/login");
      }
    };

    if (phoneNumber) {
      fetchUserDetails();
    } else {
      logout();
      navigate("/login");
    }
  }, [phoneNumber, logout, navigate]);

  const handleOpenModal = (feature) => {
    setActiveFeature(feature);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
    setActiveFeature(null);
  };

  const handleLogout = () => {
    logout();
    toast.success("Logged out successfully!");
    navigate("/login");
  };

  const toggleUpiStatus = async () => {
    try {
      const requestData = {
        phoneNumber,
        upiEnabled: !upiEnabled,
      };

      const response = await axios.post("http://localhost:8080/api/v1/upi/toggle", requestData);
      setUpiEnabled(response.data.upiEnabled);
      toast.success(
        `UPI has been ${response.data.upiEnabled ? "enabled" : "disabled"} successfully!`
      );
    } catch (error) {
      console.error("Error toggling UPI status:", error);
      toast.error("Failed to toggle UPI status. Please try again.");
    }
  };

  return (
    <div className="dashboard-wrapper" style={{ position: "relative", minHeight: "100vh" }}>
      <div className="d-flex justify-content-end align-items-center mb-3" style={{ padding: "20px", maxWidth: "100%" }}>
        <Button
          variant={upiEnabled ? "success" : "danger"}
          size="sm"
          onClick={toggleUpiStatus}
          style={{ marginRight: "10px" }}
        >
          {upiEnabled ? "UPI Enabled" : "UPI Disabled"}
        </Button>
        <Button
          variant="outline-danger"
          size="sm"
          onClick={handleLogout}
        >
          Logout
        </Button>
      </div>
      <Container className="pt-4">
        <Card
          className="text-white mb-4"
          style={{
            background: "linear-gradient(to right, #7F00FF, #E100FF)",
            borderRadius: "20px",
            padding: "20px",
            boxShadow: "0 4px 20px rgba(0, 0, 0, 0.2)",
            maxWidth: "100%",
          }}
        >
          <Card.Body>
            <div className="d-flex justify-content-between align-items-center">
              <div>
                <h4 className="mb-0">₹ {userDetails ? userDetails.balance : "0.00"}</h4>
                <small className="d-block">
                  Card Number: **** 3254 7760
                </small>
                <small style={{ opacity: 0.8 }}>
                  Card Holder: {userDetails ? userDetails.username : "Loading..."}
                </small>
              </div>
              <img
                src={upiLogo}
                alt="UPI Logo"
                style={{ width: "50px", height: "50px", borderRadius: "50%", background: "#fff" }}
              />
            </div>
          </Card.Body>
        </Card>
        <Row className="text-center mb-4 justify-content-center">
          <Col xs={12} sm={6} md={3} className="mb-3 d-flex justify-content-center">
            <Card
              className="feature-card h-100 hover-effect"
              onClick={() => handleOpenModal("AddMoney")}
              style={{
                width: "100%",
                maxWidth: "200px",
                cursor: "pointer",
              }}
            >
              <div className="text-center p-3">
                <Card.Img
                  variant="top"
                  src={addMoneyIcon}
                  style={{
                    width: "60%",
                    maxHeight: "100px",
                    objectFit: "contain",
                  }}
                />
              </div>
              <Card.Body>
                <Card.Text className="fw-bold">Add Money</Card.Text>
              </Card.Body>
            </Card>
          </Col>
          <Col xs={12} sm={6} md={3} className="mb-3 d-flex justify-content-center">
            <Card
              className="feature-card h-100 hover-effect"
              onClick={() => handleOpenModal("TransferMoney")}
              style={{
                width: "100%",
                maxWidth: "200px",
                cursor: "pointer",
              }}
            >
              <div className="text-center p-3">
                <Card.Img
                  variant="top"
                  src={transferMoneyIcon}
                  style={{
                    width: "60%",
                    maxHeight: "100px",
                    objectFit: "contain",
                  }}
                />
              </div>
              <Card.Body>
                <Card.Text className="fw-bold">Transfer</Card.Text>
              </Card.Body>
            </Card>
          </Col>
          <Col xs={12} sm={6} md={3} className="mb-3 d-flex justify-content-center">
            <Card
              className="feature-card h-100 hover-effect"
              onClick={() => handleOpenModal("CheckBalance")}
              style={{
                width: "100%",
                maxWidth: "200px",
                cursor: "pointer",
              }}
            >
              <div className="text-center p-3">
                <Card.Img
                  variant="top"
                  src={checkBalanceIcon}
                  style={{
                    width: "60%",
                    maxHeight: "100px",
                    objectFit: "contain",
                  }}
                />
              </div>
              <Card.Body>
                <Card.Text className="fw-bold">Balance</Card.Text>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
      <Modal show={showModal} onHide={handleCloseModal} centered>
        <Modal.Header closeButton>
          <Modal.Title>
            {activeFeature === "AddMoney" && "Add Money"}
            {activeFeature === "CheckBalance" && "Check Balance"}
            {activeFeature === "TransferMoney" && "Transfer Money"}
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {activeFeature === "AddMoney" && <AddMoney />}
          {activeFeature === "CheckBalance" && <CheckBalance />}
          {activeFeature === "TransferMoney" && <TransferMoney />}
        </Modal.Body>
      </Modal>
      <footer
        style={{
          backgroundColor: "#f8f9fa",
          padding: "10px 20px",
          textAlign: "center",
          position: "absolute",
          bottom: "0",
          width: "100%",
          borderTop: "1px solid #e7e7e7",
        }}
      >
        <p style={{ margin: "0", fontSize: "14px", color: "#6c757d" }}>
          © 2025 Mobipay. All rights reserved.
        </p>
      </footer>
    </div>
  );
};

export default Dashboard;