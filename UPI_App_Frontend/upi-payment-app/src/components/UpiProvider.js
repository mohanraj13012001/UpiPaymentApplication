import React, { createContext, useState } from "react";

export const UPIContext = createContext();

export const UpiProvider = ({ children }) => {
  const [phoneNumber, setPhoneNumber] = useState("");
  const [secretPin, setSecretPin] = useState("");

  const logout = () => {
    setPhoneNumber("");
    setSecretPin("");
  };

  return (
    <UPIContext.Provider value={{ phoneNumber, setPhoneNumber, secretPin, setSecretPin, logout }}>
      {children}
    </UPIContext.Provider>
  );
};