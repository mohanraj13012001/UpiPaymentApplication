import './App.css';
import { Route, Routes } from 'react-router-dom';
import Home from './components/Home';
import RegisterUser from './components/RegisterUser';
import TransferMoney from './components/TransferMoney';
import CheckBalance from './components/CheckBalance';
import Navbar from './components/Navbar';
import AddMoney from './components/AddMoney';
import { UpiProvider } from './components/UpiProvider'; 
import Login from './components/Login';

import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Dashboard from './components/Dashboard';

function App() {
  return (
    <UpiProvider> 
      <div className="App">
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/register" element={<RegisterUser />} />
          <Route path="/login" element={<Login />} />
          <Route path="/transfer" element={<TransferMoney />} />
          <Route path="/balance" element={<CheckBalance />} />
          <Route path="/add-money" element={<AddMoney />} />
          <Route path="/dashboard" element={<Dashboard />} />
        </Routes>

        <ToastContainer
          position="top-right"
          autoClose={3000}
          hideProgressBar={false}
          newestOnTop={false}
          closeOnClick
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover
          theme="light"
        />
      </div>
    </UpiProvider>
  );
}

export default App;
