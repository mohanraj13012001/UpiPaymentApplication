import React from "react";
import mobipay1 from "../assests/Image3.png";
import mobipay2 from "../assests/mobipayDesign.jpeg";
import mobipay3 from "../assests/MoneyTrasfer.jpg";

const Slides = () => {
  return (
    <div
      id="carouselExampleControls"
      className="carousel slide"
      data-bs-ride="carousel"
      data-bs-interval="3000" 
    >
      <div className="carousel-inner">
        <div className="carousel-item active">
          <img
            className="d-block w-100"
            src={mobipay1}
            alt="First slide"
            style={{ objectFit: "cover", height: "400px" }}
          />
        </div>
        <div className="carousel-item">
          <img
            className="d-block w-100"
            src={mobipay2}
            alt="Second slide"
            style={{ objectFit: "cover", height: "400px" }}
          />
        </div>
        <div className="carousel-item">
          <img
            className="d-block w-100"
            src={mobipay3}
            alt="Third slide"
            style={{ objectFit: "cover", height: "400px" }}
          />
        </div>
      </div>
      <button
        className="carousel-control-prev"
        type="button"
        data-bs-target="#carouselExampleControls"
        data-bs-slide="prev"
      >
        <span className="carousel-control-prev-icon" aria-hidden="true"></span>
        <span className="visually-hidden">Previous</span>
      </button>
      <button
        className="carousel-control-next"
        type="button"
        data-bs-target="#carouselExampleControls"
        data-bs-slide="next"
      >
        <span className="carousel-control-next-icon" aria-hidden="true"></span>
        <span className="visually-hidden">Next</span>
      </button>
    </div>
  );
};

export default Slides;