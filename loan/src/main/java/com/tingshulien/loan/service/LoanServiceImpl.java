package com.tingshulien.loan.service;

import com.tingshulien.loan.constant.LoanConstants;
import com.tingshulien.loan.dto.LoanDto;
import com.tingshulien.loan.entity.Loan;
import com.tingshulien.loan.exception.LoanAlreadyExistsException;
import com.tingshulien.loan.exception.ResourceNotFoundException;
import com.tingshulien.loan.mapper.LoanMapper;
import com.tingshulien.loan.repository.LoanRepository;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

  private final LoanRepository loanRepository;

  private final LoanMapper loanMapper;

  @Override
  public void createLoan(String mobileNumber) {
    Optional<Loan> optionalLoan= loanRepository.findByMobileNumber(mobileNumber);
    if(optionalLoan.isPresent()){
      throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber "+mobileNumber);
    }

    loanRepository.save(createNewLoan(mobileNumber));
  }

  private Loan createNewLoan(String mobileNumber) {
    Loan newLoan = new Loan();
    long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
    newLoan.setLoanNumber(Long.toString(randomLoanNumber));
    newLoan.setMobileNumber(mobileNumber);
    newLoan.setLoanType(LoanConstants.HOME_LOAN);
    newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
    newLoan.setAmountPaid(0);
    newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
    return newLoan;
  }

  @Override
  public LoanDto fetchLoan(String mobileNumber) {
    Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
        () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
    );

    return loanMapper.toDto(loan);
  }

  @Override
  public boolean updateLoan(LoanDto loanDto) {
    Loan loan = loanRepository.findByLoanNumber(loanDto.getLoanNumber()).orElseThrow(
        () -> new ResourceNotFoundException("Loan", "LoanNumber", loanDto.getLoanNumber()));

    loan.setLoanNumber(loanDto.getLoanNumber());
    loan.setLoanType(loanDto.getLoanType());
    loan.setMobileNumber(loanDto.getMobileNumber());
    loan.setTotalLoan(loanDto.getTotalLoan());
    loan.setAmountPaid(loanDto.getAmountPaid());
    loan.setOutstandingAmount(loanDto.getOutstandingAmount());
    loanRepository.save(loan);
    return  true;
  }

  @Override
  public boolean deleteLoan(String mobileNumber) {
    Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
        () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
    );

    loanRepository.deleteById(loan.getLoanId());
    return true;
  }

}
