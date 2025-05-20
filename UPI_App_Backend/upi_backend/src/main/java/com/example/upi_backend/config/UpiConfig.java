package com.example.upi_backend.config;

import com.example.upi_backend.dto.PaymentTransferDto;
import com.example.upi_backend.dto.UserDto;
import com.example.upi_backend.model.PaymentTransfer;
import com.example.upi_backend.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
public class UpiConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        // Strict matching strategy
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // User -> UserDto
        TypeMap<User, UserDto> userToDtoTypeMap = mapper.createTypeMap(User.class, UserDto.class);
        userToDtoTypeMap.addMappings(m -> {
            m.map(User::getPhoneNumber, UserDto::setPhoneNumber);
            m.map(User::getSentTransfers, UserDto::setSentTransfers);
            m.map(User::getReceivedTransfers, UserDto::setReceivedTransfers);
        });

        // UserDto -> User
        TypeMap<UserDto, User> dtoToUserTypeMap = mapper.createTypeMap(UserDto.class, User.class);
        dtoToUserTypeMap.addMappings(m -> {
            m.map(UserDto::getPhoneNumber, User::setPhoneNumber);
            m.map(UserDto::getSentTransfers, User::setSentTransfers);
            m.map(UserDto::getReceivedTransfers, User::setReceivedTransfers);
        });

        TypeMap<PaymentTransfer, PaymentTransferDto> paymentTransferToDtoMap = mapper.createTypeMap(PaymentTransfer.class, PaymentTransferDto.class);
        paymentTransferToDtoMap.addMappings(m -> {
            // Mapping the phone number of the sender (fromUser) to fromUserPhone in DTO
            m.map(src -> src.getFromUser().getPhoneNumber(), PaymentTransferDto::setFromUser);
            // Mapping the phone number of the receiver (toUser) to toUserPhone in DTO
            m.map(src -> src.getToUser().getPhoneNumber(), PaymentTransferDto::setToUser);
        });
        return mapper;
    }

    @Bean
    public BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }
}
