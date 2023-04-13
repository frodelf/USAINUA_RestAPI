package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.models.Finances;
import com.avadamedia.USAINUA.repositories.FinancesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinancesService {
    private final FinancesRepository financesRepository;

    public void save(Finances finances){financesRepository.save(finances);}
}
