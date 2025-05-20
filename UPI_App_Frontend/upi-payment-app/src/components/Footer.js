import React from 'react';

const Footer = () => {
  return (
    <div className="footer-container">
      <footer className="text-center text-lg-start text-white" style={{ backgroundColor: '#1c2331', width: '100%', minHeight: '300px' }}>
        <section className="d-flex justify-content-between p-4" style={{ backgroundColor: '#6351ce' }}>
          <div className="me-5">
            <span>Stay connected with Mobipay on social networks:</span>
          </div>
          <div>
            <a href="https://facebook.com" className="text-white me-4 footer-link">
              <i className="fab fa-facebook-f"></i>
            </a>
            <a href="https://twitter.com" className="text-white me-4 footer-link">
              <i className="fab fa-twitter"></i>
            </a>
            <a href="https://google.com" className="text-white me-4 footer-link">
              <i className="fab fa-google"></i>
            </a>
            <a href="https://instagram.com" className="text-white me-4 footer-link">
              <i className="fab fa-instagram"></i>
            </a>
            <a href="https://linkedin.com" className="text-white me-4 footer-link">
              <i className="fab fa-linkedin"></i>
            </a>
            <a href="https://github.com" className="text-white me-4 footer-link">
              <i className="fab fa-github"></i>
            </a>
          </div>
        </section>
        <section>
          <div className="container text-center text-md-start mt-5">
            <div className="row mt-3">
              <div className="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">
                <h6 className="text-uppercase fw-bold footer-header">Mobipay</h6>
                <hr className="mb-4 mt-0 d-inline-block mx-auto" style={{ width: '60px', backgroundColor: '#7c4dff', height: '2px' }} />
                <p>
                  Mobipay is your trusted UPI payment app for secure and fast transactions. Simplify your payments with Mobipay.
                </p>
              </div>
              <div className="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">
                <h6 className="text-uppercase fw-bold footer-header">Features</h6>
                <hr className="mb-4 mt-0 d-inline-block mx-auto" style={{ width: '60px', backgroundColor: '#7c4dff', height: '2px' }} />
                <p>
                  <a href="#!" className="text-white footer-link">Instant Transfers</a>
                </p>
                <p>
                  <a href="#!" className="text-white footer-link">Bill Payments</a>
                </p>
                <p>
                  <a href="#!" className="text-white footer-link">Recharge</a>
                </p>
                <p>
                  <a href="#!" className="text-white footer-link">Transaction History</a>
                </p>
              </div>
              <div className="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">
                <h6 className="text-uppercase fw-bold footer-header">Useful links</h6>
                <hr className="mb-4 mt-0 d-inline-block mx-auto" style={{ width: '60px', backgroundColor: '#7c4dff', height: '2px' }} />
                <p>
                  <a href="#!" className="text-white footer-link">Your Account</a>
                </p>
                <p>
                  <a href="#!" className="text-white footer-link">Help Center</a>
                </p>
                <p>
                  <a href="#!" className="text-white footer-link">Privacy Policy</a>
                </p>
                <p>
                  <a href="#!" className="text-white footer-link">Terms of Service</a>
                </p>
              </div>
              <div className="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">
                <h6 className="text-uppercase fw-bold footer-header">Contact</h6>
                <hr className="mb-4 mt-0 d-inline-block mx-auto" style={{ width: '60px', backgroundColor: '#7c4dff', height: '2px' }} />
                <p><i className="fas fa-home mr-3"></i> Bengaluru, KA 560001, India</p>
                <p><i className="fas fa-envelope mr-3"></i> support@mobipay.com</p>
                <p><i className="fas fa-phone mr-3"></i> +91 98765 43210</p>
                <p><i className="fas fa-print mr-3"></i> +91 98765 43211</p>
              </div>
            </div>
          </div>
        </section>
        <div className="text-center p-3" style={{ backgroundColor: 'rgba(0, 0, 0, 0.2)' }}>
          © 2025 Copyright:
          <a className="text-white footer-link" href="https://mobipay.com/">Mobipay.com</a>
        </div>
      </footer>
    </div>
  );
};

export default Footer;