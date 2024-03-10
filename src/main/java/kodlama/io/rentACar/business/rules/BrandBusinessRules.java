package kodlama.io.rentACar.business.rules;

import kodlama.io.rentACar.core.utilities.exception.BusinessException;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BrandBusinessRules {

    private BrandRepository brandRepository;

    public void checkIfBrandExists(String name){
     if (this.brandRepository.existsByName(name)){
         throw new BusinessException("Brand name already exists"); // java exception types ne işe yarar araştır
     }

    }
}
