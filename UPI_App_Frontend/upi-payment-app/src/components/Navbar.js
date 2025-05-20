import React from "react";
import { Link } from "react-router-dom";
import logo from "../assests/logo.png";

export default function Navbar() {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
      <div className="container">
        <Link className="navbar-brand d-flex align-items-center" to="/">
          <img
            src={logo}
            alt="MobiPay Logo"
            style={{ height: "50px", marginRight: "10px", width: "200px" }}
          />
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav ms-auto">
            <li className="nav-item">
              <Link className="nav-link" to="/">
                Home
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/register">
                Register
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/login">
                Login
              </Link>
            </li>
            <li className="nav-item dropdown">
              <Link
                className="nav-link dropdown-toggle"
                to="#"
                id="contactDropdown"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                Contact Us
              </Link>
              <ul className="dropdown-menu" aria-labelledby="contactDropdown">
                <li>
                  <span className="dropdown-item contact-item">
                    <strong>Phone:</strong> +91 98765 43210
                  </span>
                </li>
                <li>
                  <span className="dropdown-item contact-item">
                    <strong>Email:</strong> support@mobipay.com
                  </span>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}