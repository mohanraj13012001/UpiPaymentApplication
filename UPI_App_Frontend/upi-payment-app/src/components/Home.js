import React from 'react';
import Slides from './Slides';
import Footer from './Footer';
import teamImage from '../assests/about_image.png'; 

const Home = () => {
  return (
    <div>
      <Slides />
      <div className="container mt-5">
        {/* Header Section */}
        <div className="header" style={{ marginBottom: '0px', textAlign: 'left' }}>
          <h2
            className="column-title"
            style={{
              marginBottom: '0px', 
            }}
          >
            Welcome To Mobipay
          </h2>
          <span
            className="animate-border tw-mb-40"
            style={{
              display: 'block',
              width: '80px',
              height: '4px',
              backgroundColor: '#6351ce',
              borderRadius: '2px',
              animation: 'expand 1.5s ease-in-out infinite',
              marginLeft: '0', 
              marginTop: '0', 
            }}
          ></span>
        </div>

        {/* Content Section */}
        <div className="d-flex align-items-center" style={{ marginTop: '0',gap: '20px' }}>
          {/* Text Section */}
          <div className="text-section" style={{ flex: 1, paddingRight: '20px'}}>
            <p>
              "MOBIPAY is a Brand of FAVUX NETWORK PVT LTD. Company registered under The Startup India program of Government of India, set up in November 2020 by a team of professionals with experience in Digital Banking &amp; Payments industry. The team works on deep insights and understanding of payment and transaction technology space. We operate on a B2B model, where we partner with neighborhood retail stores who can offer Assisted Digital Financial Services like Recharge (Mobile, Dth, Data Card), Bill Payment System (Electricity, Landline, Mobile Bill Payment, Gas, Water, FASTag), Aadhaar Enabled Payment System (AEPS), Domestic Money Transfer, Pan Card (UTI / NSDL), Travel Ticket Booking (Bus, Hotel, Flight) mPOS Machine by MOBIPAY App. Our innovative solutions are modeled to make the financial transactions seamless, quick, easy and strives to empower our retailer partners."
            </p>
          </div>

          {/* Image Section */}
          <div className="image-section" style={{ flex: 1 }}>
            <img
              src={teamImage}
              alt="Team working together"
              style={{ width: '100%', height: 'auto', borderRadius: '10px' }}
            />
          </div>
        </div>
      </div>

      {/* Add margin-top to the footer */}
      <div style={{ marginTop: '50px' }}>
        <Footer />
      </div>
    </div>
  );
};

export default Home;