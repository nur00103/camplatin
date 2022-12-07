package com.example.brestaskk.service.impl;

import com.example.brestaskk.dto.request.CustomerRequest;
import com.example.brestaskk.dto.response.CustomerResponse;
import com.example.brestaskk.dto.response.ResponseModel;
import com.example.brestaskk.entity.Customer;
import com.example.brestaskk.enums.EnumStatus;
import com.example.brestaskk.enums.ExceptionEnum;
import com.example.brestaskk.exception.MyException;
import com.example.brestaskk.repository.CustomerRepository;
import com.example.brestaskk.repository.CustomerTypeRepository;
import com.example.brestaskk.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
Logger logger= LoggerFactory.getLogger(CustomerServiceImpl.class);
        private final CustomerRepository customerRepository;

        private final CustomerTypeRepository customerTypeRepository;
        private final ObjectMapper objectMapper;
        private final ModelMapper modelMapper;
        @Override
        public ResponseModel<List<CustomerResponse>> getAllCustomer() {
            List<Customer>customerList=customerRepository.findAll();
            if (customerList.isEmpty() || customerList==null){
                throw new MyException(ExceptionEnum.EMPTY);
            }
            List<CustomerResponse> customerResponseList=customerList.stream().map(customer -> {
                CustomerResponse customerResponse=convertToResponse(customer);
                return customerResponse;
            }).collect(Collectors.toList());
            return ResponseModel.<List<CustomerResponse>>builder().result(customerResponseList).error(false)
                    .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
        }

        @Override
        public ResponseModel<CustomerResponse> getCustomerById(Long customerId) {
            CustomerResponse customerResponse=null;
            if (customerRepository.findByIdAndActive(customerId, EnumStatus.ONLINE.getCode()).isPresent()){
                Customer customer=customerRepository.findByIdAndActive(customerId, EnumStatus.ONLINE.getCode()).get();
                customerResponse=convertToResponse(customer);
            }else{
                throw new MyException(ExceptionEnum.USER_NOT_FOUND);
            }
            return ResponseModel.<CustomerResponse>builder().result(customerResponse).error(false)
                    .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
        }

        @Override
        public ResponseModel<CustomerResponse> addCustomer(CustomerRequest customerRequest) {
            Customer customer=requestToEntity(customerRequest);
            logger.info(customer.getFatherName());
            Customer savedCustomer=customerRepository.save(customer);
            CustomerResponse customerResponse=convertToResponse(customer);
            return ResponseModel.<CustomerResponse>builder().result(customerResponse).error(false)
                    .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
        }

        @Override
        public ResponseModel<CustomerResponse> updateCustomer(Long customerId, CustomerRequest customerRequest) {
            Customer customer=customerRepository.findByIdAndActive(customerId, EnumStatus.ONLINE.getCode())
                    .orElseThrow(()->new MyException(ExceptionEnum.USER_NOT_FOUND));
            customer=modelMapper.map(customerRequest,Customer.class);
            customer.setId(customerId);
            Customer updatedCustomer=customerRepository.save(customer);
            CustomerResponse customerResponse=convertToResponse(updatedCustomer);
            return ResponseModel.<CustomerResponse>builder().result(customerResponse).error(false)
                    .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();

        }
        @Override
        public boolean checkCustomerData(Long customerId){
            if (!customerRepository.findByIdAndActive(customerId, EnumStatus.ONLINE.getCode()).isPresent()){
                return false;
            }else {
                return true;
            }
        }

        @Override
        public boolean checkCustomerDataRequest(CustomerRequest customerRequest) {
            if (customerRepository.findByFinAndActive(customerRequest.getFin(),EnumStatus.ONLINE.getCode()).isPresent()){
                return false;
            }else {
                return true;
            }
        }


        @Override
        public ResponseModel<CustomerResponse> deleteCustomer(Long customerId) {
            Customer customer=customerRepository.findByIdAndActive(customerId, EnumStatus.ONLINE.getCode())
                    .orElseThrow(()->new MyException(ExceptionEnum.USER_NOT_FOUND));
            customer.setActive(EnumStatus.OFFLINE.getCode());
            customerRepository.save(customer);
            return ResponseModel.<CustomerResponse>builder().result(null).error(false)
                    .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
        }
        @Override
        public CustomerResponse convertToResponse(Customer customer){
          CustomerResponse customerResponse= objectMapper.convertValue(customer,CustomerResponse.class);
          return customerResponse;
        }

        @Override
        public Customer requestToEntity(CustomerRequest customerRequest){
            logger.info(customerRequest.getFatherName());
            Customer customer=modelMapper.map(customerRequest,Customer.class);
            customer.setFatherName(customerRequest.getFatherName());
            return customer;
        }
    }


