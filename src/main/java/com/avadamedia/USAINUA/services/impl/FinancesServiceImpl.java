package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.Finances;
import com.avadamedia.USAINUA.repositories.FinancesRepository;
import com.avadamedia.USAINUA.services.FinancesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancesServiceImpl implements FinancesService {
    private final FinancesRepository financesRepository;

    public void save(Finances finances){financesRepository.save(finances);}
    public List<Finances> getAll(){return financesRepository.findAll();}
}
